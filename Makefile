DOCKER_REPO := atomarkin/studentapi

# Define variable for image tag, using an environment variable with a default
IMAGE_TAG ?= latest

.PHONY: build build-db run-db migrate-db build-app run-app lint test-app test-app-ci

# Build the Docker containers
build:
	docker compose build

# Run the database container only
run-db:
	docker compose up -d db

# Run migrations using the application
migrate-db:
	docker compose run app flyway migrate

# Build the application container
build-app:
	docker compose build app

# Run the full application stack
run-app:
	docker compose up

# Lint the application code
lint:
	mvn checkstyle:check

# Run application tests
test-app:
	mvn test

# Run Docker image
docker-run:
	docker run -d -p 8080:8080 $(DOCKER_REPO):$(IMAGE_TAG)

# Build Docker image
docker-build:
	docker build -t $(DOCKER_REPO):$(IMAGE_TAG) .

# Push Docker image
docker-push:
	docker push $(DOCKER_REPO):$(IMAGE_TAG)

# Build and push Docker image
docker-build-push: docker-build docker-push