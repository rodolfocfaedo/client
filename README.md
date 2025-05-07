## 📝 Client Project

This project is a RESTful API for client management, built with **Spring Boot** and **Java**. It includes JWT token authentication, CRUD operations for clients and phone numbers, and integration with **PostgreSQL**. Ideal for back-end development and Spring Security studies.

---

### ✨ Features

- **Add client** (name, email, password, phones).
- **Client login** (returns JWT token for authentication).
- **Fetch authenticated client** (via JWT token).
- **Update client data** (name, email).
- **Update phone number** (add/edit numbers).
- **Delete phone number** by ID.
- **Delete client** (requires authentication).

---

### 🛠 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security** (JWT Authentication)
- **PostgreSQL** (Database)
- **Gradle** (Dependency Management)
- **Postman** (API Testing)

---

### ⚙️ Prerequisites

- Java JDK 17+
- Gradle 8+
- PostgreSQL installed
- Postman (or similar API client)

---

### 🚀 Installation

1. Clone the repository:
    
    ```bash
    git clone <https://github.com/your-username/client-project.git>
    
    ```
    
2. Configure the database:
    - Create a `client_db` database in PostgreSQL.
    - Update credentials in `application.properties`:
        
        ```
        spring.datasource.url=jdbc:postgresql://localhost:5432/client_db
        spring.datasource.username=your_user
        spring.datasource.password=your_password
        
        ```
        
3. Build the project with Gradle:
    
    ```bash
    gradle clean build
    
    ```
    
4. Run the application:
    
    ```bash
    java -jar build/libs/client-project-0.0.1-SNAPSHOT.jar
    
    ```
    

---

### 🔗 Endpoints

| HTTP Method | Endpoint | Description | Headers | Parameters/Variables |
| --- | --- | --- | --- | --- |
| POST | `/client` | Register a new client | - | JSON body (client) |
| POST | `/client/login` | Login (returns JWT token) | - | JSON body (email, password) |
| POST | `/client/cellphone` | Register a new phone number | `Authorization: token` | JSON body (cellphone) |
| GET | `/client/` | Fetch client by email | `Authorization: token` | `email` (param) |
| PUT | `/client/` | Update client data | `Authorization: token` | JSON body |
| PUT | `/client/cellphone` | Update phone number | `Authorization: token` | `id` (param) + JSON body (cellphone) |
| DELETE | `/client/cellphone/{cellphoneId}` | Delete phone by ID | `Authorization: token` | `phoneId` (path) |
| DELETE | `/client/` | Delete client by ID | `Authorization: token` | `email` (path) |

---

### 📋 Usage Example (Postman)

1. **Login**:
    - Endpoint: `POST /clients/login`
    - Body (JSON):
        
        ```json
        {
          "email": "client@example.com",
          "password": "password123"
        }
        
        ```
        
    - Response: JWT token in body (use in subsequent headers).
2. **Fetch Client**:
    - Endpoint: `GET /clients/client@example.com`
    - Header: `Authorization: token`

---

### 📄 License

This project is under the [MIT](https://www.notion.so/LICENSE) license.

---

### 📧 Contact

**Developer**: Rodolfo Cunhasque Faedo

**Email**: [faedorodolfo.programming@gmail.com](mailto:faedorodolfo.programming@gmail.com)

**Background**: Software Engineering Student & Java Back-end Development.
