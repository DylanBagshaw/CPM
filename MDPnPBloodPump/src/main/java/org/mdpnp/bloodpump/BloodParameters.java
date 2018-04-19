package org.mdpnp.bloodpump;

import java.util.Random;

public class BloodParameters {
	static Random random = new Random();
	static double temp;
	static double o2;
	static double co2;
	static double rpm;
	static int tempMin = 28;
	static int tempMax = 32;
	static int o2Min = 75;
	static int o2Max = 100;
	static int co2Min = 23;
	static int co2Max = 29;
	static int rpmMin = 1200;
	static int rpmMax = 1500;

	public static double generateBloodTemp() {

		temp = random.nextInt((tempMax - tempMin) + 1) + tempMin;

		return temp;
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
		
		rpm = random.nextInt((rpmMax - rpmMin) +1) + rpmMin;
		
		return rpm;
	}
}
