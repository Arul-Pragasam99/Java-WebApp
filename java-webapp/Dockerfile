FROM eclipse-temurin:17-jre-alpine
ARG app_version=1.0.0
 
RUN echo "building version $app_version"
ENV PORT=8080
 
WORKDIR /app
COPY pom.xml ./
RUN chown -R root:root /app
USER root
COPY . .
HEALTHCHECK CMD wget -qO- http://localhost:8080 || exit 1
VOLUME ["/app/logs"]
EXPOSE 8080
ENTRYPOINT [ "java" ]
CMD ["-jar", "java-webapp-1.0.0.jar"]
STOPSIGNAL SIGTERM
 
 