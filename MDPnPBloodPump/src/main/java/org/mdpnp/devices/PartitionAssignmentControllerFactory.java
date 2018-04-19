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
package org.mdpnp.devices;

import java.util.concurrent.ScheduledExecutorService;

import org.mdpnp.rtiapi.data.EventLoop;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.publication.Publisher;
import com.rti.dds.subscription.Subscriber;

import ice.DeviceIdentity;

/**
 *
 */
public class PartitionAssignmentControllerFactory
		implements FactoryBean<PartitionAssignmentController>, DisposableBean {

	private PartitionAssignmentController instance;

	private ScheduledExecutorService executor;
	private EventLoop eventLoop;
	private Subscriber subscriber;
	private Publisher publisher;
	private DeviceIdentity deviceIdentity;
	private DomainParticipant domainParticipant;
	private String partitionFileName = null;

	@Override
	public PartitionAssignmentController getObject() throws Exception {
		if (instance == null) {
			String f = partitionFileName;
			if (f == null || f.length() == 0)
				instance = new PartitionAssignmentController(deviceIdentity, domainParticipant, eventLoop, publisher,
						subscriber);
			else
				instance = new PartitionAssignmentController.PersistentPartitionAssignment(executor, f, deviceIdentity,
						domainParticipant, eventLoop, publisher, subscriber);
		}
		instance.start();
		return instance;
	}

	public PartitionAssignmentControllerFactory(ScheduledExecutorService executor, DomainParticipant domainParticipant,
			EventLoop eventLoop, Publisher publisher, Subscriber subscriber, DeviceIdentity deviceIdentity) {
		this(executor, domainParticipant, eventLoop, publisher, subscriber, deviceIdentity, null);
	}

	public PartitionAssignmentControllerFactory(ScheduledExecutorService executor, DomainParticipant domainParticipant,
			EventLoop eventLoop, Publisher publisher, Subscriber subscriber, DeviceIdentity deviceIdentity,
			String partitionFileName) {
		this.executor = executor;
		this.domainParticipant = domainParticipant;
		this.eventLoop = eventLoop;
		this.publisher = publisher;
		this.subscriber = subscriber;
		this.deviceIdentity = deviceIdentity;
		this.partitionFileName = partitionFileName;
	}

	@Override
	public Class<?> getObjectType() {
		return PartitionAssignmentController.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		if (instance != null) {
			instance.shutdown();
		}
	}
}
