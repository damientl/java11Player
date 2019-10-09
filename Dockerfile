FROM maven:3.6.2-jdk-11-slim
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN mvn clean install
RUN java -version
RUN java --module-path target/player-app-1.0-SNAPSHOT.jar --module com.development.playerapp/com.development.playerapp.PlayerApp