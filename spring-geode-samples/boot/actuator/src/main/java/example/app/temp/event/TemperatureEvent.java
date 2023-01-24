/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import example.app.temp.model.TemperatureReading;

// tag::class[]
public class TemperatureEvent extends ApplicationEvent {

	@Getter
	private final TemperatureReading temperatureReading;

	public TemperatureEvent(Object source, TemperatureReading temperatureReading) {

		super(source);

		this.temperatureReading = temperatureReading;
	}
}
// end::class[]
