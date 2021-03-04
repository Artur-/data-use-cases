# Running stage: the part that is used for running the application
FROM amazoncorretto:15
RUN yum -y install shadow-utils which
COPY target/*.jar /usr/app/app.jar
COPY node_modules/chance /usr/app/node_modules/chance
COPY node_modules/.bin /usr/app/node_modules/.bin
RUN useradd -m myuser
USER myuser
EXPOSE 8080
WORKDIR /usr/app
CMD java -jar app.jar
