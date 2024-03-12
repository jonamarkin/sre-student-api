# Define variable
SPRING_BOOT_RUN = mvn spring-boot:run

# Define targets
.PHONY: run
run:
	@$(SPRING_BOOT_RUN)

