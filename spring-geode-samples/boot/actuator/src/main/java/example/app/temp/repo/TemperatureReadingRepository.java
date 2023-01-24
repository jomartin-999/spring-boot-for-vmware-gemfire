/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.repo;

import java.util.List;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;

import example.app.temp.model.TemperatureReading;

@SuppressWarnings("unused")
// tag::class[]
public interface TemperatureReadingRepository extends CrudRepository<TemperatureReading, Long> {

	List<TemperatureReading> findByTimestampGreaterThanAndTimestampLessThan(
		Long timestampLowerBound, Long timestampUpperBound);

	@Query("SELECT count(*) FROM /TemperatureReadings WHERE temperature >= 212")
	Integer countBoilingTemperatureReadings();

	@Query("SELECT count(*) FROM /TemperatureReadings WHERE temperature <= 32")
	Integer countFreezingTemperatureReadings();

}
// end::class[]
