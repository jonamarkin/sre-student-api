#!/bin/bash

# Stop on error
set -e

#Install docker function
install_docker() {
    # Update the apt package index
    apt-get update

    # Install packages to allow apt to use a repository over HTTPS
    apt-get install -y apt-transport-https ca-certificates curl software-properties-common

    # Add Docker’s official GPG key
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

    # Set up the stable repository
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

    # Update the apt package index
    apt-get update

    # Install the latest version of Docker CE
    apt-get install -y docker-ce

    # Add the vagrant user to the docker group
    usermod -aG docker vagrant

    # Enable Docker to start on boot
    systemctl enable docker
}

# Install Docker Compose
install_docker_compose() {
    # Download the latest version of Docker Compose
    echo "Downloading the latest version of Docker Compose"
    curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
}

install_make(){
  echo "Installing make"
  apt-get install -y make
}

main(){
  install_docker
  install_docker_compose
  install_make
  echo "Provisioning complete"
}

main