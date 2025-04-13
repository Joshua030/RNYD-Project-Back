# 📌 API Documentation

Este repositorio contiene los endpoints principales para autenticación, registro y progreso de usuario.

## 🚀 Endpoints

### 1. Autenticación (SignInController)

#### 🔹 Iniciar Sesión
- **URL:** `/auth/signin`
- **Método:** `POST`
- **Descripción:** Autentica a un usuario y devuelve un token JWT.
- **Parámetros:**
  - `UserDTO` (JSON body)
    ```json
    {
      "email": "usuario@example.com",
      "keyword": "contraseña123"
    }
    ```
- **Respuestas:**
  - `200 OK`: Retorna el token de autenticación.
  - `401 UNAUTHORIZED`: "Email o contraseña inválida".

#### 🔹 Validar Token
- **URL:** `/auth/validate-token`
- **Método:** `POST`
- **Descripción:** Valida si un token es válido o ha expirado.
- **Parámetros:**
  - `token` (query param)
- **Respuestas:**
  - `200 OK`: "Token válido".
  - `401 UNAUTHORIZED`: "Token inválido o expirado".

---

### 2. Registro de Usuarios (SignUpController)

#### 🔹 Obtener Usuarios Registrados
- **URL:** `/signup`
- **Método:** `GET`
- **Descripción:** Retorna una lista de usuarios registrados.
- **Respuestas:**
  - `200 OK`: Lista de `UserDTO`.

#### 🔹 Registrar Usuario
- **URL:** `/signup/register`
- **Método:** `POST`
- **Descripción:** Registra un nuevo usuario.
- **Parámetros:**
  - `UserDTO` (JSON body)
    ```json
    {
      "email": "nuevo@example.com",
      "name": "nombre",
      "surname": "apellido",
      "keyword" : "1234",
      "birth_date" : "2025-03-29",
      "role" : "USER" 
    }
    ```
- **Respuestas:**
  - `200 OK`: Retorna el usuario registrado.
  - `400 BAD REQUEST`: Si ocurre un error.

---

### 3. Progreso del Usuario (UserProgressController)

#### 🔹 Subir Progreso
- **URL:** `/progress/upload/{email}`
- **Método:** `POST`
- **Descripción:** Guarda el progreso de un usuario.
- **Parámetros:**
  - `email` (path param)
  - `UserProgressRequest` (JSON body)
    ```json
    {
      "imageUrl": "https://example.com/image1.jpg",
      "weight": 72.5,
      "height": 1.75,
      "progressDate": "2025-03-29"
    }
    ```
- **Respuestas:**
  - `200 OK`: Confirma el guardado del progreso.

#### 🔹 Obtener Historial de Progreso
- **URL:** `/progress/history/{email}`
- **Método:** `GET`
- **Descripción:** Obtiene el historial de progreso de un usuario.
- **Parámetros:**
  - `email` (path param)
- **Respuestas:**
  - `200 OK`: Lista de `UserProgressEntity`.
  - `404 NOT FOUND`: Si no hay registros.

### 4. Pasarela de pago (StripeController)
#### 🔹 Crear subscripción
- **URL:** `/stripe/create-subscription`
- **Método:** `POST`
- **Descripción:** Crea una subscripción.
- **Parámetros:**
- `StripeDTO` (JSON body)
    ```json
    {
      "name": "NUTRITION",
      "description": "Nutrition plan .",
      "price": 25,
      "priceId": "9876542"
    }
    ```
  - **Respuestas:**
  - `201 CREATED`: True.
  - `400 BAD REQUEST`: False.
  
#### 🔹 Crear subscripción
- **URL:** `/stripe/subscribe`
- **Método:** `POST`
- **Descripción:** Solicitud de link de pago.
- **Parámetros:**
- `StripeDTO` (JSON body)
    ```json
    {
      "priceId": "9876542"
    }
    ```
  - **Respuestas:**
  - `201 CREATED`: Enlace de pago.
  - `400 BAD REQUEST`: Error al crear el enlace.

### 5. Selección de plan (PlanController)
#### 🔹 Asignar o cambiar un plan
- **URL:** `/plans/assign/{email}`
- **Método:** `PATCH`
- **Descripción:** Asigna/Cambia una subscripción.
- **Parámetros:**
- `email` (path param)
- `PlanRequest` (JSON body)
    ```json
    {
      "plan" : "NUTRITION"
    }
    ```
  - **Respuestas:**
  - `200 OK`: El nuevo plan asignado es: %s.
  - `404 NOT FOUND`: Este correo no existe.

#### 🔹 Cancelar plan
- **URL:** `/plans/cancel/{email}`
- **Método:** `PATCH`
- **Descripción:** Cancela una subscripción.
- **Parámetros:**
- `email` (path param)
  - **Respuestas:**
  - `200 OK`: Plan cancelado.
  - `404 NOT FOUND`: Este correo no existe.

# 📌 Tabla de Endpoints

Esta tabla resume todos los endpoints disponibles en la API.

| Módulo | Método  | URL                         | Descripción                                  | Parámetros                                         | Respuestas                                                                          |
|--------|---------|-----------------------------|----------------------------------------------|----------------------------------------------------|-------------------------------------------------------------------------------------|
| **Autenticación** | `POST`  | `/auth/signin`              | Iniciar sesión y obtener un token JWT.       | `UserDTO` (body)                                   | `200 OK`: Token JWT<br>`401 UNAUTHORIZED`: Credenciales inválidas                   |
| **Autenticación** | `POST`  | `/auth/validate-token`      | Validar si un token es válido o ha expirado. | `token` (query param)                              | `200 OK`: Token válido<br>`401 UNAUTHORIZED`: Token inválido o expirado             |
| **Registro de Usuarios** | `GET`   | `/signup`                   | Obtener la lista de usuarios registrados.    | Ninguno                                            | `200 OK`: Lista de usuarios                                                         |
| **Registro de Usuarios** | `POST`  | `/signup/register`          | Registrar un nuevo usuario.                  | `UserDTO` (body)                                   | `200 OK`: Usuario registrado<br>`400 BAD REQUEST`: Error en el registro             |
| **Progreso del Usuario** | `POST`  | `/progress/upload/{email}`  | Subir progreso del usuario.                  | `email` (path param), `UserProgressRequest` (body) | `200 OK`: Progreso guardado                                                         |
| **Progreso del Usuario** | `GET`   | `/progress/history/{email}` | Obtener historial de progreso del usuario.   | `email` (path param)                               | `200 OK`: Lista de progresos<br>`404 NOT FOUND`: No hay registros                   |
| **Pasarela de pago** | `POST`  | `/stripe/create-subscription` | Crea una subscripción.                       | `StripeDTO` (body)                                 | `201 CREATED`: True<br>`400 BAD REQUEST`: False                                     |
| **Pasarela de pago** | `POST`  | `/stripe/subscribe`               | Solicitud de link de pago.                   | `StripeDTO` (body)                                 | `201 CREATED`: Enlace de pago<br>`400 BAD REQUEST`: Error al crear el enlace        |
| **Selección de plan** | `PATCH` | `/plans/assign/{email}`                | Asigna/Cambia una subscripción.              | `PlanRequest` (body)                                 | `200 OK`: El nuevo plan asignado es: %s.<br>`404 NOT FOUND`: Este correo no existe. |
| **Selección de plan** | `PATCH` | `/plans/cancel/{email}`                | Cancela una subscripción.                    | `PlanRequest` (body)                                 | `200 OK`: Plan cancelado.<br>`404 NOT FOUND`: Este correo no existe.                |

