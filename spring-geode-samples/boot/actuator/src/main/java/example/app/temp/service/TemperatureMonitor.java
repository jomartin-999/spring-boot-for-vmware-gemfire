/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.service;

import java.util.Optional;

import org.apache.geode.cache.query.CqEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.gemfire.listener.annotation.ContinuousQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import example.app.temp.event.BoilingTemperatureEvent;
import example.app.temp.event.FreezingTemperatureEvent;
import example.app.temp.model.TemperatureReading;

// tag::class[]
@Service
@SuppressWarnings("unused")
public class TemperatureMonitor {

	private final ApplicationEventPublisher applicationEventPublisher;

	public TemperatureMonitor(ApplicationEventPublisher applicationEventPublisher) {

		Assert.notNull(applicationEventPublisher, "ApplicationEventPublisher is required");

		this.applicationEventPublisher = applicationEventPublisher;
	}

	@ContinuousQuery(name = "BoilingTemperatureMonitor",
		query = "SELECT * FROM /TemperatureReadings WHERE temperature >= 212")
	public void boilingTemperatureReadings(CqEvent event) {

		Optional.ofNullable(event)
			.map(CqEvent::getNewValue)
			.filter(TemperatureReading.class::isInstance)
			.map(TemperatureReading.class::cast)
			.map(it -> new BoilingTemperatureEvent(this, it))
			.ifPresent(this.applicationEventPublisher::publishEvent);
	}

	@ContinuousQuery(name = "FreezingTemperatureMonitor",
		query = "SELECT * FROM /TemperatureReadings WHERE temperature <= 32")
	public void freezingTemperatureReadings(CqEvent event) {

		Optional.ofNullable(event)
			.map(CqEvent::getNewValue)
			.filter(TemperatureReading.class::isInstance)
			.map(TemperatureReading.class::cast)
			.map(it -> new FreezingTemperatureEvent(this, it))
			.ifPresent(this.applicationEventPublisher::publishEvent);
	}
}
// end::class[]
