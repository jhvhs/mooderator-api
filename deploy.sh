#!/usr/bin/env bash
set -eu

./gradlew clean build
cf push