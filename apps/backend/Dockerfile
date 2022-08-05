# Gradle version of this: https://spring.io/guides/topicals/spring-boot-docker/#_multi_stage_build
FROM azul/zulu-openjdk-alpine:11.0.5 as build
WORKDIR /workspace/app/backend/

COPY gradlew .
COPY gradle gradle
COPY settings.gradle /workspace/app/backend/
COPY kpi-visualization kpi-visualization

ENV GRADLE_OPTS -Dorg.gradle.daemon=false -Dorg.gradle.internal.launcher.welcomeMessageEnabled=false 

RUN ./gradlew clean build -x test

ENV JAVA_HOME=/usr/lib/jvm/zulu11-ca

RUN mkdir -p kpi-visualization/build/dependency && (cd kpi-visualization/build/dependency; $JAVA_HOME/bin/jar -xf ../libs/*.jar)

RUN ls -lart /workspace/app/backend/
RUN ls -lart /workspace/app/backend/kpi-visualization/
RUN ls -lart /workspace/app/backend/kpi-visualization/build/
RUN ls -lart /workspace/app/backend/kpi-visualization/build/libs/
RUN ls -lart /workspace/app/backend/kpi-visualization/build/dependency

FROM azul/zulu-openjdk-alpine:11.0.5-jre
VOLUME /tmp
ENV DEPENDENCY=/workspace/app/backend/kpi-visualization/build/dependency
COPY --from=build $DEPENDENCY/BOOT-INF/lib /app/backend/lib
COPY --from=build $DEPENDENCY/META-INF /app/backend/META-INF
COPY --from=build $DEPENDENCY/BOOT-INF/classes /app/backend
EXPOSE 7890
ENTRYPOINT ["java", "-cp", "app/backend:app/backend/lib/*", "in.co.ad.springbootredissample.SpringBootRedisSampleApplication"]
