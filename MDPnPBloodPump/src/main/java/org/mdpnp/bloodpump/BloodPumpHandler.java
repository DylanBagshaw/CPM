package org.mdpnp.bloodpump;

import org.mdpnp.devices.simulation.AbstractSimulatedConnectedDevice;
import org.mdpnp.rtiapi.data.EventLoop;
import org.mdpnp.rtiapi.data.EventLoop.ConditionHandler;
import org.mdpnp.rtiapi.data.QosProfiles;
import org.mdpnp.rtiapi.data.TopicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rti.dds.infrastructure.Condition;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.StringSeq;
import com.rti.dds.publication.Publisher;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.QueryCondition;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.Topic;

import ice.BypassObjective;
import ice.BypassStatusDataWriter;

public class BloodPumpHandler extends AbstractSimulatedConnectedDevice {

	private static final Logger log = LoggerFactory.getLogger(BloodPumpHandler.class);

	public static ice.BypassStatus bypassStatus = (ice.BypassStatus) ice.BypassStatus.create();
	public static InstanceHandle_t bypassStatusHandle;

	public static ice.BypassStatusDataWriter bypassStatusWriter;
	public static ice.BypassObjectiveDataReader bypassObjectiveReader;
	public static QueryCondition bypassObjectiveQueryCondition;
	public static Topic bypassStatusTopic, bypassObjectiveTopic;

	// private static InstanceHandle_t numeric_Handle;
	private static ice.NumericDataWriter numeric_Writer;
	// private static Topic numericTopic;

	public static String id = "";

	public class MyHandler extends Main {

	}

	private final MyHandler handler = new MyHandler();

	protected void writeIdentity() {
		deviceIdentity.manufacturer = "MDPnP";
		deviceIdentity.model = "Cardiopulmonary Pump";
		id = deviceIdentity.unique_device_identifier;
		writeDeviceIdentity();
	}

	protected void stopThePump(boolean stopThePump) {
		handler.setInterlockStop(stopThePump);
	}

	public BloodPumpHandler(Subscriber subscriber, Publisher publisher, EventLoop eventLoop) {
		super(subscriber, publisher, eventLoop);

		writeIdentity();

		ice.BypassStatusTypeSupport.register_type(getParticipant(), ice.BypassStatusTypeSupport.get_type_name());
		bypassStatusTopic = TopicUtil.findOrCreateTopic(domainParticipant, ice.BypassStatusTopic.VALUE,
				ice.BypassStatusTypeSupport.class);
		bypassStatusWriter = (BypassStatusDataWriter) publisher.create_datawriter_with_profile(bypassStatusTopic,
				QosProfiles.ice_library, QosProfiles.state, null, StatusKind.STATUS_MASK_NONE);

		bypassStatus.unique_device_identifier = deviceIdentity.unique_device_identifier;
		bypassStatusHandle = bypassStatusWriter.register_instance(bypassStatus);

		bypassStatus.bypass_flow_lmin = 0;
		bypassStatus.bypass_speed_rpm = 0;
		bypassStatus.blood_temp_celsius = 0;
		bypassStatus.blood_press_mmhg = 0;
		bypassStatus.volume_bypassed_ml = 0;
		bypassStatus.bypassActive = false;
		bypassStatus.bypass_duration_seconds = 0;

		bypassStatusWriter.write(bypassStatus, bypassStatusHandle);

		ice.BypassObjectiveTypeSupport.register_type(getParticipant(), ice.BypassObjectiveTypeSupport.get_type_name());
		bypassObjectiveTopic = TopicUtil.findOrCreateTopic(getParticipant(), ice.BypassObjectiveTopic.VALUE,
				ice.BypassObjectiveTypeSupport.class);

		bypassObjectiveReader = (ice.BypassObjectiveDataReader) subscriber.create_datareader_with_profile(
				bypassObjectiveTopic, QosProfiles.ice_library, QosProfiles.state, null, StatusKind.STATUS_MASK_NONE);

		StringSeq params = new StringSeq();
		params.add("'" + deviceIdentity.unique_device_identifier + "'");
		bypassObjectiveQueryCondition = bypassObjectiveReader.create_querycondition(
				SampleStateKind.NOT_READ_SAMPLE_STATE, ViewStateKind.ANY_VIEW_STATE,
				InstanceStateKind.ALIVE_INSTANCE_STATE, "unique_device_identifier = %0", params);
		eventLoop.addHandler(bypassObjectiveQueryCondition, new ConditionHandler() {
			private ice.BypassObjectiveSeq data_seq = new ice.BypassObjectiveSeq();
			private SampleInfoSeq info_seq = new SampleInfoSeq();

			@Override
			public void conditionChanged(Condition condition) {

				for (;;) {
					try {
						bypassObjectiveReader.read_w_condition(data_seq, info_seq,
								ResourceLimitsQosPolicy.LENGTH_UNLIMITED, (ReadCondition) condition);
						for (int i = 0; i < info_seq.size(); i++) {
							SampleInfo si = (SampleInfo) info_seq.get(i);
							ice.BypassObjective data = (BypassObjective) data_seq.get(i);
							if (si.valid_data) {
								stopThePump(data.stopBypass);
							}
						}
					} catch (RETCODE_NO_DATA noData) {
						break;
					} finally {
						bypassObjectiveReader.return_loan(data_seq, info_seq);
					}
				}
			}

		});

		ice.NumericTypeSupport.register_type(getParticipant(), ice.NumericTypeSupport.get_type_name());
		// numericTopic = getParticipant().create_topic(ice.NumericTopic.VALUE,
		// ice.NumericTypeSupport.get_type_name(), DomainParticipant.TOPIC_QOS_DEFAULT,
		// null,
		// StatusKind.STATUS_MASK_NONE);
		numeric_Writer = (ice.NumericDataWriter) getParticipant().create_datawriter_with_profile(numericTopic,
				QosProfiles.ice_library, QosProfiles.numeric_data, null, StatusKind.STATUS_MASK_NONE);

		Main.bypassStatus = bypassStatus;
		Main.bypassStatusHandle = bypassStatusHandle;
		Main.bypassStatusWriter = bypassStatusWriter;
		Main.numeric_Writer = numeric_Writer;
	}

	@Override
	public void shutdown() {
		log.info("Shutdown in progress...");

		eventLoop.removeHandler(bypassObjectiveQueryCondition);
		bypassObjectiveReader.delete_readcondition(bypassObjectiveQueryCondition);
		bypassObjectiveQueryCondition = null;

		subscriber.delete_datareader(bypassObjectiveReader);
		bypassObjectiveReader = null;

		getParticipant().delete_topic(bypassObjectiveTopic);
		bypassObjectiveTopic = null;

		bypassStatusWriter.unregister_instance(bypassStatus, bypassStatusHandle);
		bypassStatusHandle = null;

		publisher.delete_datawriter(bypassStatusWriter);
		bypassStatusWriter = null;

		getParticipant().delete_topic(bypassStatusTopic);
		bypassStatusTopic = null;

		numeric_Writer.unregister_instance(Main.flow_rate, Main.numeric_Handle);
		numeric_Writer.unregister_instance(Main.pump_velocity, Main.numeric_Handle);
		numeric_Writer.unregister_instance(Main.vol_bypassed, Main.numeric_Handle);
		numeric_Writer.unregister_instance(Main.blood_press, Main.numeric_Handle);
		numeric_Writer.unregister_instance(Main.blood_temp, Main.numeric_Handle);

		super.shutdown();
		log.info("Shutdown completed.");
	}

	@Override
	protected String iconResourceName() {
		return "CPM.jpg";
	}
}
