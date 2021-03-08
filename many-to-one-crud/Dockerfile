# Running stage: the part that is used for running the application
FROM amazoncorretto:15
RUN yum -y install shadow-utils which
COPY target/*.jar /usr/app/app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8080
WORKDIR /usr/app
CMD java -jar app.jar
