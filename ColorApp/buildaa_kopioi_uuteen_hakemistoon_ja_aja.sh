#!/bin/bash

kohde="testx_$(date +%s)"

mkdir -p "$kohde" && \
	mvn package && \
	cp target/ColorApp-1.0-SNAPSHOT-shaded.jar "${kohde}/" && \
	cd "$kohde" && \
	java -jar ColorApp-1.0-SNAPSHOT-shaded.jar
