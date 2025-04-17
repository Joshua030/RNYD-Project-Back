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

### 6. Gestión de Usuarios (UserController)

Este módulo contiene los endpoints para consultar, modificar, eliminar y verificar usuarios registrados en el sistema.

---

#### 🔹 Obtener Todos los Usuarios

- **URL:** `/user`
- **Método:** `GET`
- **Descripción:** Retorna una lista con todos los usuarios registrados.
- **Parámetros:** Ninguno
- **Respuestas:**
  - `200 OK`: Lista de `UserDTO`.

---

#### 🔹 Obtener Usuario por Email

- **URL:** `/user/{email}`
- **Método:** `GET`
- **Descripción:** Retorna la información de un usuario según su email.
- **Parámetros:**
  - `email` (path param)
- **Respuestas:**
  - `200 OK`: Objeto `UserDTO`.
  - `404 NOT FOUND`: Usuario no encontrado.

---

#### 🔹 Modificar Usuario

- **URL:** `/user`
- **Método:** `PATCH`
- **Descripción:** Modifica los datos de un usuario existente.
- **Parámetros:**
  - `UserDTO` (JSON body)
    ```json
    {
      "email": "usuario@example.com",
      "name": "NuevoNombre",
      "surname": "NuevoApellido",
      "keyword": "nuevaClave",
      "birth_date": "2025-04-01",
      "role": "USER"
    }
    ```
- **Respuestas:**
  - `200 OK`: Mensaje de modificación exitosa.
  - `404 NOT FOUND`: Usuario no encontrado.

---

#### 🔹 Eliminar Usuario

- **URL:** `/user/{email}`
- **Método:** `DELETE`
- **Descripción:** Elimina un usuario según su email.
- **Parámetros:**
  - `email` (path param)
- **Respuestas:**
  - `200 OK`: Mensaje de eliminación exitosa.
  - `404 NOT FOUND`: Usuario no encontrado.

---

#### 🔹 Verificar Rol de Administrador

- **URL:** `/user/check-admin/{email}`
- **Método:** `GET`
- **Descripción:** Verifica si un usuario tiene el rol de administrador.
- **Parámetros:**
  - `email` (path param)
- **Respuestas:**
  - `200 OK`: `true` si es administrador, `false` si no lo es.

### 7. Gestión de Dietas (DietController)

Este módulo contiene los endpoints para crear, actualizar, eliminar y asignar dietas a usuarios.

---

#### 🔹 Crear Dieta

