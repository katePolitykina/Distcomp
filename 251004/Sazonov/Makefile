SERVICE=api

MAIN=./cmd/${SERVICE}
EXECUTABLE=./bin/${SERVICE}
IMAGE_NAME=distcomp/${SERVICE}
DOCKER_COMPOSE_ENV_FILE=./deploy/docker/.env.dev
TERRAFORM_ENV_FILE=./deploy/terraform/.env.dev

build:
	CGO_ENABLED=0 go build -o ${EXECUTABLE} ${MAIN}
.PHONY: build

run: build
	${EXECUTABLE}
.PHONY: run

docker-build:
	docker build -t "${IMAGE_NAME}" .
.PHONY: docker-build

docker-up:
	docker compose --env-file "${DOCKER_COMPOSE_ENV_FILE}" up -d
.PHONY: docker-up

docker-down:
	docker compose --env-file "${DOCKER_COMPOSE_ENV_FILE}" down
.PHONY: docker-down

tf-up:
	zenv -f ${TERRAFORM_ENV_FILE} terraform apply
.PHONY: tf-up

tf-plan:
	zenv -f ${TERRAFORM_ENV_FILE} terraform plan
.PHONY: tf-plan

tf-down:
	zenv -f ${TERRAFORM_ENV_FILE} terraform destroy
.PHONY: tf-down

unit-test:
	go test -v $$(go list ./... | grep -v "mocks|tests")
.PHONY: unit-test

integration-test:
	zenv -f ./tests/integration/.env -- go test -v $$(go list ./... | grep "tests/integration")
.PHONY: integration-test

gen-swag-docs:
	swag init --dir ./internal -g ./handler/http/handler.go
.PHONY: gen-swag-docs
