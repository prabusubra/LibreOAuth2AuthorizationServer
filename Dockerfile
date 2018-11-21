FROM openjdk:8-alpine
COPY target/LibreOAuth2AuthorizationServer.jar LibreOAuth2AuthorizationServer.jar
EXPOSE 8010
CMD ["java", "-jar", "LibreOAuth2AuthorizationServer.jar"]
