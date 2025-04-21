# Para correr la app en local
```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/tu-repo.git
cd tu-repo

# Compilar y descargar dependencias
mvn clean install

# Correr la aplicación
mvn spring-boot:run
```





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
    - `200 OK`: Retorna el token de autenticación:
      ```eyJhbGciOiJIUzI1NiJ9...```
    - `401 UNAUTHORIZED`: "Email o contraseña inválida".

#### 🔹 Validar Token

- **URL:** `/auth/validate-token`
- **Método:** `POST`
- **Descripción:** Valida si un token es válido o ha expirado.
- **Parámetros:**
    - `token`:
  ````
  eyJhbGciOiJIUzI1NiJ9...
  ````
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
    - `200 OK`: Lista de `UserDTO`:

````json
[
  {
    "name": "John",
    "surname": "Doe",
    "email": "jorge2@example.com",
    "keyword": "$2a$12$tEy2q5Lcf4LLhPR3qGZfo.YQQXctfKVVtrkjW7znb0U9bunAGRD9e",
    "birth_date": "1995-05-20",
    "role": "USER",
    "plan": null,
    "userProgress": [],
    "diet": null,
    "workout": null
  },
  {
    "name": "Jorge",
    "surname": "Bytes",
    "email": "yeyeyey@example.com",
    "keyword": "GT2XY0GGUyjl4itfuNGlRg==",
    "birth_date": "1999-10-20",
    "role": "USER",
    "plan": "NONE",
    "userProgress": [
      {
        "imageUrl": "https://example.com/images/progress1.jpg",
        "weight": 72.5,
        "height": 1.75,
        "progressDate": "2025-05-15"
      }
    ],
    "diet": {
      "diet_id": 2,
      "diet_name": "Prueba Diet",
      "start_date": "2025-04-01",
      "note": "Come mucho mirei",
      "created_at": "2025-04-01T10:30:00"
    },
    "workout": {
      "workout_id": 1,
      "workout_name": "Engordar",
      "start_date": "2025-04-14",
      "note": "Pata y bises.",
      "created_at": "2025-04-14T09:45:00"
    }

    ]
````

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
    - `200 OK`: Usuario registrado correctamente.
    - `400 BAD REQUEST`: Error al registrar el usuario.

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
    - `200 OK`: Confirma el guardado del progreso o si se ha sobreescrito si es el mismo mes.

#### 🔹 Obtener Historial de Progreso

- **URL:** `/progress/history/{email}`
- **Método:** `GET`
- **Descripción:** Obtiene el historial de progreso de un usuario.
- **Parámetros:**
    - `email` (path param)
- **Respuestas:**
    - `200 OK`: Lista de `UserProgressEntity`:
```json

```
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

#### 🔹 Get all plans

- **URL:** `/plans`
- **Método:** `GET`
- **Descripción:** Devuelve todos los planes.
    - **Respuestas:**
    - `200 OK`: Lista de planes:
  ```json
    [
      "NONE",
      "NUTRITION",
      "TRAINING",
      "COMPLETE"
    ] 
  ```
    - `404 NOT FOUND`: Este correo no existe.
    -

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
        "diet_name": "Prueba Die2t2",
        "start_date": "2025-04-01",
        "note": "Dieta baja en carbohidratos",
        "created_at": "2025-04-01"
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
        "diet_id": 1,
        "diet_name": "KETO",
        "note": "Actualización a dieta keto",
        "start_date": "2024-04-01T10:30:00",
        "created_at": "2024-04-01T10:30:00"
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
        "diet_id": 2
      }
      ```
- **Respuestas:**
    - `200 OK`: Dieta asignada exitosamente.
    - `400 BAD REQUEST`: No se pudo asignar la dieta.

#### 🔹 Get Dieta por Id

- **URL:** `/diet/{id}`
- **Método:** `GET`
- **Descripción:** Recoge una dieta por su id.
- **Parámetros:**
    - `id` (path param)
- **Respuestas:**
    - `200 OK`: DietDTO:
      ```json
      {
        "diet_id": 2,
        "diet_name": "Prueba Diet",
        "start_date": "2025-04-01",
        "note": "Come mucho mirei",
        "created_at": "2025-04-01T10:30:00"
      }
      ```
    - `404 NOT FOUND`: No se encontró la dieta.

#### 🔹 Get All Dietas

- **URL:** `/diet`
- **Método:** `GET`
- **Descripción:** Devuelve todas las dietas.
- **Respuestas:**
    - `200 OK`: Una lista de DietDTO:
      ```json
      {
        "diet_id": 2,
        "diet_name": "Prueba Diet",
        "start_date": "2025-04-01",
        "note": "Come mucho mirei",
        "created_at": "2025-04-01T10:30:00"
      }
      ```
    - `400 BAD REQUEST`: No se ha podido recoger las dietas.

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

