/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.security.client.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

/**
 * An Abstract Data Type (ADT) modeling a {@literal Customer}.
 *
 * @author John Blum
 * @see Id
 * @see Region
 * @since 1.0.0
 */
// tag::class[]
@Region("Customers")
@EqualsAndHashCode
@ToString(of = "name")
@RequiredArgsConstructor(staticName = "newCustomer")
public class Customer {

	@Id @NonNull @Getter
	private Long id;

	@NonNull @Getter
	private String name;

}
// end::class[]
