FROM docker:git
WORKDIR /action
COPY ./entrypoint.sh .
RUN chmod +x ./entrypoint.sh
ENTRYPOINT ["/action/entrypoint.sh"]