- **URL:** `/diet/create`
- **Método:** `POST`
- **Descripción:** Crea una nueva dieta.
- **Parámetros:**
  - `DietDTO` (JSON body)
    ```json
    {
      "id": 1,
      "type": "LOW_CARB",
      "description": "Dieta baja en carbohidratos",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `201 CREATED`: Dieta creada exitosamente.
  - `400 BAD REQUEST`: No se pudo crear la dieta.

---

#### 🔹 Actualizar Dieta

- **URL:** `/diet`
- **Método:** `PATCH`
- **Descripción:** Actualiza la información de una dieta existente.
- **Parámetros:**
  - `DietDTO` (JSON body)
    ```json
    {
      "id": 1,
      "type": "KETO",
      "description": "Actualización a dieta keto",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `200 OK`: Dieta actualizada exitosamente.
  - `400 BAD REQUEST`: No se pudo actualizar la dieta.

---

####🔹 Eliminar Dieta

- **URL:** `/diet/{id}`
- **Método:** `DELETE`
- **Descripción:** Elimina una dieta específica según su ID.
- **Parámetros:**
  - `id` (path param)
- **Respuestas:**
  - `204 NO CONTENT`: Dieta eliminada exitosamente.
  - `400 BAD REQUEST`: No se pudo eliminar la dieta.

---

#### 🔹 Asignar Dieta a Usuario

- **URL:** `/diet/assign/{email}`
- **Método:** `POST`
- **Descripción:** Asigna una dieta a un usuario según su email.
- **Parámetros:**
  - `email` (path param)
  - `DietDTO` (JSON body)
    ```json
    {
      "id": 2,
      "type": "VEGAN",
      "description": "Dieta vegana personalizada",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `200 OK`: Dieta asignada exitosamente.
  - `400 BAD REQUEST`: No se pudo asignar la dieta.

### 8.️ Gestión de Rutinas de Ejercicio (WorkOutController)

Este módulo contiene los endpoints para crear, actualizar, eliminar y asignar rutinas de ejercicio a los usuarios.

---

#### 🔹 Crear Rutina de Ejercicio

- **URL:** `/workout/create`
- **Método:** `POST`
- **Descripción:** Crea una nueva rutina de ejercicio.
- **Parámetros:**
  - `WorkOutDTO` (JSON body)
    ```json
    {
      "id": 1,
      "name": "Full Body",
      "description": "Rutina completa para todo el cuerpo",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `201 CREATED`: Rutina creada exitosamente.
  - `400 BAD REQUEST`: No se pudo crear la rutina.

---

#### 🔹 Actualizar Rutina de Ejercicio

- **URL:** `/workout`
- **Método:** `PATCH`
- **Descripción:** Actualiza la información de una rutina de ejercicio existente.
- **Parámetros:**
  - `WorkOutDTO` (JSON body)
    ```json
    {
      "id": 1,
      "name": "Full Body Avanzado",
      "description": "Versión avanzada del entrenamiento full body",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `200 OK`: Rutina actualizada exitosamente.
  - `400 BAD REQUEST`: No se pudo actualizar la rutina.

---

#### 🔹 Eliminar Rutina de Ejercicio

- **URL:** `/workout/{id}`
- **Método:** `DELETE`
- **Descripción:** Elimina una rutina de ejercicio específica según su ID.
- **Parámetros:**
  - `id` (path param)
- **Respuestas:**
  - `204 NO CONTENT`: Rutina eliminada exitosamente.
  - `400 BAD REQUEST`: No se pudo eliminar la rutina.

---

#### 🔹 Asignar Rutina a Usuario

- **URL:** `/workout/assign/{email}`
- **Método:** `POST`
- **Descripción:** Asigna una rutina de ejercicio a un usuario según su email.
- **Parámetros:**
  - `email` (path param)
  - `WorkOutDTO` (JSON body)
    ```json
    {
      "id": 2,
      "name": "Piernas y Glúteos",
      "description": "Entrenamiento específico para parte inferior",
      "createdDate": "2025-04-01"
    }
    ```
- **Respuestas:**
  - `200 OK`: Rutina asignada exitosamente.
  - `400 BAD REQUEST`: No se pudo asignar la rutina.

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
| **Gestión de Dietas**| `POST`  | `/diet/create`                | Crea una nueva dieta.                               | `DietDTO` (body)                                       | `201 CREATED`: Dieta creada<br>`400 BAD REQUEST`: No se pudo crear la dieta          |
| **Gestión de Dietas**| `PATCH` | `/diet`                       | Actualiza una dieta existente.                      | `DietDTO` (body)                                       | `200 OK`: Dieta actualizada<br>`400 BAD REQUEST`: No se pudo actualizar              |
| **Gestión de Dietas**| `DELETE`| `/diet/{id}`                  | Elimina una dieta por ID.                           | `id` (path param)                                      | `204 NO CONTENT`: Dieta eliminada<br>`400 BAD REQUEST`: No se pudo eliminar          |
| **Gestión de Dietas**| `POST`  | `/diet/assign/{email}`        | Asigna una dieta a un usuario por email.            | `email` (path param), `DietDTO` (body)                 | `200 OK`: Dieta asignada<br>`400 BAD REQUEST`: No se pudo asignar la dieta           |
| **Gestión de Rutinas**| `POST` | `/workout/create`             | Crea una nueva rutina de ejercicio.                 | `WorkOutDTO` (body)                                    | `201 CREATED`: Rutina creada<br>`400 BAD REQUEST`: No se pudo crear la rutina        |
| **Gestión de Rutinas**| `PATCH`| `/workout`                    | Actualiza una rutina de ejercicio.                  | `WorkOutDTO` (body)                                    | `200 OK`: Rutina actualizada<br>`400 BAD REQUEST`: No se pudo actualizar             |
| **Gestión de Rutinas**| `DELETE`| `/workout/{id}`              | Elimina una rutina por ID.                          | `id` (path param)                                      | `204 NO CONTENT`: Rutina eliminada<br>`400 BAD REQUEST`: No se pudo eliminar         |
| **Gestión de Rutinas**| `POST` | `/workout/assign/{email}`     | Asigna una rutina a un usuario por email.           | `email` (path param), `WorkOutDTO` (body)              | `200 OK`: Rutina asignada<br>`400 BAD REQUEST`: No se pudo asignar la rutina         |
