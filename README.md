# Temperature Monitor

Simple project to showcase usage of Kafka events between two different services.

## Project structure
- [Temperature Reader](/temperature-reader/) registers temperatures and publishes this event to Kafka.
- [Temperature Storage](/temperature-storage/) reads the temperature registered and persists them on a Postgres database.

## RESTful API

Temperature Reader exposes an endpoint that allow creating a temperature reading, such as:

```json
{
  "temperature" : 15.0,
  "format" : "CELSIUS"
}
```

While Temperature Storage lists those readings.

To create and list temperature readings, you can use the Postman Collection as an example.

Further documentation is on Swagger, which is available at <code>/swagger-ui.html</code>

## Technologies

- Java 21
- Kafka/ZooKeeper
- AKHQ (to monitor Kafka topics)
- Postgres
- PgAdmin (to monitor Postgres database)
- Docker (for building and running containers)

## Running on IDE

Both services can be run on any Java IDE that supports Gradle. Kafka and Postgres are required dependencies and can be configured on the respective services "application.yml" files.

Both required dependencies can be started using the [docker compose file](/docker-compose-dev.yml):

```bash
docker compose -f docker-compose-dev.yml up -d
```

By default, this will expose the services on the following ports:

| Service | Port |
| ------- | ---- |
| Postgres | 5432 |
| PgAdmin4 | 16543 |
| Kafka | 9092 |
| ZooKeeper | 2181 |
| AKHQ | 1900 |

By default, the temperature services will expose the following ports:

| Service | Port |
| ------- | ---- |
| temperature-reader | 8900 |
| temperature-storage | 9000 |

And by default, this will set the following environment variables ([dev.env](dev.env)):

| Variable                                | Value                                                      |
|-----------------------------------------|------------------------------------------------------------|
| POSTGRES_USER                           | root                                                       |
| POSTGRES_PASSWORD                       | root                                                       |
| POSTGRES_DB                             |                                                            |
| PGADMIN_DEFAULT_EMAIL                   | admin@temperature.com                                      |
| PGADMIN_DEFAULT_PASSWORD                | admin                                                      |
| KAFKA_BROKER_ID                         | 1                                                          |
| KAFKA_ZOOKEEPER_CONNECT                 | zookeeper:2181                                             |
| KAFKA_LISTENERS                         | PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092    |
| KAFKA_ADVERTISED_LISTENERS              | PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092    |
| KAFKA_LISTENER_SECURITY_PROTOCOL_MAP    | PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT               |
| KAFKA_INTER_BROKER_LISTENER_NAME        | PLAINTEXT                                                  |
| KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR  | 1                                                          |

## Running on Docker with local containers

Services can be built and run alongside the required dependencies using the following command:

```bash
docker compose -f docker-compose-homolog.yml up -d
```

And by default, this will set the following environment variables ([homolog.env](homolog.env)):

| Variable                                | Value                                                      |
|-----------------------------------------|------------------------------------------------------------|
| POSTGRES_USER                           | root                                                       |
| POSTGRES_PASSWORD                       | root                                                       |
| POSTGRES_DB                             |                                                            |
| PGADMIN_DEFAULT_EMAIL                   | admin@temperature.com                                      |
| PGADMIN_DEFAULT_PASSWORD                | admin                                                      |
| KAFKA_BROKER_ID                         | 1                                                          |
| KAFKA_ZOOKEEPER_CONNECT                 | zookeeper:2181                                             |
| KAFKA_LISTENERS                         | PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092    |
| KAFKA_ADVERTISED_LISTENERS              | PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092    |
| KAFKA_LISTENER_SECURITY_PROTOCOL_MAP    | PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT               |
| KAFKA_INTER_BROKER_LISTENER_NAME        | PLAINTEXT                                                  |
| KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR  | 1                                                          |
| TEMPERATURE_READER_SERVER_PORT          | 8900                                                       |
| TEMPERATURE_STORAGE_SERVER_PORT         | 9000                                                       |
| KAFKA_URL                               | kafka:9092                                                 |
| POSTGRES_URL                            | jdbc:postgresql://postgres:5432/temperature-monitor        |

Ports can be changed freely, but make sure these changes are reflected on the docker-compose file.
