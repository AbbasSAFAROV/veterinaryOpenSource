FROM adoptopenjdk/openjdk11
WORKDIR app
COPY ./target/veterinary-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80
CMD ["java","-jar","app.jar"]