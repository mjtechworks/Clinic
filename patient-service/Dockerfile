FROM java:8

ADD ./build/libs/patient-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/patient-service-1.0-SNAPSHOT.jar"]

EXPOSE 8000