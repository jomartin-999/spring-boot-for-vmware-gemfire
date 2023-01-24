/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.geode.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;
import org.springframework.geode.config.annotation.UseGroups;
import org.springframework.geode.config.annotation.UseMemberName;

import example.app.temp.event.BoilingTemperatureEvent;
import example.app.temp.event.FreezingTemperatureEvent;
import example.app.temp.event.TemperatureEvent;
import example.app.temp.model.TemperatureReading;
import example.app.temp.service.TemperatureMonitor;

// tag::class[]
@SpringBootApplication
@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = TemperatureReading.class)
@UseGroups("TemperatureMonitors")
@UseMemberName("TemperatureMonitoringService")
@SuppressWarnings("unused")
public class BootGeodeClientApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(BootGeodeClientApplication.class)
			.web(WebApplicationType.SERVLET)
			.build()
			.run(args);
	}

	@Bean
	TemperatureMonitor temperatureMonitor(ApplicationEventPublisher applicationEventPublisher) {
		return new TemperatureMonitor(applicationEventPublisher);
	}

	@EventListener(classes = { BoilingTemperatureEvent.class, FreezingTemperatureEvent.class })
	public void temperatureEventHandler(TemperatureEvent temperatureEvent) {

		System.err.printf("%1$s TEMPERATURE READING [%2$s]%n",
			temperatureEvent instanceof BoilingTemperatureEvent ? "HOT" : "COLD",
				temperatureEvent.getTemperatureReading());
	}
}
// end::class[]
