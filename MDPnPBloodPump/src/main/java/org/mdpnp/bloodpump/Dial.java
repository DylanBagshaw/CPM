package org.mdpnp.bloodpump;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

import org.mdpnp.util.rotator.RotatorControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Dial {
	private static final Logger log = LoggerFactory.getLogger(Dial.class);

	@FXML
	Parent main;

	@FXML
	Main mainController;

	@FXML
	Label rateLabel, pressure, bloodTemp, rpm, rateUnit, tempUnit, pressureUnit, rpmUnit;

	@FXML
	BorderPane borderpane;

	RotatorControl rotator = new RotatorControl("Rotate");

	final String DEGREE = "\u00b0";
	final String SUBSCRIPT_TWO = "\u2082";
	int temp;
	static BigDecimal rate;
	static double volume = 0;
	static double rpmNumeric, bloodTempNumeric, pressureNumeric;

	// 1 PaCO2
	// 2 PaO2
	// 3 Blood viscosity
	// 4 Intercranial pressure
	// 5 Mean arterial pressures
	// 6 Central venous pressure
	// 7 Drugs

	public void init(Main main) {
		mainController = main;
		log.info("Rate Connected...");

		rateLabel.setText("0.00");
		bloodTemp.setText("0");
		tempUnit.setText(DEGREE + "C");
		rpm.setText("0.00");
		rpmUnit.setText("rpms");
		pressure.setText("0.0");
		pressureUnit.setText("mmHg");
		// o2.setText("O" + SUBSCRIPT_TWO + ":\t\t\t" + 100.0 + " mmHg");
		// co2.setText("CO" + SUBSCRIPT_TWO + ":\t\t\t" + 100.0 + " mEq/L");
		setTemp();
		measureVolume();
	}

	public void initialize() {

		borderpane.setLeft(rotator);

		// volumeKnob.setBlockIncrement(1);
		// volumeKnob.setPrefWidth(250);
		// volumeKnob.setMaxWidth(250);

		// volumeKnob.valueProperty().addListener((o, oldVal, newVal) -> {
		// volumeKnob.setRotate(newVal.doubleValue());
		// double rate = map(newVal.doubleValue(), 0, 100, 0, 100);

		// rate = new BigDecimal(volumeKnob.getValue());
		// rate = rate.setScale(2, RoundingMode.HALF_UP);
		//
		// rateValueLabel.textProperty().set("Flow Rate:\t" + rate + " L/min");
		// });

		rotator.rotationProperty().addListener((o, oldVal, newVal) -> {

			rate = new BigDecimal(RotatorControl.rate);
			rate = rate.setScale(2, RoundingMode.HALF_UP);

			rateLabel.textProperty().set(String.valueOf(rate));
			rateUnit.textProperty().set("L/min");
		});

	}

	// int measure_volume_interval = 100;

	private void measureVolume() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						double flow;

						flow = (RotatorControl.rate / 60) / 10;
						volume += flow;
					}
				});
			}
		}, 0, 100);
	}

	private void setTemp() {
		// creates a clock and updates it every second
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						if (!rateLabel.getText().equals("0.00")) {
							bloodTempNumeric = BloodParameters.generateBloodTemp();
							bloodTemp.setText(String.valueOf(bloodTempNumeric));
							
							rpmNumeric = BloodParameters.generateRPM();
							rpm.setText(String.valueOf(rpmNumeric));
							
							pressureNumeric = BloodParameters.generateBloodPressure();
							pressure.setText(String.valueOf(pressureNumeric));
							
							// o2.setText("O" + SUBSCRIPT_TWO + ":\t\t\t" + BloodParameters.generateO2() + "
							// mmHg");
							// co2.setText("CO" + SUBSCRIPT_TWO + ":\t\t\t" + BloodParameters.generateCO2()
							// + " mEq/L");
						} else {
							rateLabel.setText("0.00");
							bloodTemp.setText("0");
							rpm.setText("0.00");
							pressure.setText("0.0");
						}
					}
				});
			}
		}, 0, 1000);
	}

	@SuppressWarnings("unused")
	private static double map(double input, double old_min_range, double old_max_range, double new_min_range,
			double new_max_range) {
		double math = (input - old_min_range) * (new_max_range - new_min_range) / (old_max_range - old_min_range)
				+ new_min_range;
		return math;
	}
}
