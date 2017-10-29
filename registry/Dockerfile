FROM java:8

ADD ./build/libs/registry-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry-1.0-SNAPSHOT.jar"]

EXPOSE 7777