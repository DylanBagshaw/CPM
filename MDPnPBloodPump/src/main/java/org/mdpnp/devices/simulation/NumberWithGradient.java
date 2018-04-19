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
package org.mdpnp.devices.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mfeinberg
 *
 *         A mutable number that given a 'start' and 'end' values gradually
 *         settles and the 'end' value advancing towards it with every call to
 *         intValue()/longValue/etc APIs
 *
 */
public class NumberWithGradient extends Number {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(NumberWithGradient.class);

	final Number delegate;
	final Number gradient;
	final Number targetValue;
	final Number startValue;
	Number currentValue = null;

	public NumberWithGradient(Number startValue, Number targetValue, Number gradient) {

		if (startValue instanceof NumberWithGradient) {
			NumberWithGradient other = (NumberWithGradient) startValue;
			this.startValue = other.currentValue == null ? other.startValue : other.currentValue;
		} else
			this.startValue = startValue;

		this.delegate = targetValue;
		this.targetValue = makeTarget(targetValue);
		this.gradient = gradient;

		if (this.startValue == null)
			throw new IllegalArgumentException("start value cannot be null");
		if (this.gradient == null)
			throw new IllegalArgumentException("gradient value cannot be null");
		if (this.delegate == null)
			throw new IllegalArgumentException("delegate value cannot be null");
	}

	@Override
	public String toString() {
		return "NumberWithGradient{" + "delegate=" + delegate + ", gradient=" + gradient + ", targetValue="
				+ targetValue + ", startValue=" + startValue + ", currentValue=" + currentValue + '}';
	}

	@Override
	public int intValue() {
		return next().intValue();
	}

	@Override
	public long longValue() {
		return next().longValue();
	}

	@Override
	public float floatValue() {
		return next().floatValue();
	}

	@Override
	public double doubleValue() {
		return next().doubleValue();
	}

	private final Number next() {
		if (currentValue == null) {
			currentValue = startValue;
			return currentValue;
		}
		return makeNext();
	}

	protected Number makeTarget(Number target) {
		return target.doubleValue();
	}

	protected Number makeNext() {
		double toRet = currentValue.doubleValue();
		double delta = targetValue.doubleValue() - toRet;
		// we are at the target level ?
		if (Math.abs(delta) < gradient.doubleValue()) {
			currentValue = delegate.doubleValue();
		} else {
			delta = Math.min(gradient.doubleValue(), delta);
			delta = Math.max(-gradient.doubleValue(), delta);
			currentValue = toRet + delta;
		}
		return currentValue;
	}
}
