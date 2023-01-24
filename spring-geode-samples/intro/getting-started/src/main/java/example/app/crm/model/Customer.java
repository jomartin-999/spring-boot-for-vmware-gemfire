/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

/**
 * Abstract Data Type (ADT) modeling a {@link Customer}.
 *
 * @author John Blum
 * @see org.springframework.data.annotation.Id
 * @see org.springframework.data.gemfire.mapping.annotation.Region
 * @since 1.2.0
 */
// tag::class[]
@Region("Customers")
@Data
@ToString(of = "name")
@NoArgsConstructor
@AllArgsConstructor(staticName = "newCustomer")
public class Customer {

	@Id
	private Long id;

	private String name;

}
// end::class[]
