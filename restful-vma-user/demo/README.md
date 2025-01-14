# User API

API Restful para el registro de usuarios, generando un token jwt para cada uno, <ejercicio hecho por vma>.

## Features

- Crea un usuario con la siguiente estructura.
{
    "name": "Victor Avalos",
    "password": "vavalos123",
    "email": "victor.avalos@vmatest.com",
    "isActive": true,
    "phones": [
        {
            "countryCode": "56",
            "cityCode": "02",
            "number": "12345678"
        },
        {
            "countryCode": "56",
            "cityCode": "09",
            "number": "87654321"
        }
    ]
}
- Swagger documentation

## Prerequisites

- Java 11 or higher
- Maven
- Spring Boot

## Getting Started

### Clone the repository

git clone [https://github.com/yourusername/user-api.git](https://github.com/CybionV/userapi.git)
cd user-api

## una vez clonado el repositorio, ejecutar en consola "mvn spring-boot:run"
## usando postman o algun programa similar, utilizar el siguiente curl

curl -X POST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{
    "name": "Victor Avalos",
    "password": "password123",
    "email": "victor.avalos@apitest.com",
    "isActive": true,
    "phones": [
        {
            "countryCode": "56",
            "cityCode": "02",
            "number": "4567890"
        },
        {
            "countryCode": "56",
            "cityCode": "9",
            "number": "876543221"
        }
    ]
}'

