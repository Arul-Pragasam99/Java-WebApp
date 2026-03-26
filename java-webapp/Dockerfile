FROM maven:3.9.5-eclipse-temurin-17
ARG app_version=1.0.0
 
RUN echo "building version $app_version"
ENV PORT=8080
 
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline -q
COPY . .
RUN mvn clean package -DskipTests -q
RUN chown -R root:root /app
USER root
HEALTHCHECK CMD wget -qO- http://localhost:8080 || exit 1
VOLUME ["/app/logs"]
EXPOSE 8080
ENTRYPOINT [ "java" ]
CMD ["-jar", "target/java-webapp-1.0.0.jar"]
STOPSIGNAL SIGTERM