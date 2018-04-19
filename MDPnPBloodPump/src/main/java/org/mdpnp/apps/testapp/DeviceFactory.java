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

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.TreeSet;

import org.mdpnp.devices.AbstractDevice;
import org.mdpnp.devices.DeviceDriverProvider;
import org.mdpnp.devices.DeviceDriverProvider.SpringLoadedDriver;
import org.mdpnp.bloodpump.BloodPumpHandler;
import org.mdpnp.rtiapi.data.EventLoop;
import org.springframework.context.support.AbstractApplicationContext;

import com.rti.dds.publication.Publisher;
import com.rti.dds.subscription.Subscriber;

public class DeviceFactory {

	static DeviceDriverProvider[] getAvailableDevices() {
		ServiceLoader<DeviceDriverProvider> l = ServiceLoader.load(DeviceDriverProvider.class);

		Collection<DeviceDriverProvider> all = new TreeSet<DeviceDriverProvider>(
				new Comparator<DeviceDriverProvider>() {
					@Override
					public int compare(DeviceDriverProvider o1, DeviceDriverProvider o2) {
						return o1.getDeviceType().toString().compareTo(o2.getDeviceType().toString());
					}
				});

		final Iterator<DeviceDriverProvider> iter = l.iterator();
		while (iter.hasNext()) {
			all.add(iter.next());
		}

		DeviceDriverProvider[] arr = all.toArray(new DeviceDriverProvider[all.size()]);
		return arr;
	}

	public static DeviceDriverProvider getDeviceDriverProvider(String alias) {
		ServiceLoader<DeviceDriverProvider> l = ServiceLoader.load(DeviceDriverProvider.class);
		final Iterator<DeviceDriverProvider> iter = l.iterator();
		while (iter.hasNext()) {
			DeviceDriverProvider ddp = iter.next();
			if (alias.equals(ddp.getDeviceType().getAlias()))
				return ddp;
		}
		throw new IllegalArgumentException("Cannot resolve '" + alias + " to a known device");
	}

	public static class CardioPulmonaryPumpProvider extends SpringLoadedDriver {

		@Override
		public DeviceType getDeviceType() {
			return new DeviceType(ice.ConnectionType.Network, "MD PnP", "CP Pump", "MD PnP CP Pump", 1);
		}

		@Override
		public AbstractDevice newInstance(AbstractApplicationContext context) throws Exception {
			EventLoop eventLoop = context.getBean("eventLoop", EventLoop.class);
			Subscriber subscriber = context.getBean("subscriber", Subscriber.class);
			Publisher publisher = context.getBean("publisher", Publisher.class);
			return new BloodPumpHandler(subscriber, publisher, eventLoop);
		}
	}
}
