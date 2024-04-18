## Define variable
#SPRING_BOOT_RUN = mvn spring-boot:run
#
## Define targets
#.PHONY: run
#run:
#	@$(SPRING_BOOT_RUN)

#DOCKER_IMAGE_NAME := studentapi:v1.0.0
#
#.PHONY: build run
#
#build:
#	docker build -t $(DOCKER_IMAGE_NAME) .
#
#run:
#	docker run -d -p 8080:8080 $(DOCKER_IMAGE_NAME)


.PHONY: build-db run-db migrate-db build-app run-app lint test-app

build:
	docker compose build

run-db:
	docker compose up -d db

migrate-db:
	docker compose run app flyway migrate

build-app:
	docker compose build app

run-app:
	docker compose up

lint:
	mvn checkstyle:check

test-app:
	mvn test
