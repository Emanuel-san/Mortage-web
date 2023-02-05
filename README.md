# Mortgage-web
### Description
A webb application built on Spring Boot with some plain java in the back-end which 
calculates the monthly payment on a mortgage.</br>
Available Dockerfile to run it on standalone docker container.

Preview of the app [here](http://ec2-34-237-0-254.compute-1.amazonaws.com:8080) running on AWS EC2 instance.

## Running the app

### Before you start
After cloning the repository you will have to do an initial packaging except if you're running it in your IDE. </br>You can either do it by using maven CLI command `mvn package` if you have it installed
or your IDE maven packing tool.

### JAR

![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) <b>You have to add `prospects.txt` to the maven build folder `/target` if you run the app with the jar file since the app looks for the file in root folder it's running from. </b>![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)

To run the app navigate to the project root folder in a bash/powershell terminal.

Start the app with command `java -jar target/mortgage-web-0.0.1-SNAPSHOT.jar`.
The app will now be reachable on http://localhost:8080.

You can move `mortgage-web-0.0.1-SNAPSHOT.jar` to any location you want but `prospects.txt` have to exist in the same folder as the jar file.


### Docker
These instructions assume you have Docker installed to run docker commands in a powershell/bash terminal.
</br>See [docker docs](https://docs.docker.com/get-docker/) for install instructions.


Navigate to the mortgage-web project root folder.

`cd <dir_path>\mortgage-web`

Now build a docker image, replace `<image_name>` with your own name that you wish to use for the image.

`docker build -t <image_name> .`

The Dockerfile will make sure you always build the latest version of the app from the maven build folder.

Now invoke the application inside a container, `<image_name>` the name you gave your image and replace `<new_container_name>` with your own container name that you wish to use.

`docker run -p 8080:8080 --name <new_container_name> <image_name>`

The application will now be reachable on http://localhost:8080 but remember to stop the container if you are just testing this application
since the container is publishing itself on your host machines port 8080 and will do so until you stop it.
If your running Docker Desktop then this can be done under *Containers*, else do `docker stop <container_id_or_name>`.

### Invoke the app on an AWS EC2 container
Prerequisite to the following instructions below:
* [AWS CLI installed](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
* [Have EC2 SSH key pair setup](https://docs.aws.amazon.com/ground-station/latest/ug/create-ec2-ssh-key-pair.html)
* [Have access key to start an EC2 instance](https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html#access-keys-about)
* [Have a VPC setup](https://docs.aws.amazon.com/vpc/latest/userguide/what-is-amazon-vpc.html)
* [Creating a security group with port 8080 and SSH port open](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/working-with-security-groups.html#creating-security-group)
* [Create an S3 bucket](https://docs.aws.amazon.com/AmazonS3/latest/userguide/creating-bucket.html)
* [Creating an EC2 instance with Amazon Linux](https://docs.aws.amazon.com/efs/latest/ug/gs-step-one-create-ec2-resources.html)

When creating the EC2 instance, make sure it is on a public subnet, else it won't be accessible from the outside. Enable Auto-assign public IP and choose your security group.

[Connect to your EC2 instance using ssh](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AccessingInstancesLinux.html) and install [Amazon Corretto 18](https://docs.aws.amazon.com/corretto/latest/corretto-18-ug/generic-linux-install.html#rpm-linux-install-instruct).

After installation is complete open a new command prompt and transfer the JAR file and *prospects.txt* [using native secure copy (SCP)](https://docs.aws.amazon.com/managedservices/latest/appguide/qs-file-transfer.html) with the following commands(*from project root folder*).
</br>`scp .\mortgage-web-0.0.1-SNAPSHOT.jar ec2-user@<your_ec2_instance_ip>:/home/ec2-user`</br></br>
`scp .\prospects.txt ec2-user@<your_ec2_instance_ip>:/home/ec2-user`

Switch back to the command prompt with the ssh connection to your EC2 instance and run the JAR file `java -jar target/mortgage-web-0.0.1-SNAPSHOT.jar`

The Spring Boot app will start and you can now test for a response on your local machine with `curl <you_ec2_instance_ip>:8080` or use your browser to navigate to the ec2 instance and see the app front-end.

[Create an AMI image](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/create-ami.html) of your instance, then you'll always have an instance with the java jdk installed when starting a new EC2 instance using that image.

Upload a fresh JAR file and `prospects.txt` to you s3 bucket, replace `<your_s3_bucket_name>` with your own bucket name that you created.</br>
`aws s3 cp target/mortgage-web-0.0.1-SNAPSHOT.jar s3://<your_s3_bucket_name>/mortgage-web-0.0.1-SNAPSHOT.jar`</br></br>
`aws s3 cp prospects.txt s3://mortgage-assignment-bucket/prospects.txt`</br>

Instead of creating a new shell script use `startup.sh` included with the project and modify the bucket name `mortgage-assignment-bucket`
to your own.

You now have a complete pipeline where you can modify the app or input data file and upload them to your s3 bucket and start a new EC2 instance that will download the latest version from your
bucket on start up using the shell script

[Start an EC2 instance](https://docs.aws.amazon.com/cli/latest/userguide/cli-services-ec2-instances.html#launching-instances) from your image using the startup shell script.

`aws ec2 run-instances` </br>
`--image-id ami-xxxx`</br>
`--count 1`</br>
`--instance-type t2.micro`</br>
`--key-name my-ssh-key`</br>
`--subnet-id subnet-xxxx`</br>
`--security-group-ids sg-xxxx`</br>
`--user-data file://startup.sh`</br>
`--associate-public-ip-address`

See [AWS CLI Command Reference](https://docs.aws.amazon.com/cli/latest/reference/ec2/run-instances.html) for documentation on each option.

You now have an EC2 instance reachable on port 8080 running your latest version and data.


### Modifications to input data file *prospects.txt* (IDE, JAR, DOCKER)

If you run the application through an IDE or using the JAR file then any changes to the file will be read when a new request is sent to the server.
However, if you're running the application on a docker container and insert more data or change it then you will have to rebuild
the docker image and container.

To remove an image and a container, first stop the container as described above, then you can remove the container using `docker rm <container_id_or_name>`
and then</br> `docker rmi <image_id_or_name>`.

Redo `docker build -t <image_name>` and then `docker run -p 8080:8080 --name <new_container_name> <image_name>` and you will now have updated data in your container.



