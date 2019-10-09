@echo off
echo Setting JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.2
echo setting PATH
set PATH=%JAVA_HOME%\bin;%PATH%
echo Display java version
java -version
call mvn clean install
java --module-path target/player-app-1.0-SNAPSHOT.jar --module com.development.playerapp/com.development.playerapp.PlayerApp