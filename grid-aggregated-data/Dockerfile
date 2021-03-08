# Running stage: the part that is used for running the application
FROM amazoncorretto:15
RUN curl -sL https://rpm.nodesource.com/setup_15.x | bash -
RUN yum -y install shadow-utils which nodejs
COPY target/*.jar /usr/app/app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8080
WORKDIR /usr/app
CMD java -jar app.jar
