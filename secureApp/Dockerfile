
FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000


COPY secureApp/target/classes /usrapp/bin/classes
COPY secureApp/target/dependency /usrapp/bin/dependency

COPY secureApp/keystores /usrapp/bin/secureApp/keystores
COPY secureService/keystores /usrapp/bin/secureService/keystores


CMD ["java","-cp","./classes:./dependency/*","co.edu.eci.ieti.secureapp.SecureSparkServiceApp"]
