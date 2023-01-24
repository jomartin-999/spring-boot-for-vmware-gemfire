/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package example.app.temp.event;

import example.app.temp.model.TemperatureReading;

@SuppressWarnings("unused")
public class FreezingTemperatureEvent extends TemperatureEvent {

	public FreezingTemperatureEvent(Object source, TemperatureReading temperatureReading) {
		super(source, temperatureReading);
	}
}
