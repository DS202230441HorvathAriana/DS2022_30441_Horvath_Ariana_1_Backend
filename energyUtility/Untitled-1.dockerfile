FROM openjdk:8
ADD target/energyUtility-0.0.1-SNAPSHOT.jar energyUtility-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "energyUtility-0.0.1-SNAPSHOT.jar"]