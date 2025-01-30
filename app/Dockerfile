# Usando o JDK 17 para construir
FROM openjdk:17-jdk-slim AS build
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Certifica-se de que o script mvnw tenha permissão de execução
RUN chmod +x ./mvnw

EXPOSE 8080

# Realiza o build da aplicação
RUN ./mvnw clean package -DskipTests

# Usando o mesmo JDK 17 para a execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR da fase de build
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
