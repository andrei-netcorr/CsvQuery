FROM adoptopenjdk/openjdk16:x86_64-alpine-jre-16.0.1_9
RUN cd / \
    && mkdir csvquery
COPY target/csvquery.jar /csvquery

# --spring.data.mongodb.host=csvquery_mongodb
EXPOSE 8080
WORKDIR /csvquery
ENTRYPOINT ["java", "-server", "-noverify", "-Xmx2048M", "-jar", "csvquery.jar"]
