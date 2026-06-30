FROM eclipse-temurin:17-jdk
WORKDIR /logistics/shipment
COPY /target/shipment_service-0.0.1-SNAPSHOT.jar  /logistics/shipment/shipment_service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
CMD ["java","-jar","shipment_service-0.0.1-SNAPSHOT.jar"]