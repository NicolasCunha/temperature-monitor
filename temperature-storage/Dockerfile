FROM gradle:8.10.1-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Project metadata to avoid 0.0.1-SNAPSHOT
ARG PROJ_NAME=temperature-storage
ARG PROJ_VERSION=0.0.1-SNAPSHOT

#ENV JAVA_OPTS="-Dhttp.proxyHost=${PROXY_HOST} -Dhttp.proxyPort=${PROXY_PORT} -Dhttps.proxyHost=${PROXY_HOST} -Dhttps.proxyPort=${PROXY_PORT} -Dhttp.nonProxyHosts=*.philips.com"
#ENV JAVA_TOOL_OPTIONS="-Dhttp.proxyHost=${PROXY_HOST} -Dhttp.proxyPort=${PROXY_PORT} -Dhttps.proxyHost=${PROXY_HOST} -Dhttps.proxyPort=${PROXY_PORT} -Dhttp.nonProxyHosts=*.philips.com"

# Output env and args variables

RUN echo "PROXY_HOST: ${PROXY_HOST}"
RUN echo "PROXY_PORT: ${PROXY_PORT}"
RUN echo "PROJ_NAME: ${PROJ_NAME}"
RUN echo "PROJ_VERSION: ${PROJ_VERSION}"
RUN echo "JAVA_OPTS: ${JAVA_OPTS}"
RUN echo "JAVA_TOOL_OPTIONS: ${JAVA_TOOL_OPTIONS}"

# Build the project and create the jar file
RUN echo "Building project..."
RUN gradle build --no-daemon --refresh-dependencies --info -Pversion=${PROJ_VERSION}
RUN echo "Finish!"

RUN echo "Renaming JAR file ${PROJ_NAME}-${PROJ_VERSION}.jar to ${PROJ_NAME}.jar"
RUN mv /home/gradle/src/build/libs/${PROJ_NAME}-${PROJ_VERSION}.jar /home/gradle/src/build/libs/${PROJ_NAME}.jar
RUN echo "Finish!"

RUN ls -a

FROM amazoncorretto:21-alpine

VOLUME /tmp

COPY --from=build /home/gradle/src/build/libs/*.jar .

ARG JAVA_OPS="-XX:+UseParallelGC -verbose:gc -Xms256m -Xmx512m"

ENV JAVA_OPS=${JAVA_OPS}

COPY --chmod=0755 ./entrypoint.sh .

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]