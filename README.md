# Time Travel
> Space-time travel machine!

The project is build using below stack:

Java

Play Framework

REST

## Deploy Local Server

The project can be deployed on localhost using [SBT](https://www.scala-sbt.org/download.html). Follow the below steps to bring up the server. The server runs by default on 9000 port. 

```console
$ cd <project>
$ sbt run
```

## Usage

### POST /details

> Submit travel details

> Sample json

```json
{
    "pgi": "Some124",
    "place": "London",
    "date": "2018-01-01"
}
```

## Testing

> Run all test

```console
$ cd <project>
$ sbt test
```
