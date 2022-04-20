FROM openjdk:11
ADD target/tijani-blog.jar tijani-blog.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tijani-blog.jar"]

