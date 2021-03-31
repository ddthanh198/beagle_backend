FROM maven:3.6.3-openjdk-15
RUN mkdir /beagle
WORKDIR /beagle
COPY . .
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]
