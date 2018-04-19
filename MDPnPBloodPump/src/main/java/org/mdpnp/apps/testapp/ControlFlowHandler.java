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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * I cannot stand mixing UI code with headless controllers like database
 * engines. Yes, sometimes there is a need for user feedback when running in
 * interactive mode. So here comes this little utility that would work as a
 * bridge between the two worlds.
 */
public abstract class ControlFlowHandler {

	private static final Logger log = LoggerFactory.getLogger(ControlFlowHandler.class);

	public abstract boolean confirm(String header, String message, boolean defaultCode);

	public abstract boolean confirmError(String header, String message, boolean defaultCode);

	public static class HeadlessControlFlowHandler extends ControlFlowHandler {
		@Override
		public boolean confirm(String header, String message, boolean defaultCode) {
			log.warn(header + "\n" + message);
			return defaultCode;
		}

		@Override
		public boolean confirmError(String header, String message, boolean defaultCode) {
			log.warn(header + "\n" + message);
			return defaultCode;
		}
	}

	public static class UIControlFlowHandler extends ControlFlowHandler {
		@Override
		public boolean confirm(String header, String message, boolean defaultCode) {
			log.warn(header + "\n" + message);
			return DialogUtils.OkCancelDialog(header, message);
		}

		@Override
		public boolean confirmError(String header, String message, boolean defaultCode) {
			log.error(header + "\n" + message);
			message = message + "\n\n'OK' to continue, 'Cancel' to terminate the application";
			return DialogUtils.OkCancelDialog(header, message);
		}
	}

	@SuppressWarnings("serial")
	public static class ConfirmedError extends IllegalStateException {
		public ConfirmedError(String message, Throwable cause) {
			super(message, cause);
		}

		public ConfirmedError(String s) {
			super(s);
		}
	}
}
