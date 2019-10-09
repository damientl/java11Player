# Player Application

## I've prepared three modes to run the aplication:
- docker
  
      Prerequisites: Docker installed
- windows
  
      Prerequisites: Java 11 and Maven 3.3.9+ installed
- linux 
  
      Prerequisites: Java 11 and Maven 3.3.9+ installed

## Docker run:
docker build -t my-java-app .

Just this commands above creates the image and runs the application showing the results.
To runs again for any changes, run the following:

docker run -it --rm -v <project root path>:/usr/src/myapp -w /usr/src/myapp my-java-app /bin/bash

For example:

docker run -it --rm -v C:\Users\damien.lutz\Documents\projeto\playerApp:/usr/src/myapp -w /usr/src/myapp my-java-app /bin/bash

Inside bash you can run again:
mvn clean install
java --module-path target/player-app-1.0-SNAPSHOT.jar --module com.development.playerapp/com.development.playerapp.PlayerApp


## Windows run:
Inside project root folder, run:
script/run.bat

This will:
 - set JAVA environment variables and
 - install the application with maven and run the generated .jar file 

## Linux run:
Inside project root folder, run:
./script/run.sh

This will install the application with maven and run the generated .jar file 