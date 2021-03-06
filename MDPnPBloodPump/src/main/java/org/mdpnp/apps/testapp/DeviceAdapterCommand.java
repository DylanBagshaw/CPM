/*******************************************************************************
 * Copyright (c) 2017, MD PnP Program
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package org.mdpnp.apps.testapp;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

import org.mdpnp.devices.AbstractDevice;
import org.mdpnp.devices.DeviceDriverProvider;
import org.mdpnp.devices.DeviceDriverProvider.DeviceType;
import org.mdpnp.rtiapi.data.EventLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import com.rti.dds.subscription.Subscriber;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * single vm batch command. assumes none of the run-time support available yet -
 * no top-level spring context exists yet.
 */

public class DeviceAdapterCommand implements Configuration.HeadlessCommand, Configuration.GUICommand {

	private static final Logger log = LoggerFactory.getLogger(org.mdpnp.apps.testapp.DeviceAdapterCommand.class);

	// Parent remote;
	// static Remote remoteController = new Remote();

	@Override
	public int execute(final Configuration config) throws Exception {
		// TODO revisit check for headless and check for FX Application Thread
		// This attempts to initialize the default Toolkit which will fail in truly
		// headless environments. Is there another precheck for a graphical display that
		// can be called before this? or is it possible to substitute a different
		// Toolkit?
		// if(Platform.isFxApplicationThread())
		// throw new IllegalStateException("Trying to start headless blocking device
		// adapter on UI thread");

		DeviceDriverProvider ddp = DeviceFactory.getDeviceDriverProvider("MD PnP Pump");
		if (null == ddp) {
			log.error("Unknown device type was specified");
			throw new Exception("Unknown device type was specified");
		}

		final AbstractApplicationContext context = config.createContext("DeviceAdapterContext.xml");

		HeadlessAdapter da = new HeadlessAdapter(ddp, context, false); // TODO

		da.setAddress(config.getAddress());

		da.init();

		// this will block until stops kills everything from another thread or a
		// VM's shutdown hook
		da.run();

		// will only get here once the controller loop is stopped
		context.close();

		return 0;
	}

	@Override
	public IceApplication create(Configuration config) throws Exception {

		if (Platform.isFxApplicationThread() && config.isHeadless())
			throw new IllegalStateException("Attempting to start headless app on the UI thread");

		DeviceDriverProvider ddp = DeviceFactory.getDeviceDriverProvider("MD PnP Cardiopulmonary Pump");
		if (null == ddp) {
			log.error("Unknown device type was specified");
			throw new Exception("Unknown device type was specified");
		}

		final AbstractApplicationContext context = config.createContext("DeviceAdapterContext.xml");

		GUIAdapter da = new GUIAdapter(ddp, context) {
			@Override
			public void stop() {
				super.stop();
				// at the very end; kill the context that was created here.
				log.info("Shut down spring context");
				context.close();
			}
		};

		da.setAddress(config.getAddress());

		return da;
	}

	static class HeadlessAdapter extends Observable implements DeviceDriverProvider.DeviceAdapter, Runnable {

