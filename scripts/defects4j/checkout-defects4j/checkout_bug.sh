#!/usr/bin/env bash

project=$1
bug=$2

BUG_DIR=$BUGGY_VERSION_DIR/${project,,}/${project,,}_${bug}
mkdir -p $BUG_DIR
defects4j checkout -p $project -v ${bug}b -w $BUG_DIR
PATCH_DIR=$FIXED_VERSION_DIR/${project,,}/${project,,}_${bug}
mkdir -p $PATCH_DIR
defects4j checkout -p $project -v ${bug}f -w $PATCH_DIR
