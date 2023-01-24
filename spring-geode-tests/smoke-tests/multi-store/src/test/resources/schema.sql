/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

CREATE TABLE IF NOT EXISTS contacts (
  	name VARCHAR(256) PRIMARY KEY,
  	email_address VARCHAR(256),
  	phone_number VARCHAR(256)
);
