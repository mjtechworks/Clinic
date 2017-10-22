FROM java:8

ADD ./build/libs/doctor-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/doctor-service-1.0-SNAPSHOT.jar"]

EXPOSE 7000