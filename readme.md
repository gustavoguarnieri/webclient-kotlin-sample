# Web Client Consumer Kotlin Sample

The project is an example of using the http web client to promote synchronous and asynchronous https calls.

## Requirements

Java 11+

IntelliJ IDEA / Netbeans / Eclipse

MongoDB

## Usage

```python

1) Execute command: docker-compose up -d (the file docker-compose.yml will be executed).

2) Start project

3) Synchronous call:
   curl --location --request GET 'http://localhost:9080/webclient-consumer/api/v1/products/1/images/sync'

   Asynchronous call:
   curl --location --request GET 'http://localhost:9080/webclient-consumer/api/v1/products/1/images/async'
   
   curl --location --request GET 'http://localhost:9080/webclient-consumer/api/v1/products/1/images/async/coroutines'
```

## Documentation

[Swagger](http://localhost:9080/webclient-consumer/swagger-ui/index.html)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)