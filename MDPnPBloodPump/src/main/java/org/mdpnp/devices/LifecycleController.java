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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 */
public class LifecycleController implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(LifecycleController.class);

	// added type arguments to List and ArrayList
	private List<?> managedBeans = new ArrayList<Object>();
	private String startMethodName = "start";
	private ApplicationContext applicationContext;

	// added type arguments to List
	public void setManagedBeans(List<?> managedBeans) {
		this.managedBeans = managedBeans;
	}

	public void setStartMethod(String startMethodName) {
		this.startMethodName = startMethodName;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		// not my context...
		if (contextRefreshedEvent != null && contextRefreshedEvent.getSource() != applicationContext)
			return;

		log.info(
				"Got ContextRefreshedEvent from " + (applicationContext == null ? "????" : applicationContext.getId()));

		int count = 0;

		for (Object bean : managedBeans) {

			try {
				Method m = BeanUtils.findMethod(bean.getClass(), startMethodName);
				m.invoke(bean);
				count++;
			} catch (Exception ex) {
				log.error("Failed to call " + bean.getClass() + "::" + startMethodName);
			}
		}

		log.info("Successfully called " + startMethodName + " on " + count + " beans");

	}
}
