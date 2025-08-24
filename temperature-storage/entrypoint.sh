#!/bin/ash
echo "Starting application: currently JAVA_OPS: ${JAVA_OPTS}"
exec java ${JAVA_OPTS} -jar temperature-storage.jar