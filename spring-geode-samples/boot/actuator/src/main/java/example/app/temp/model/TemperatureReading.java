/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// tag::class[]
@Data
@Region("TemperatureReadings")
@RequiredArgsConstructor(staticName = "newTemperatureReading")
@SuppressWarnings("unused")
public class TemperatureReading {

	private static final int BOILING_TEMPERATURE = 212;
	private static final int FREEZING_TEMPERATURE = 32;

	@Id
	private Long timestamp = System.currentTimeMillis();

	@NonNull
	private final Integer temperature;

	@Transient
	public boolean isBoiling() {

		Integer temperature = getTemperature();

		return temperature != null && temperature >= BOILING_TEMPERATURE;
	}

	@Transient
	public boolean isFreezing() {

		Integer temperature = getTemperature();

		return temperature != null && temperature <= FREEZING_TEMPERATURE;
	}

	@Transient
	public boolean isNormal() {
		return !(isBoiling() || isFreezing());
	}

	@Override
	public String toString() {
		return String.format("%d °F", getTemperature());
	}
}
// end::class[]
