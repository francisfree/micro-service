FROM public.ecr.aws/docker/library/eclipse-temurin:17-jre-alpine
RUN apk add --no-cache tzdata
ENV TZ=Africa/Nairobi
RUN apk --no-cache add curl
WORKDIR /opt/app
RUN mkdir "certificates"
RUN addgroup --system -g 2000 javauser && adduser -S -s /usr/sbin/nologin -G javauser -u 2000 javauser
COPY target/*.jar /opt/app/app.jar
RUN chown -R javauser:javauser .
USER javauser
ENTRYPOINT ["java","-jar","app.jar"]