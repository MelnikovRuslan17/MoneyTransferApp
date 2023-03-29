FROM openjdk:19-jdk-alpine
EXPOSE 5500
ADD target/MoneyTransferApp-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java", "-jar", "/myapp.jar"]