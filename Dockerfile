FROM eclipse-temurin:17.0.12_7-jdk-alpine
LABEL authors="kys9808@pusan.ac.kr"

COPY ./build/libs/kr.starbuckslike-0.0.1-SNAPSHOT.jar springSystem.jar

ENTRYPOINT ["java","-jar","springSystem.jar"]
