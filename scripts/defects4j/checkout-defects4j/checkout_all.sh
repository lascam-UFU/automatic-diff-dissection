#!/usr/bin/env bash

export OUTPUT_DIR="/tmp/defects4j-checkout"
export BUGGY_VERSION_DIR=$OUTPUT_DIR/"buggy-versions"
export FIXED_VERSION_DIR=$OUTPUT_DIR/"fixed-versions"

SCRIPT_DIR="$(dirname "${BASH_SOURCE[0]}")"

for bug in $(seq 1 26); do $SCRIPT_DIR/checkout_bug.sh Chart $bug; done
for bug in $(seq 1 133); do $SCRIPT_DIR/checkout_bug.sh Closure $bug; done
for bug in $(seq 1 65); do $SCRIPT_DIR/checkout_bug.sh Lang $bug; done
for bug in $(seq 1 106); do $SCRIPT_DIR/checkout_bug.sh Math $bug; done
for bug in $(seq 1 27); do $SCRIPT_DIR/checkout_bug.sh Time $bug; done
for bug in $(seq 1 38); do $SCRIPT_DIR/checkout_bug.sh Mockito $bug; done
