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

/**
 * @author mfeinberg
 *
 *         A mutable number with value that fluctuates within a range with steps
 *         in small random increments. It is intended to be used to simulate a
 *         source of physical signal that would change in small deltas, but
 *         could drift over a broad spectrum over time.
 */
public class NumberWithJitter<T> extends Number {

	private final Number increment;
	private final Number floor;
	private final Number ceil;
	private final Number initialValue;
	private Number currentValue;

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

	synchronized Number next() {
		double diff = (increment.doubleValue() - (2 * increment.doubleValue() * Math.random()));
		double nextValue = currentValue.doubleValue() + diff;
		if (nextValue < floor.doubleValue() || nextValue > ceil.doubleValue()) {
			nextValue = currentValue.doubleValue() - diff;
		}
		Number toRet = currentValue;
		currentValue = nextValue;
		return toRet;
	}

	@Override
	public String toString() {
		return "NumberWithJitter{" + "increment=" + increment + ", floor=" + floor + ", ceil=" + ceil
				+ ", initialValue=" + initialValue + ", currentValue=" + currentValue + '}';
	}

	@SuppressWarnings("unchecked")
	T getIncrement() {
		return (T) increment;
	}

	@SuppressWarnings("unchecked")
	T getFloor() {
		return (T) floor;
	}

	@SuppressWarnings("unchecked")
	T getCeil() {
		return (T) ceil;
	}

	@SuppressWarnings("unchecked")
	T getInitialValue() {
		return (T) initialValue;
	}

	@SuppressWarnings("unchecked")
	T getCurrentValue() {
		return (T) currentValue;
	}

	/**
	 * Create a number with initialValue being in the middle between upper/lower
	 * bounds
	 */
	public NumberWithJitter(Number initialValue, Number increment, Number maxDelta) {
		this(initialValue, increment, initialValue.doubleValue() - maxDelta.doubleValue(),
				initialValue.doubleValue() + maxDelta.doubleValue());
	}

	public NumberWithJitter(Number initialValue, Number increment, Number floor, Number ceil) {
		this.increment = increment;
		this.ceil = ceil;
		this.floor = floor;
		this.initialValue = initialValue;
		this.currentValue = initialValue;
	}
}
