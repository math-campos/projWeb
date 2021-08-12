#!/bin/sh
mvn clean package && docker build -t br.edu.ifsul/SIstemaMedicoWeb .
docker rm -f SIstemaMedicoWeb || true && docker run -d -p 9080:9080 -p 9443:9443 --name SIstemaMedicoWeb br.edu.ifsul/SIstemaMedicoWeb