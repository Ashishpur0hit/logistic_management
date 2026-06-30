FROM eclipse-temurin:17-jdk
WORKDIR /logistics/gateway
COPY /target/api-gateway-0.0.1-SNAPSHOT.jar /logistics/gateway/api-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8084
CMD ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]