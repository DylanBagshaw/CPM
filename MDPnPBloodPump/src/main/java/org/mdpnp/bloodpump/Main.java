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
import ice.Time_t;
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

	public static InstanceHandle_t numeric_Handle;
	public static ice.NumericDataWriter numeric_Writer;
	public static ice.Numeric flow_rate, pump_velocity, blood_temp, blood_press, vol_bypassed;

	private static boolean stopThePump;
	boolean active, paused;
	public double seconds = 0;

	public void initialize() {
		log.info("App Started");
		setClock();

		dialController.init(this);
		// rotatorController.init(this);

		numeric_Handle = numeric_Writer.register_instance(flow_rate);

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
		}, 0, 1000);
	}

	public void pumpData() {
		BigDecimal round;
		// round = new BigDecimal(Dial.rate);
		// round = round.setScale(2, BigDecimal.ROUND_HALF_UP);

		// // bypass flow rate
		// try {
		// Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		// bypassStatus.bypass_flow_lmin = Dial.rate.doubleValue();
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_flow_lmin = 0;
		// }
		//
		// if (bypassStatus.bypass_flow_lmin < 0.1)
		// active = false;
		// else
		// active = true;
		//
		// // bypass status
		// try {
		// bypassStatus.bypassActive = active;
		// } catch (NullPointerException npe) {
		// bypassStatus.bypassActive = false;
		// }
		//
		// // bypass speed
		// try {
		// bypassStatus.bypass_speed_rpm = BloodParameters.generateRPM();
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_speed_rpm = 0;
		// }
		//
		// // blood temp
		// try {
		// bypassStatus.blood_temp_celsius = BloodParameters.generateBloodTemp();
		// } catch (NullPointerException npe) {
		// bypassStatus.blood_temp_celsius = 0;
		// }
		//
		// // blood pressure
		// try {
		// bypassStatus.blood_press_mmhg = 0;
		// } catch (NullPointerException npe) {
		// bypassStatus.blood_press_mmhg = 0;
		// }
		//
		// // volume bypassed
		// try {
		// round = new BigDecimal(Dial.volume);
		// round = round.setScale(2, BigDecimal.ROUND_HALF_UP);
		// bypassStatus.volume_bypassed_ml = round.doubleValue();
		// } catch (NullPointerException npe) {
		// bypassStatus.volume_bypassed_ml = 0;
		// }
		//
		// // time on bypass
		// try {
		// bypassStatus.bypass_duration_seconds = (int) seconds;
		// } catch (NullPointerException npe) {
		// bypassStatus.bypass_duration_seconds = 0;
		// }
		//
		// // write to supervisor
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

		if (infusionStatus.solution_volume_ml < 0.1)
			active = false;
		else
			active = true;
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

		// volume bypassed
		try {
			round = new BigDecimal(Dial.volume);
			round = round.setScale(2, BigDecimal.ROUND_HALF_UP);
			infusionStatus.volume_to_be_infused_ml = round.intValue();
		} catch (NullPointerException npe) {
			infusionStatus.volume_to_be_infused_ml = 0;
		}

		// we'll do flow rate here...
		try {
			Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			infusionStatus.solution_volume_ml = Dial.rate.intValue();
		} catch (NullPointerException npe) {
			infusionStatus.solution_volume_ml = 0;
		}

		// time passed
		try {
			infusionStatus.infusion_duration_seconds = (int) seconds;
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

		long time = System.currentTimeMillis();
		flow_rate = new ice.Numeric();
		flow_rate.unique_device_identifier = BloodPumpHandler.id;
		flow_rate.metric_id = rosetta.MDC_FLOW_FLUID_PUMP.VALUE;
		flow_rate.vendor_metric_id = rosetta.MDC_FLOW_FLUID_PUMP.VALUE;

		// this may not be the best metric
		// flow_rate.metric_id = rosetta.MDC_FLOW_BLD_PULM_CAP.VALUE;
		// flow_rate.vendor_metric_id = rosetta.MDC_FLOW_BLD_PULM_CAP.VALUE;
		flow_rate.unit_id = rosetta.MDC_DIM_X_L_PER_MIN.VALUE;
		// flow_rate.instance_id = 0;
		try {
			// Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			flow_rate.value = Dial.rate.intValue();
		} catch (Exception e) {
			flow_rate.value = 0;
		}
		flow_rate.device_time = new Time_t();
		flow_rate.presentation_time = new Time_t();

		flow_rate.instance_id = 1;
		flow_rate.device_time.sec = (int) (time / 1000L);
		flow_rate.device_time.nanosec = (int) (time % 1000L * 1000000L);
		flow_rate.presentation_time.copy_from(flow_rate.device_time);
		numeric_Writer.write(flow_rate, numeric_Handle);

		// pressure of blood in pump
		blood_press = new ice.Numeric();
		blood_press.unique_device_identifier = BloodPumpHandler.id;
		blood_press.metric_id = rosetta.MDC_PRESS_BLD_ART_MEAN.VALUE;
		blood_press.vendor_metric_id = rosetta.MDC_PRESS_BLD_ART_MEAN.VALUE;
		blood_press.unit_id = rosetta.MDC_DIM_MMHG.VALUE;
		try {
			blood_press.value = 0;
		} catch (Exception e) {
			blood_press.value = 0;
		}
		blood_press.device_time = new Time_t();
		blood_press.presentation_time = new Time_t();

		blood_press.instance_id = 1;
		blood_press.device_time.sec = (int) (time / 1000L);
		blood_press.device_time.nanosec = (int) (time % 1000L * 1000000L);
		blood_press.presentation_time.copy_from(blood_press.device_time);
		numeric_Writer.write(blood_press, numeric_Handle);

		// time on bypass
		// = new ice.Numeric();
		// .unique_device_identifier = BloodPumpHandler.id;
		// .metric_id = rosetta.
		// .vendor_metric_id = rosetta.
		// .unit_id = rosetta.MDC_DIM_SEC;
		// .device_time = new Time_t();
		// .presentation_time = new Time_t();
		//
		// .instance_id = 1;
		// .device_time.sec = (int) (time / 1000L);
		// .device_time.nanosec = (int) (time % 1000L * 1000000L);
		// .presentation_time.copy_from(.device_time);

		// volume bypassed
		vol_bypassed = new ice.Numeric();
		vol_bypassed.unique_device_identifier = BloodPumpHandler.id;
		vol_bypassed.metric_id = rosetta.MDC_VOL_FLUID_DELIV.VALUE;
		vol_bypassed.vendor_metric_id = rosetta.MDC_VOL_FLUID_DELIV.VALUE;
		vol_bypassed.unit_id = rosetta.MDC_DIM_L.VALUE;
		try {
			round = new BigDecimal(Dial.volume);
			round = round.setScale(2, BigDecimal.ROUND_HALF_UP);
			vol_bypassed.value = round.floatValue();
		} catch (Exception e) {
			vol_bypassed.value = 0;
		}
		vol_bypassed.device_time = new Time_t();
		vol_bypassed.presentation_time = new Time_t();

		vol_bypassed.instance_id = 1;
		vol_bypassed.device_time.sec = (int) (time / 1000L);
		vol_bypassed.device_time.nanosec = (int) (time % 1000L * 1000000L);
		vol_bypassed.presentation_time.copy_from(vol_bypassed.device_time);
		numeric_Writer.write(vol_bypassed, numeric_Handle);

		// blood temp
		blood_temp = new ice.Numeric();
		blood_temp.unique_device_identifier = BloodPumpHandler.id;
		blood_temp.metric_id = rosetta.MDC_TEMP_BLD.VALUE;
		blood_temp.vendor_metric_id = rosetta.MDC_TEMP_BLD.VALUE;
		blood_temp.unit_id = rosetta.MDC_DIM_DEGC.VALUE;
		try {

			blood_temp.value = (float) BloodParameters.generateBloodTemp();
		} catch (Exception e) {
			blood_temp.value = 0;
		}
		blood_temp.device_time = new Time_t();
		blood_temp.presentation_time = new Time_t();

		blood_temp.instance_id = 1;
		blood_temp.device_time.sec = (int) (time / 1000L);
		blood_temp.device_time.nanosec = (int) (time % 1000L * 1000000L);
		blood_temp.presentation_time.copy_from(blood_temp.device_time);
		numeric_Writer.write(blood_temp, numeric_Handle);

		// bypass pump velocity (rpm)
		// not the correct way to use velocity index as it's defined as "Peak velocity
		// of blood flow in the aorta" MDC_DIM_PER_KILO_SEC (*/1000s) however I will use
		// it to describe the velocity of the bypass pump in rpms
		pump_velocity = new ice.Numeric();
		pump_velocity.unique_device_identifier = BloodPumpHandler.id;
		pump_velocity.metric_id = rosetta.MDC_VELOCITY_INDEX.VALUE;
		pump_velocity.vendor_metric_id = rosetta.MDC_VELOCITY_INDEX.VALUE;
		pump_velocity.unit_id = rosetta.MDC_DIM_X_ROTATIONS_PER_MIN.VALUE;
		try {
			// Dial.rate = Dial.rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			pump_velocity.value = (float) BloodParameters.generateRPM();
		} catch (Exception e) {
			pump_velocity.value = 0;
		}
		pump_velocity.device_time = new Time_t();
		pump_velocity.presentation_time = new Time_t();

		pump_velocity.instance_id = 1;
		pump_velocity.device_time.sec = (int) (time / 1000L);
		pump_velocity.device_time.nanosec = (int) (time % 1000L * 1000000L);
		pump_velocity.presentation_time.copy_from(pump_velocity.device_time);
		numeric_Writer.write(pump_velocity, numeric_Handle);
	}

	public void setInterlockStop(boolean interLockStop) {
		Main.stopThePump = interLockStop;

		if (!stopThePump) {
			// paused = false;
			// log.info("Pump is no longer in interlock.");
		}
	}
}
