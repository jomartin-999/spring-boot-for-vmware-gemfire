/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.temp.geode.server;

import java.util.Optional;

import org.apache.geode.cache.GemFireCache;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.gemfire.IndexFactoryBean;
import org.springframework.data.gemfire.PeerRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableStatistics;
import org.springframework.data.gemfire.config.annotation.RegionConfigurer;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.geode.config.annotation.UseGroups;
import org.springframework.scheduling.annotation.EnableScheduling;

import example.app.temp.model.TemperatureReading;
import example.app.temp.repo.TemperatureReadingRepository;
import example.app.temp.service.TemperatureSensor;

// tag::class[]
@SpringBootApplication
@CacheServerApplication(name = "TemperatureServiceServer")
@EnableEntityDefinedRegions(basePackageClasses = TemperatureReading.class)
@EnableGemfireRepositories(basePackageClasses = TemperatureReadingRepository.class)
@EnableScheduling
@EnableStatistics
@UseGroups("TemperatureSensors")
@SuppressWarnings("unused")
public class BootGeodeServerApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(BootGeodeServerApplication.class)
			.web(WebApplicationType.SERVLET)
			.build()
			.run(args);
	}

	@Bean
	TemperatureSensor temperatureSensor(TemperatureReadingRepository repository) {
		return new TemperatureSensor(repository);
	}

	@Bean
	RegionConfigurer temperatureReadingsConfigurer() {

		return new RegionConfigurer() {

			@Override
			public void configure(String beanName, PeerRegionFactoryBean<?, ?> regionBean) {

				Optional.ofNullable(beanName)
					.filter("TemperatureReadings"::equals)
					.ifPresent(it -> regionBean.setStatisticsEnabled(true));
			}
		};
	}

	@Bean
	@DependsOn("TemperatureReadings")
	IndexFactoryBean temperatureReadingTemperatureIndex(GemFireCache gemfireCache) {

		IndexFactoryBean temperatureTimestampIndex = new IndexFactoryBean();

		temperatureTimestampIndex.setCache(gemfireCache);
		temperatureTimestampIndex.setExpression("temperature");
		temperatureTimestampIndex.setFrom("/TemperatureReadings");
		temperatureTimestampIndex.setName("TemperatureReadingTemperatureIdx");

		return temperatureTimestampIndex;
	}

	@Bean
	@DependsOn("TemperatureReadings")
	IndexFactoryBean temperatureReadingTimestampIndex(GemFireCache gemfireCache) {

		IndexFactoryBean temperatureTimestampIndex = new IndexFactoryBean();

		temperatureTimestampIndex.setCache(gemfireCache);
		temperatureTimestampIndex.setExpression("timestamp");
		temperatureTimestampIndex.setFrom("/TemperatureReadings");
		temperatureTimestampIndex.setName("TemperatureReadingTimestampIdx");

		return temperatureTimestampIndex;
	}
}
// end::class[]
