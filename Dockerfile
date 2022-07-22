FROM openjdk:17-alpine

ARG USER_GRUOP_NAME=currencyExchange
ARG USER_NAME=eureka

RUN addgroup -S ${USER_GRUOP_NAME} && adduser -S ${USER_NAME} -G ${USER_GRUOP_NAME}
USER ${USER_NAME}:${USER_GRUOP_NAME}

COPY /target/Bulletin-board-API.jar .
ENTRYPOINT ["java","-jar", "Bulletin-board-API.jar"]