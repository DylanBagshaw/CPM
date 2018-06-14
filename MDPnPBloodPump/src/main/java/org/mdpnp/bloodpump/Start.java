package org.mdpnp.bloodpump;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

import org.mdpnp.apps.testapp.Configuration;
import org.mdpnp.apps.testapp.ControlFlowHandler;
import org.mdpnp.apps.testapp.DeviceFactory;
import org.mdpnp.apps.testapp.DialogUtils;
import org.mdpnp.apps.testapp.IceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Start extends Application {

	private static final Logger log = LoggerFactory.getLogger(Start.class);

	private final static File[] searchPath = new File[] { new File(".JumpStartSettings"),
			new File(System.getProperty("user.home"), ".JumpStartSettings") };

	static Configuration runConf;
	static IceApplication app;

	static void loadSystemProps() throws IOException {
		URL u = Start.class.getResource("/ice.system.properties");
		if (u != null) {
			log.info("Loading base system configuration from " + u.toExternalForm());
			InputStream is = u.openStream();
			System.getProperties().load(is);
			is.close();
		}
		File f = new File("ice.system.properties");
		if (f.exists()) {
			log.info("Loading user overrides configuration from " + f.getAbsolutePath());
			InputStream is = new FileInputStream(f);
			System.getProperties().load(is);
			is.close();
		}
	}

	static String ip = "";
	static int domainId = 15;
	static Stage stage;

	@Override
	public void stop() throws Exception {
		super.stop();
		if (null != app) {
			app.stop();
			app = null;
		}
	}

	public static Throwable unwind(Throwable t, Class<? extends Throwable> clazz) {

		while (!clazz.isAssignableFrom(t.getClass()) && t.getCause() != null) {
			t = t.getCause();
		}
		return t;
	}

	public static void main(String[] args) throws Exception {
		loadSystemProps();

		Configuration runConf;
		if (args.length > 0) {
			runConf = Configuration.read(args);

			if (null == runConf) {
				return;
			} else {
				Configuration.searchAndSaveSettings(runConf, searchPath);
			}
			Configuration.HeadlessCommand cmd = runConf.getCommand();
			int retCode = cmd.execute(runConf);
			log.info("This is the end, exit code=" + retCode);
			System.exit(retCode);

		} else {
			launch(args);
			Platform.exit();
			log.info("This is the end, exit code=" + 0);
			System.exit(0);
		}
		// launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();

					if (addr instanceof Inet6Address)
						continue;

					if (addr.getHostAddress().startsWith("192."))
						ip = addr.getHostAddress();
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		runConf = Configuration.searchAndLoadSettings(searchPath);

		// ConfigurationDialog d = ConfigurationDialog.showDialog(runConf, this);

		// It's nice to be able to change settings even without running
		// Even if the user presses 'quit' save the state so that it can be used
		// to boot strap the dialog later.
		//

		runConf = new Configuration(false, Configuration.Application.ICE_Device_Interface, domainId,
				DeviceFactory.getDeviceDriverProvider("MD PnP Cardiopulmonary Pump"), ip, "");

		Object o = runConf.getApplication().getAppClass().getConstructor().newInstance();

		if (o instanceof Configuration.GUICommand) {
			o = ((Configuration.GUICommand) o).create(runConf);
		}

		if (o instanceof IceApplication) {
			app = (IceApplication) o;

			try {
				app.setConfiguration(runConf);
				app.init();
				app.start(stage);
			} catch (Throwable ex) {
				log.error("Failed to start application", ex);

				ex = unwind(ex, ControlFlowHandler.ConfirmedError.class);
				if (!(ex instanceof ControlFlowHandler.ConfirmedError))
					DialogUtils.ExceptionDialog("Click OK to terminate application", ex);

				// Any exception here would kill the FX thread - there is no
				// point in attempting to recover as the state of the app is unknown.
				// Just exit out of the VM.
				//
				System.exit(-1);
			}
		} else {
			throw new IllegalStateException("Invalid FX application request " + o);
		}

		// primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		// @Override
		// public void handle(WindowEvent t) {
		// Platform.exit();
		// System.exit(0);
		// }
		// });

		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			// scene.setCursor(Cursor.HAND);

			// scene.getStylesheets().add(getClass().getResource("FxExperiencePlayer.css").toExternalForm());
			primaryStage.setTitle("MDPnP Cardiopulmonary Pump");
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}

		primaryStage.show();

		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		primaryStage.getScene().getAccelerators().put(KeyCombination.keyCombination("CTRL+C"), () -> {
			Platform.exit();
			System.exit(0);
		});

	}

}
