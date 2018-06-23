package org.mdpnp.bloodpump;

import java.math.BigDecimal;
import java.util.Random;

import org.mdpnp.devices.simulation.GlobalSimulationObjectiveListener;
import org.mdpnp.devices.simulation.NumberWithGradient;
import org.mdpnp.devices.simulation.NumberWithJitter;

import ice.GlobalSimulationObjective;

public class BloodParameters {
	static Random random = new Random();
	static Number temp = new NumberWithJitter<Double>(30, 0.5, 28, 32);
	static double o2;
	static double co2;
	static Number rpm = new NumberWithJitter<Double>(1300, 100, 1200, 1500);
	static Number bp = new NumberWithJitter<Double>(90, 1, 80, 100);
	static int tempMin = 28;
	static int tempMax = 32;
	static int o2Min = 75;
	static int o2Max = 100;
	static int co2Min = 23;
	static int co2Max = 29;
	static int rpmMin = 1200;
	static int rpmMax = 1500;

	public static double generateBloodPressure() {
		nextDraw();
		// bp = new NumberWithJitter<Double>(90, 1, 80, 100);

		BigDecimal round;
		round = new BigDecimal(bp.doubleValue());
		round = round.setScale(2, BigDecimal.ROUND_HALF_UP);

		return round.doubleValue();
	}

	public static double generateBloodTemp() {
		nextDraw();
		// temp = new NumberWithJitter<Double>(30, 1, 28, 32);

		// temp = random.nextInt((tempMax - tempMin) + 1) + tempMin;

		BigDecimal round;
		round = new BigDecimal(temp.doubleValue());
		round = round.setScale(0, BigDecimal.ROUND_HALF_UP);

		return round.doubleValue();
	}

	public static double generateCO2() {

		co2 = random.nextInt((co2Max - co2Min) + 1) + co2Min;

		return co2;
	}

	public static double generateO2() {

		o2 = random.nextInt((o2Max - o2Min) + 1) + o2Min;

		return o2;
	}

	public static double generateRPM() {
		nextDraw();
		// rpm = new NumberWithJitter<Double>(1300, 100, 1200, 1500);

		// rpm = random.nextInt((rpmMax - rpmMin) +1) + rpmMin;

		BigDecimal round;
		round = new BigDecimal(rpm.doubleValue());
		round = round.setScale(0, BigDecimal.ROUND_HALF_UP);

		return round.doubleValue();
	}

	static double[] nextDraw() {
		return new double[] { bp.doubleValue(), temp.doubleValue(), rpm.doubleValue() };
	}

	// public void setTargetBP(Number targetBP) {
	// this.bp = new NumberWithGradient(bp, targetBP, 1);
	// }
	//
	// public void setTargetTemp(Number targetTemp) {
	// this.temp = new NumberWithGradient(temp, targetTemp, 1);
	// }
	//
	// public void setTargetRPM(Number targetRPM) {
	// this.rpm = new NumberWithGradient(rpm, targetRPM, 1);
	// }
}
