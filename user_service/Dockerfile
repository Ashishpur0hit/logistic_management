FROM eclipse-temurin:17-jdk
WORKDIR /logistics/user
COPY /target/user_service-0.0.1-SNAPSHOT.jar  /logistics/user/user_service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
CMD ["java","-jar","user_service-0.0.1-SNAPSHOT.jar"]