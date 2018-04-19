package org.mdpnp.apps.testapp;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login {

	private Stage stage;
	boolean locked = true;

	public boolean isLocked() {
		return locked;
	}

	public void showLogin(Stage primaryStage) throws IOException {

		stage = primaryStage;

		stage.setTitle("Login Screen");
		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		final Text scenetitle = new Text("");
		scenetitle.setId("login-text");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Username:");
		grid.add(userName, 0, 1);

		final TextField un = new TextField();
		grid.add(un, 1, 1);

		Label pwLabel = new Label("Password:");
		grid.add(pwLabel, 0, 2);

		final PasswordField pw = new PasswordField();
		grid.add(pw, 1, 2);

		Button btn = new Button("Sign-in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		// btn.defaultButtonProperty().bind(btn.focusedProperty());
		btn.setDefaultButton(true);

		Scene scene = new Scene(grid, 450, 300);
		stage.setScene(scene);
		stage.show();

		new Thread(() -> {
			CardReader.Reader();
			if (CardReader.getResponse() != null) {
				scenetitle.setText("Welcome!");
				scenetitle.setId("welcome-text");
				un.setText("badge");
				pw.setText("badge");
				locked = false;
			}
		}).start();

		btn.setOnAction(e -> {
			final String usernameText = un.getText();
			final String passwordText = pw.getText();
			if (!usernameText.isEmpty() && !passwordText.isEmpty()) {
				scenetitle.setText("Welcome!");
				scenetitle.setId("welcome-text");
				locked = false;
				stage.hide();

			} else {
				locked = true;
				scenetitle.setText("Invalid");
				scenetitle.setId("reject-text");
			}
		});

	}

}
