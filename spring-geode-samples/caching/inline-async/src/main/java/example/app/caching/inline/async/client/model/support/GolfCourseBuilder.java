/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.client.model.support;

import example.app.caching.inline.async.client.model.GolfCourse;

/**
 * A {@literal Builder} class used to build {@link GolfCourse golf courses}.
 *
 * @author John Blum
 * @see example.app.caching.inline.async.client.model.GolfCourse
 * @since 1.4.0
 */
public abstract class GolfCourseBuilder {

	/**
	 * Builds the {@literal Augusta National} {@link GolfCourse}, home of the {@literal Masters} major golf tournament.
	 *
	 * @return a new instance of {@link GolfCourse} modeling {@literal Augusta National} in Augusta, GA; USA.
	 * @see example.app.caching.inline.async.client.model.GolfCourse
	 */
	public static GolfCourse buildAugustaNational() {

		return GolfCourse.newGolfCourse("Augusta National")
			.withHole(1, 4)
			.withHole(2, 5)
			.withHole(3, 4)
			.withHole(4, 3)
			.withHole(5, 4)
			.withHole(6, 3)
			.withHole(7, 4)
			.withHole(8, 5)
			.withHole(9, 4)
			.withHole(10, 4)
			.withHole(11, 4)
			.withHole(12, 3)
			.withHole(13, 5)
			.withHole(14, 4)
			.withHole(15, 5)
			.withHole(16, 3)
			.withHole(17, 4)
			.withHole(18, 4);
	}
}
