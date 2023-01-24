/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.service;

import java.io.PrintStream;
import java.util.PrimitiveIterator;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import example.app.temp.model.TemperatureReading;
import example.app.temp.repo.TemperatureReadingRepository;

// tag::class[]
@Service
@SuppressWarnings("unused")
public class TemperatureSensor {

	private final PrimitiveIterator.OfInt temperatureStream =
		new Random(System.currentTimeMillis())
			.ints(-100, 400)
			.iterator();

	private final TemperatureReadingRepository repository;

	public TemperatureSensor(TemperatureReadingRepository repository) {

		Assert.notNull(repository, "TemperatureReadingRepository is required");

		this.repository = repository;
	}

	@Scheduled(fixedRateString = "${example.app.temp.sensor.reading.schedule.rate:1000}")
	public void readTemperature() {

		TemperatureReading temperatureReading =
			TemperatureReading.newTemperatureReading(temperatureStream.nextInt());

		this.repository.save(log(temperatureReading));
	}

	private TemperatureReading log(TemperatureReading temperatureReading) {

		PrintStream out = temperatureReading.isNormal() ? System.out : System.err;

		out.printf("TEMPERATURE READING [%s]%n", temperatureReading);

		return temperatureReading;
	}
}
// end::class[]
