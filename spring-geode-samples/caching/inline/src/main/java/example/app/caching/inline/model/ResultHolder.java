/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Abstract Data Type (ADT) and persistent entity modeling the results of a mathematical calculation.
 *
 * @author John Blum
 * @see java.io.Serializable
 * @see javax.persistence.Entity
 * @see javax.persistence.IdClass
 * @see javax.persistence.Table
 * @since 1.1.0
 */
// tag::class[]
@Entity
@Getter
@IdClass(ResultHolder.ResultKey.class)
@EqualsAndHashCode(of = { "operand", "operator" })
@RequiredArgsConstructor(staticName = "of")
@Table(name = "Calculations")
public class ResultHolder implements Serializable {

	@Id @NonNull
	private Integer operand;

	@Id
	@NonNull
	@Enumerated(EnumType.STRING)
	private Operator operator;

	@NonNull
	private Integer result;

	protected ResultHolder() { }

	@Override
	public String toString() {
		return getOperator().toString(getOperand(), getResult());
	}

	@Getter
	@EqualsAndHashCode
	@RequiredArgsConstructor(staticName = "of")
	public static class ResultKey implements Serializable {

		@NonNull
		private Integer operand;

		@NonNull
		private Operator operator;

		protected ResultKey() { }

	}
}
// end::class[]