		HeadlessAdapter(DeviceDriverProvider df, AbstractApplicationContext parentContext, boolean isStandalone)
				throws Exception {

			deviceType = df.getDeviceType();
			deviceHandle = df.create(parentContext);

			if (isStandalone) {
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					public void run() {
						log.info("Calling killAdapter from shutdown hook");
						stop();
					}
				}));
			}
		}

		protected void update(String msg, int pct) {
			log.info(pct + "% " + msg);
		}

		public void init() throws Exception {

			if (null != initialPartition) {
				deviceHandle.setPartition(initialPartition);
			}
			if (null != address)
				deviceHandle.setAddress(address);
		}

		/**
		 * blocking call to start adapter's listening loop. It is expected that stop API
		 * will be called on another thread
		 */
		@Override
		public void run() {

			if (!deviceHandle.connect()) {
				stopOk.countDown();
			}

			// Wait until killAdapter
			try {
				stopOk.await();
			} catch (InterruptedException ex) {
				log.error("Device adapter run failed to block on start/stop latch", ex);
				throw new RuntimeException("Device adapter run failed to block on start/stop latch", ex);
			}
		}

		@Override
		public synchronized void stop() {

			Metrics metrics = new Metrics();
			try {
				update("Ask the device to disconnect from the ICE", 50);
				long tm = metrics.start();
				deviceHandle.disconnect();
				metrics.stop("disconnect", tm);

				update("Shutting down the device", 75);
				tm = metrics.start();
				deviceHandle.stop();
				metrics.stop("device.shutdown", tm);
			} catch (Exception ex) {
				log.error("Failed to stop", ex);
				throw ex;
			} finally {
				stopOk.countDown();
			}
		}

		protected String[] initialPartition;
		private String address = null;

		private final CountDownLatch stopOk = new CountDownLatch(1);

		private final DeviceDriverProvider.DeviceAdapter deviceHandle;
		private final DeviceType deviceType;

		protected DeviceType getDeviceType() {
			return deviceType;
		}

		@Override
		public void setAddress(String v) {
			address = v;
		}

		@Override
		public AbstractDevice getDevice() {
			return deviceHandle.getDevice();
		}

		public <T> T getComponent(String name, Class<T> requiredType) throws Exception {
			return deviceHandle.getComponent(name, requiredType);
		}

		public <T> T getComponent(Class<T> requiredType) throws Exception {
			return deviceHandle.getComponent(requiredType);
		}

		@Override
		public void setPartition(String[] v) {
			initialPartition = v;
		}

		@Override
		public boolean connect() {
			return deviceHandle.connect();
		}

		@Override
		public void disconnect() {
			deviceHandle.disconnect();
		}
	}

	public static class GUIAdapter extends IceApplication implements DeviceDriverProvider.DeviceAdapter, Runnable {

		private final HeadlessAdapter controller;

		public GUIAdapter(DeviceDriverProvider deviceFactory, AbstractApplicationContext context) throws Exception {
			controller = new HeadlessAdapter(DeviceFactory.getDeviceDriverProvider("MD PnP Cardiopulmonary Pump"), context, false) { // TODO
			};
		}

		@Override
		public void stop() {

			if (!Platform.isFxApplicationThread())
				throw new IllegalStateException("Sneaky developer! Trying to stop ui outside of FX thread");

			// Required to trigger destruction of animated DevicePanels
			log.info("Shut down local user interface");

			controller.stop();
		}

		@Override
		public void init() throws Exception {
			super.init();
			controller.init();
		}

		@Override
		public void start(final Stage primaryStage) throws Exception {

			// if(!Platform.isFxApplicationThread())
			// throw new IllegalStateException("Sneaky developer! Trying to start ui outside
			// of FX thread");

			@SuppressWarnings("unused")
			AbstractDevice device = controller.getDevice();
			@SuppressWarnings("unused")
			DeviceType deviceType = controller.getDeviceType();

			// Use the device subscriber so that we
			// automatically maintain the same partition as the device
			@SuppressWarnings("unused")
			final EventLoop eventLoop = controller.getComponent("eventLoop", EventLoop.class);
			@SuppressWarnings("unused")
			final Subscriber subscriber = controller.getComponent("subscriber", Subscriber.class);

			// TODO These beans are required only for the standalone adapter with GUI,
			// perhaps they should get their own spring config though?
			// TODO contentfilter these on the one device?

			@SuppressWarnings("unused")
			org.mdpnp.devices.TimeManager tm = controller.getComponent(org.mdpnp.devices.TimeManager.class);

			// FXMLLoader loader = new FXMLLoader(Remote.class.getResource("Remote.fxml"));
			// loader.setController(remoteController);
			// Parent root = loader.load();

			Platform.runLater(() -> {

				Stage stage = primaryStage == null ? new Stage(StageStyle.DECORATED) : primaryStage;

				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent event) {

						log.info("Shutting down");

						// this is a dialog - the application's 'close' event
						// wont happen
						// if (primaryStage == null) {
						try {
							GUIAdapter.this.stop();
						} catch (Exception e) {
							log.error("Failed to stop device adapter");
						}
						// }
						Platform.exit();
						System.exit(0);
					}
				});
//				Scene scene = new Scene(root);
//				scene.setCursor(Cursor.HAND);
//				//
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//				stage.setTitle("MDPnP CP Pump");
//				stage.setScene(scene);
//
//				stage.show();
//
//				Rectangle2D screen = Screen.getPrimary().getVisualBounds();
//				if (screen.getHeight() < 600 && screen.getWidth() < 850)
//					stage.setFullScreen(true);
//
//				stage.setResizable(false);
//				stage.getScene().getAccelerators().put(KeyCombination.keyCombination("CTRL+C"), () -> {
//					Platform.exit();
//					System.exit(0);
//				});

				Thread deviceRunner = new Thread(AbstractDevice.threadGroup, this);
				deviceRunner.setDaemon(true);
				deviceRunner.start();
			});
		}

		@Override
		public void setPartition(String[] v) {
			controller.setPartition(v);
		}

		@Override
		public void addObserver(Observer v) {
			controller.addObserver(v);
		}

		@Override
		public void deleteObserver(Observer v) {
			controller.deleteObserver(v);
		}

		@Override
		public AbstractDevice getDevice() {
			return controller.getDevice();
		}

		@Override
		public void run() {
			controller.run();
		}

		@Override
		public void setAddress(String address) {
			controller.setAddress(address);
		}

		@Override
		public <T> T getComponent(String name, Class<T> requiredType) throws Exception {
			return controller.getComponent(name, requiredType);
		}

		@Override
		public <T> T getComponent(Class<T> requiredType) throws Exception {
			return controller.getComponent(requiredType);
		}

		@Override
		public boolean connect() {
			return controller.connect();
		}

		@Override
		public void disconnect() {
			controller.disconnect();
		}
	}

	private static class Metrics {
		long start() {
			return System.currentTimeMillis();
		}

		long stop(String s, long tm) {
			log.trace(s + " took " + (System.currentTimeMillis() - tm) + "ms");
			return start();
		}
	}

}
