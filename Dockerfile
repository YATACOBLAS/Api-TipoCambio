FROM openjdk:8-alpine

#WORKDIR /app

##COPY ./pom.xml .
#COPY ./.mvn ./.mvn
#COPY ./mvnw .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#COPY ./src ./src

#RUN ./mvnw clean package -DskipTests

COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

#CMD ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
CMD ["java","-jar","app.jar"]
