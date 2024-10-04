# Use uma imagem oficial do Maven para compilar o projeto
FROM maven:latest AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e outros arquivos de configuração necessários
COPY pom.xml .

# Baixe as dependências do Maven (isso ajuda a aproveitar o cache do Docker)
RUN mvn dependency:go-offline -B

# Copie o código-fonte do projeto
COPY src ./src

# Compile o projeto
RUN mvn clean package -DskipTests

# Use uma imagem mais leve para executar o aplicativo
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho para a imagem final
WORKDIR /app

# Copie o JAR construído da imagem de compilação
COPY --from=build /app/target/BoraTrocarBackend-3.0.6.jar .

EXPOSE 8080

# Defina o comando padrão para executar o aplicativo
CMD ["java", "-jar", "BoraTrocarBackend-3.0.6.jar"]
