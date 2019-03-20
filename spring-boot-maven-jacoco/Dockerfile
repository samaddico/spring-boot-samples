FROM openjdk:8-jdk-alpine
VOLUME /tmp
MAINTAINER Samuel Addico <samaddico@gmail.com>
ARG JAR_FILE
COPY $JAR_FILE /usr/local/share/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urando","-jar","/usr/local/share/app.jar"]
EXPOSE 8080
