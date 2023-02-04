FROM openjdk:18
WORKDIR /app
COPY /target/mortgage-web-0.0.1-SNAPSHOT.jar mortgage-web-0.0.1-SNAPSHOT.jar
COPY prospects.txt prospects.txt

ENTRYPOINT ["java","-jar", "mortgage-web-0.0.1-SNAPSHOT.jar"]