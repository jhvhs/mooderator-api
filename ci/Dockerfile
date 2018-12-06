FROM ubuntu:16.04

# Packages
RUN DEBIAN_FRONTEND=noninteractive apt-get update && apt-get install -y \
  curl \
  wget \
  jq 

WORKDIR /tmp/docker-build

# Golang
ENV GOLANG_VERSION=1.11
RUN wget -q https://storage.googleapis.com/golang/go${GOLANG_VERSION}.linux-amd64.tar.gz && \
  tar -C /usr/local -xzf go${GOLANG_VERSION}.linux-amd64.tar.gz && rm go${GOLANG_VERSION}.linux-amd64.tar.gz
ENV GOPATH /root/go
RUN mkdir -p /root/go/bin
ENV PATH $PATH:/usr/local/go/bin:$GOPATH/bin
