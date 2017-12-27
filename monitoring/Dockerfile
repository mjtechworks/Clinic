FROM java:8

ADD ./build/libs/monitoring-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/monitoring-1.0-SNAPSHOT.jar"]

EXPOSE 8989 8080