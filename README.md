# Mortgage-web
### Description
A webb application built on Spring Boot with some plain java logic in the back-end which 
calculates the monthly payment on a mortgage.</br>
Available Dockerfile to run it on standalone docker container.

## Running the app

All instructions below assume that you already have cloned the git repository locally.

### IDE instructions
Start your favourite IDE and run `MortgageWebApplication`, Intellij recommended as IDE.

### JAR instruction
To run the jar file you will either have to give the full directory path to the jar file or navigate to the project root folder to shorten the command.

Either `java -jar <dir_path>/mortgage-web-0.0.1-SNAPSHOT.jar` or just</br> `java -jar mortgage-web-0.0.1-SNAPSHOT.jar` if your in project root folder.

You can move `mortgage-web-0.0.1-SNAPSHOT.jar` to any location you want but `prospects.txt` have to exist in the same folder as the jar file for the application to be able to read it.


### Docker instruction
These instructions assume you have Docker installed to docker installer in a powershell/bash terminal.

Navigate to the mortgage-web project root folder.

`cd <your path>\mortgage-web`

When your in the project root folder you will need to build a docker image first, replace `<image_name>` with your own name that you wish to use for the image.

`docker build -t <image_name>`

Now invoke the application inside a container, `<image_name>` the name you gave your image and replace `<new_container_name>` with your own container name that you wish to use.

`docker run -p 8080:8080 --name <new_container_name> <image_name>`

The application will now be reachable on localhost:8080 but remember to stop the container if you are just testing this application
since the container is publishing itself on your host machines port 8080 and will do so until you stop it.
If your running Docker Desktop then this can be done under *Containers*, else do `docker stop <container_id_or_name>`.

### Modifications to input data file *prospects.txt*

If you run the application through an IDE or using the jar file then any changes to the file will be read when a new request is sent to the server.
However, if you're running the application on a docker container and insert more data or change it then you will have to rebuild
the docker image and container.

To remove an image and a container, first stop the container as described above, then you can remove the container using `docker rm <container_id_or_name>`
and then</br> `docker rmi <image_id_or_name>`.

Redo `docker build -t <image_name>` and then `docker run -p 8080:8080 --name <new_container_name> <image_name>` and you will now have updated data in your container.