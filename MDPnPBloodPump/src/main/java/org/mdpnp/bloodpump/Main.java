package org.mdpnp.bloodpump;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// import org.mdpnp.apps.testapp.DialogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rti.dds.infrastructure.InstanceHandle_t;

import ice.BypassStatus;
import ice.BypassStatusDataWriter;
import ice.InfusionStatus;
import ice.InfusionStatusDataWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	// @FXML
	// StackPane stackPane = new StackPane();

	@FXML
	Parent dial;

	@FXML
	Dial dialController;

	// @FXML
	// Parent rotatorControl;
	//
	// @FXML
	// RotatorControl rotatorController;

	@FXML
	Label clock, pumpId;

	// public static BypassStatus bypassStatus;
	// public static InstanceHandle_t bypassStatusHandle;
	// public static BypassStatusDataWriter bypassStatusWriter;

	/***************************************************
	 * comment out the code above in this function and uncomment the code below if
	 * using OpenICE that does not have the CardioPulmonaryPump data.
	 */

	public static InfusionStatus infusionStatus;
	public static InstanceHandle_t infusionStatusHandle;
	public static InfusionStatusDataWriter infusionStatusWriter;

	/**************************************************/

	private static boolean stopThePump;
	boolean active, paused;
	public double seconds = 0;

	public void initialize() {
		log.info("App Started");
		setClock();

		dialController.init(this);
		// rotatorController.init(this);

		String id = BloodPumpHandler.id;
		int idLength = id.length();
		id = id.substring(idLength - 7);
		pumpId.setText("UID: " + id);
	}

	private void setClock() {
		// creates a clock and updates it every 0.5 seconds
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						active = true;
						seconds += 0.5;
						SimpleDateFormat date = new SimpleDateFormat("kk:mm:ss a");
						Date time = new Date();
						date.format(time);
						clock.setText(date.format(time).toString());
						// the following is irrelevant for now
						/**********************************/
						// if (stopThePump && !paused) {
						// paused = true;
						// active = false;
						// log.warn("Pump is in interlock");
						// DialogUtils.OkCancelDialog("! Interlock Stop !",
						// "OpenICE Application has detected an adverse event and has "
						// + "stopped the pump! Pump cannot be restarted until the interlock is "
						// + "cleared." + " Check on the patient before continuing the bypass.");
						// }
						/**********************************/
						pumpData();
					}
				});
			}
		}, 0, 500);
	}

	public void pumpData() {
		// BigDecimal round;
		// // round = new BigDecimal(Dial.rate);
		// // round = round.setScale(2, BigDecimal.ROUND_HALF_UP);
		// try {
		// Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		// bypassStatus.bypass_flow_lmin = Dial.rate.doubleValue();
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_flow_lmin = 0;
		// }
		// try {
		// bypassStatus.bypassActive = active;
		// } catch (NullPointerException npe) {
		// bypassStatus.bypassActive = false;
		// }
		//
		// // 1g/ml 1000mcg/ml
		// try {
		// bypassStatus.bypass_speed_rpm = BloodParameters.generateRPM();
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_speed_rpm = 0;
		// }
		//
		// // how many mls have gone through?
		// try {
		// bypassStatus.blood_temp_celsius = BloodParameters.generateBloodTemp();
		// } catch (NullPointerException npe) {
		// bypassStatus.blood_temp_celsius = 0;
		// }
		//
		// // we'll do flow rate here...
		// try {
		// bypassStatus.blood_press_mmhg = 0;
		// } catch (NullPointerException npe) {
		// bypassStatus.blood_press_mmhg = 0;
		// }
		//
		// // time passed
		// try {
		// round = new BigDecimal(Dial.volume);
		// round = round.setScale(2, BigDecimal.ROUND_HALF_UP);
		// bypassStatus.volume_bypassed_ml = round.doubleValue();
		// } catch (NullPointerException npe) {
		// bypassStatus.volume_bypassed_ml = 0;
		// }
		//
		// // NA
		// try {
		// bypassStatus.bypass_duration_seconds = (int) seconds;
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_duration_seconds = 0;
		// }
		//
		// if (bypassStatusWriter != null) {
		// try {
		// bypassStatusWriter.write(bypassStatus, bypassStatusHandle);
		// } catch (NullPointerException npe) {
		// log.error("Error writing the bypass status to OpenICE", npe);
		// }
		// }

		/******************************************************************
		 * comment out the code above in this function and uncomment the code below if
		 * using OpenICE that does not have the CardioPulmonaryPump data.
		 */

		try {
			infusionStatus.drug_name = "Blood";
		} catch (NullPointerException npe) {
			infusionStatus.drug_name = "Not Selected";
		}
		try {
			infusionStatus.infusionActive = active;
		} catch (NullPointerException npe) {
			infusionStatus.infusionActive = false;
		}

		// 1g/ml 1000mcg/ml
		try {
			infusionStatus.drug_mass_mcg = 0;
		} catch (NullPointerException npe) {
			infusionStatus.drug_mass_mcg = 0;
		}

		// how many mls have gone through?
		try {
			infusionStatus.solution_volume_ml = 0;
		} catch (NullPointerException npe) {
			infusionStatus.solution_volume_ml = 0;
		}

		// we'll do flow rate here...
		try {
			Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			infusionStatus.volume_to_be_infused_ml = Dial.rate.intValue();
		} catch (NullPointerException npe) {
			infusionStatus.volume_to_be_infused_ml = 0;
		}

		// time passed
		try {
			infusionStatus.infusion_duration_seconds = 0;
		} catch (NumberFormatException nfe) {
			infusionStatus.infusion_duration_seconds = 0;
		} catch (NullPointerException npe) {
			infusionStatus.infusion_duration_seconds = 0;
		}

		// NA
		try {
			float percentComplete = 0;
			percentComplete = 0;
			infusionStatus.infusion_fraction_complete = percentComplete;
		} catch (NullPointerException npe) {
			infusionStatus.infusion_fraction_complete = 0;
		}

		if (infusionStatusWriter != null) {
			try {
				infusionStatusWriter.write(infusionStatus, infusionStatusHandle);
			} catch (NullPointerException npe) {
				log.error("Error writing the infusion status to OpenICE", npe);
			}
		}

		/*******************************************************/

	}

	public void setInterlockStop(boolean interLockStop) {
		Main.stopThePump = interLockStop;

		if (!stopThePump) {
			// paused = false;
			// log.info("Pump is no longer in interlock.");
		}
	}
}
