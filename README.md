# app-rest-demo

## Get unique card from uuid

http://localhost:8080/app-rest-demo/api/v1/card/22739c36-c621-11ea-87d0-0242ac130003

## Simulate Not Found

http://localhost:8080/app-rest-demo/api/v1/card/nf/227

## All cards from user

http://localhost:8080/app-rest-demo/api/v1/card/user/a618522c-c624-11ea-87d0-0242ac130003

## Register new card

http://localhost:8080/app-rest-demo/api/v1/card/new

Payload example:
```json
{    
    "nickname": "Cielo",
    "number": "1234123412341237",
    "flag": "Elo",
    "localPhoneSAC": "08005912117",
    "internationPhoneSAC": "16367227111",
    "emailSAC": "meajuda@cielo.com.br",
    "bankCard": true,
    "bankName": "NuBank",
	"userUUID": "a618522c-c624-11ea-87d0-0242ac130003"
}
```