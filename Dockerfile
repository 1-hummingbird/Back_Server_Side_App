FROM eclipse-temurin:17-jre-alpine
LABEL authors="kys9808@pusan.ac.kr"

COPY ./build/libs/kr.starbuckslike-0.0.2-SNAPSHOT.jar springSystem.jar

ENTRYPOINT ["java","-jar","springSystem.jar"]
