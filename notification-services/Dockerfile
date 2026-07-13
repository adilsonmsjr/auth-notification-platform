# 1. Escolha uma imagem que venha com o Maven e o Java instalados
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# 2. Copie o arquivo de configuração e baixe as dependências (para usar o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 3. Copie o seu código fonte e compile o projeto gerando o .jar
COPY src ./src
RUN mvn clean package -DskipTests

# 4. Escolha uma imagem que tenha APENAS o Java para rodar (sem o peso do Maven)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 5. Copie o .jar gerado lá na Etapa 1 para dentro desta nova imagem
COPY --from=build /app/target/*.jar app.jar

# 6. Defina o comando que inicia o seu microsserviço
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]