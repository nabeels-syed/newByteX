FROM openjdk:15
LABEL maintainer ahnabeel@sheridancollege.ca
COPY target/HelloWorld-0.0.1-SNAPSHOT.jar helloworld.jar
ENTRYPOINT ["java", "-jar", "helloworld.jar"]