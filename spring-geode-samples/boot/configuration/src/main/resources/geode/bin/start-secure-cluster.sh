#!/bin/bash

#
# Copyright (c) VMware, Inc. 2023. All rights reserved.
# SPDX-License-Identifier: Apache-2.0
#

gfsh -e "run --file=@samples-dir@/boot/configuration/build/resources/main/geode/bin/start-secure-cluster.gfsh"
