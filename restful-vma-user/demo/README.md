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

```sh
git clone [https://github.com/yourusername/user-api.git](https://github.com/CybionV/userapi.git)
cd user-api
