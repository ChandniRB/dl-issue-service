# dl-issue-service
Driving Licence application for demonstrating microservice architecture and communication between microservices.

The application comprises of two services:
1. [dl-issue-service](https://github.com/ChandniRB/dl-issue-service) - for applying DL, generating DL and checking application status
2. [dl-test-service](https://github.com/ChandniRB/dl-test-service) - for checking test slot availability, submitting test result


## Prerequisites
1. Java version 17
2. Maven version 3.8.8
3. Kafka 2.13

## Setup

1. Create tables in Postgres:
```
CREATE TABLE IF NOT EXISTS public.dl_application
(
    applicationid text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    mobile text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default",
    test_date date,
    test_slot text COLLATE pg_catalog."default",
    CONSTRAINT dl_application_pkey PRIMARY KEY (applicationid)
)

```

```
CREATE TABLE IF NOT EXISTS public.dl_issued
(
    dl_no text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    mobile text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default",
    CONSTRAINT dl_issued_pkey PRIMARY KEY (dl_no)
)

```

```
CREATE TABLE IF NOT EXISTS public.dlno_applicationid
(
    dlno text COLLATE pg_catalog."default",
    applicationid text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dlno_applicationid_pkey PRIMARY KEY (applicationid)
)

```

2. Install and start Kafka:

```
./bin/zookeeper-server-start.sh config/zookeeper.properties

./bin/kafka-server-start.sh config/server.properties
```

3. Set environment variables `DB_IP`, `DB_PORT`, `DB_NAME_DL`, `DB_USER`, `DB_PASSWORD`, `KAFKA_HOST`

4. `mvn clean install`

5. `docker build -t dl-issue-service:latest .`

6. `docker run --network host -p 8000:8000 dl-issue-service:latest .`



## Run

1. Apply for DL:
```
http://localhost:8000/dl/apply
```

2. Check application status
```
http://localhost:8000/dl/status/<applicationId>
```
