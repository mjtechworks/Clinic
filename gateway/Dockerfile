FROM java:8

ADD ./target/gateway-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-1.0-SNAPSHOT.jar"]

EXPOSE 4000