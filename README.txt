**************************************
Start:

1. Start docker containers (docker-compose up -d)
2. Run Application

**************************************
POST request (insert new receipt in db)


curl --location --request POST 'http://localhost:8080/car/publish' \
--header 'Content-Type: application/json' \
--data-raw ' {
        "price": "100",
        "dateOfSale": "2001-12-12",
        "seller": {
            "firstName": "firstName1",
            "lastName": "lastName1",
            "birthday": "2000-02-02"
        },
        "car": {
            "manufacturer": "manufacturer1",
            "make": "make1"
    }
  }'

**************************************
GET request (get all receipts from db)

curl --location --request GET 'http://localhost:8080/car/get'