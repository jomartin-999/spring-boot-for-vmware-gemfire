/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.client.model.support;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.gemfire.util.ArrayUtils;
import org.springframework.util.StringUtils;

import example.app.caching.inline.async.client.model.Golfer;

/**
 *  A {@literal Builder} class used to build {@link Golfer Golfers}.
 *
 * @author John Blum
 * @see example.app.caching.inline.async.client.model.Golfer
 * @since 1.4.0
 */
public abstract class GolferBuilder {

	public static final String[] FAVORITE_GOLFER_NAMES = {
		"Arnold Palmer",
		"Ben Hogan",
		"Bobby Jones",
		"Tiger Woods",
		"Rory McIlroy",
		"Dustin Johnson",
		"Jason Day",
		"Justin Thomas",
		"John Rahm",
		"Jordan Spieth",
		"Phil Michelson",
		"Ricky Fowler"
	};

	public static Set<Golfer> buildGolfers(String... names) {

		return Arrays.stream(ArrayUtils.nullSafeArray(names, String.class))
			.filter(StringUtils::hasText)
			.map(Golfer::newGolfer)
			.collect(Collectors.toSet());
	}
}
