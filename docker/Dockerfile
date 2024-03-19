# Use a imagem oficial do OpenJDK como base
FROM openjdk:17

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o jar da sua aplicação para o contêiner
ADD boratroca-mark-1.jar app.jar

# Exponha a porta em que sua aplicação Spring Boot está sendo executada
EXPOSE 8080

# Comando para iniciar sua aplicação quando o contêiner for iniciado
CMD ["java", "-jagr", "app.jar"]