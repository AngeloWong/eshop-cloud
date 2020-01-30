FROM java:8
# MAINTAINER Angelo
LABEL maintainer=Angelo
VOLUME /tmp
ADD hystrix-turbine-server-1.0-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8770
