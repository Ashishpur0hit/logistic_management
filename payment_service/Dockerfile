FROM eclipse-temurin:17-jdk
WORKDIR /logistics/payment
COPY /target/payment_service-0.0.1-SNAPSHOT.jar  /logistics/payment/payment_service-0.0.1-SNAPSHOT.jar
EXPOSE 8083
CMD ["java","-jar","payment_service-0.0.1-SNAPSHOT.jar"]