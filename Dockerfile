FROM eclipse-temurin:17

RUN useradd appuser
USER appuser

VOLUME /tmp

env SERVER_PORT 8080
EXPOSE 8770

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT java -cp app:app/lib/* \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    ${JAVA_OPTS} \
    com.dazo.emailcorporativo.EmailCorporativoApplication