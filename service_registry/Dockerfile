FROM eclipse-temurin:17-jdk
WORKDIR /logistics/service_registry
COPY /target/service_registry-0.0.1-SNAPSHOT.jar  /logistics/service_registry/service_registry-0.0.1-SNAPSHOT.jar
EXPOSE 8761
CMD ["java","-jar","service_registry-0.0.1-SNAPSHOT.jar"]