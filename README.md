# rest-api-kotlin-springboot

```bash
# Create topic

curl --location --request POST 'http://localhost:8080/topics' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "First title",
    "message": "This is my message",
    "courseId": 1,
    "authorId": 1
}'
```

```bash
# List topics

curl --location --request GET 'http://localhost:8080/topics' \
--header 'Content-Type: application/json' \
--data-raw ''
```


```bash
# List specific topic

curl --location --request GET 'http://localhost:8080/topics/1' \
--header 'Content-Type: application/json' \
--data-raw ''
```


```bash
# Update topic

curl --location --request PUT 'http://localhost:8080/topics' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "title": "First title UPDATED",
    "message": "This is my message"
}'
```


```bash
# Delete topic

curl --location --request DELETE 'http://localhost:8080/topics/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "title": "First title UPDATED",
    "message": "This is my message"
}'
```


