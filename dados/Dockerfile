FROM eclipse-temurin:11
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/desafiobtg-dados.jar
WORKDIR /app
ENTRYPOINT java -jar desafiobtg-dados.jar