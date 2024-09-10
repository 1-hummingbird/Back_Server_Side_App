FROM eclipse-temurin:17.0.12_7-jdk-alpine
LABEL authors="kys9808@pusan.ac.kr"

COPY ./build/libs/<spring boot app jar file name>.jar <target file name>.jar

ENTRYPOINT ["java","-jar","<target file name>.jar"]