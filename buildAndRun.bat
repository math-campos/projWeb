@echo off
call mvn clean package
call docker build -t br.edu.ifsul/SIstemaMedicoWeb .
call docker rm -f SIstemaMedicoWeb
call docker run -d -p 9080:9080 -p 9443:9443 --name SIstemaMedicoWeb br.edu.ifsul/SIstemaMedicoWeb