#!/bin/bash
cd /home/ec2-user
aws s3 cp s3://mortgage-assignment-bucket/mortgage-web-0.0.1-SNAPSHOT.jar .
java -jar mortgage-web-0.0.1-SNAPSHOT.jar