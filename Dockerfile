
FROM java:8




EXPOSE 8080

# Copy your fat jar to the container
ADD build/distributions/as-monolithic.tar.gz /as-monolithic

# Launch the verticle
ENV WORKDIR /as-monolithic/run-bin
ENTRYPOINT ["sh", "-c"]
CMD ["cd $WORKDIR ; ./monolithic.sh"]