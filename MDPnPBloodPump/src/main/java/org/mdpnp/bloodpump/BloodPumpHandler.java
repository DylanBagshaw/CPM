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

import ice.InfusionObjective;
import ice.InfusionStatusDataWriter;

public class BloodPumpHandler extends AbstractSimulatedConnectedDevice {

	private static final Logger log = LoggerFactory.getLogger(BloodPumpHandler.class);

	public static ice.InfusionStatus infusionStatus = (ice.InfusionStatus) ice.InfusionStatus.create();
	public static InstanceHandle_t infusionStatusHandle;

	public static ice.InfusionStatusDataWriter infusionStatusWriter;
	public static ice.InfusionObjectiveDataReader infusionObjectiveReader;
	public static QueryCondition infusionObjectiveQueryCondition;
	public static Topic infusionStatusTopic, infusionObjectiveTopic;

	public static String id = "";

	public class MyHandler extends Main {

	}

	private final MyHandler handler = new MyHandler();

	protected void writeIdentity() {
		deviceIdentity.manufacturer = "MDPnP";
		deviceIdentity.model = "Blood Pump";
		id = deviceIdentity.unique_device_identifier;
		writeDeviceIdentity();
	}

	protected void stopThePump(boolean stopThePump) {
		handler.setInterlockStop(stopThePump);
	}

	public BloodPumpHandler(Subscriber subscriber, Publisher publisher, EventLoop eventLoop) {
		super(subscriber, publisher, eventLoop);

		writeIdentity();

		ice.InfusionStatusTypeSupport.register_type(getParticipant(), ice.InfusionStatusTypeSupport.get_type_name());
		infusionStatusTopic = TopicUtil.findOrCreateTopic(domainParticipant, ice.InfusionStatusTopic.VALUE,
				ice.InfusionStatusTypeSupport.class);
		infusionStatusWriter = (InfusionStatusDataWriter) publisher.create_datawriter_with_profile(infusionStatusTopic,
				QosProfiles.ice_library, QosProfiles.state, null, StatusKind.STATUS_MASK_NONE);

		infusionStatus.unique_device_identifier = deviceIdentity.unique_device_identifier;
		infusionStatusHandle = infusionStatusWriter.register_instance(infusionStatus);

		infusionStatus.drug_name = "Blood";
		infusionStatus.drug_mass_mcg = 0;
		infusionStatus.solution_volume_ml = 0;
		infusionStatus.infusion_duration_seconds = 0;
		infusionStatus.infusion_fraction_complete = 0;
		infusionStatus.infusionActive = false;
		infusionStatus.volume_to_be_infused_ml = 0;

		infusionStatusWriter.write(infusionStatus, infusionStatusHandle);

		ice.InfusionObjectiveTypeSupport.register_type(getParticipant(),
				ice.InfusionObjectiveTypeSupport.get_type_name());
		infusionObjectiveTopic = TopicUtil.findOrCreateTopic(getParticipant(), ice.InfusionObjectiveTopic.VALUE,
				ice.InfusionObjectiveTypeSupport.class);

		infusionObjectiveReader = (ice.InfusionObjectiveDataReader) subscriber.create_datareader_with_profile(
				infusionObjectiveTopic, QosProfiles.ice_library, QosProfiles.state, null, StatusKind.STATUS_MASK_NONE);

		StringSeq params = new StringSeq();
		params.add("'" + deviceIdentity.unique_device_identifier + "'");
		infusionObjectiveQueryCondition = infusionObjectiveReader.create_querycondition(
				SampleStateKind.NOT_READ_SAMPLE_STATE, ViewStateKind.ANY_VIEW_STATE,
				InstanceStateKind.ALIVE_INSTANCE_STATE, "unique_device_identifier = %0", params);
		eventLoop.addHandler(infusionObjectiveQueryCondition, new ConditionHandler() {
			private ice.InfusionObjectiveSeq data_seq = new ice.InfusionObjectiveSeq();
			private SampleInfoSeq info_seq = new SampleInfoSeq();

			@Override
			public void conditionChanged(Condition condition) {

				for (;;) {
					try {
						infusionObjectiveReader.read_w_condition(data_seq, info_seq,
								ResourceLimitsQosPolicy.LENGTH_UNLIMITED, (ReadCondition) condition);
						for (int i = 0; i < info_seq.size(); i++) {
							SampleInfo si = (SampleInfo) info_seq.get(i);
							ice.InfusionObjective data = (InfusionObjective) data_seq.get(i);
							if (si.valid_data) {
								stopThePump(data.stopInfusion);
							}
						}
					} catch (RETCODE_NO_DATA noData) {
						break;
					} finally {
						infusionObjectiveReader.return_loan(data_seq, info_seq);
					}
				}
			}

		});

		Main.infusionStatus = infusionStatus;
		Main.infusionStatusHandle = infusionStatusHandle;
		Main.infusionStatusWriter = infusionStatusWriter;
	}

	@Override
	public void shutdown() {
		log.info("Shutdown in progress...");

		eventLoop.removeHandler(infusionObjectiveQueryCondition);
		infusionObjectiveReader.delete_readcondition(infusionObjectiveQueryCondition);
		infusionObjectiveQueryCondition = null;

		subscriber.delete_datareader(infusionObjectiveReader);
		infusionObjectiveReader = null;

		getParticipant().delete_topic(infusionObjectiveTopic);
		infusionObjectiveTopic = null;

		infusionStatusWriter.unregister_instance(infusionStatus, infusionStatusHandle);
		infusionStatusHandle = null;

		publisher.delete_datawriter(infusionStatusWriter);
		infusionStatusWriter = null;

		getParticipant().delete_topic(infusionStatusTopic);
		infusionStatusTopic = null;

		super.shutdown();
		log.info("Shutdown completed.");
	}

	@Override
	protected String iconResourceName() {
		return "CPM.jpg";
	}

}
