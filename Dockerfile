# ESTÁGIO 1: O Estágio de Build (ambiente de compilação)
# Usa uma imagem com Maven e JDK para compilar o código
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom.xml para que o Maven baixe as dependências
# Isso otimiza o cache do Docker, que não precisará baixar tudo novamente
# a cada mudança no código-fonte
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Executa o build para criar o .jar na pasta 'target'
RUN mvn clean package

# --------------------------------------------------------------------------

# ESTÁGIO 2: A Imagem Final (ambiente de execução)
# Usa uma imagem leve com apenas o JRE (Java Runtime Environment)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia o arquivo .jar compilado no estágio 'build' para a imagem final
COPY --from=build /app/target/api-turst-*.jar ./app.jar

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

#Antes era ->

# Stage 2: The Final Stage (Production)
#FROM eclipse-temurin:21-jdk-alpine
#COPY target/api-turst-*.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
