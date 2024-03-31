## Define variable
#SPRING_BOOT_RUN = mvn spring-boot:run
#
## Define targets
#.PHONY: run
#run:
#	@$(SPRING_BOOT_RUN)

DOCKER_IMAGE_NAME := studentapi:v1.0.0

.PHONY: build run

build:
	docker build -t $(DOCKER_IMAGE_NAME) .

run:
	docker run -d -p 8080:8080 $(DOCKER_IMAGE_NAME)
