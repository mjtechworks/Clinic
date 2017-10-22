FROM java:8

ADD ./build/libs/drug-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/drug-service-1.0-SNAPSHOT.jar"]

EXPOSE 7500