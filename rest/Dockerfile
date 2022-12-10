FROM eclipse-temurin:11
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/desafiobtg-rest.jar
WORKDIR /app
ENTRYPOINT java -jar desafiobtg-rest.jar