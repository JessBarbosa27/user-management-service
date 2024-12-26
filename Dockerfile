FROM openjdk:21

EXPOSE 55001

ADD target/user-management-service.jar user-management-service.jar

ENTRYPOINT ["java", "jar", "/user-management-service.jar"]