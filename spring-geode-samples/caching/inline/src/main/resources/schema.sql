/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

CREATE TABLE IF NOT EXISTS calculations (
	operand INTEGER NOT NULL,
  	operator VARCHAR(256) NOT NULL,
	result INTEGER NOT NULL,
  	PRIMARY KEY (operand, operator)
);
