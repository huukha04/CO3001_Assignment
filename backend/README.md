# Backend - CO3001 Assignment

Dự án Backend được xây dựng bằng **Spring Boot 3.5.8** với kiến trúc **Feature-Based Architecture**. Nó cung cấp các API REST cho quản lý khóa học, người dùng, xác thực, phản hồi và lên lịch.

## Mục lục

- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cấu trúc dự án](#cấu-trúc-dự-án)
- [Cài đặt và chạy](#cài-đặt-và-chạy)
- [Các API](#các-api)

---

## Yêu cầu hệ thống

### Java 21
Dự án này **yêu cầu Java 21** trở lên để chạy.

**Cách cài đặt Java 21:**
1. Truy cập [Oracle Java SE Downloads](https://www.oracle.com/java/technologies/downloads/#java21) hoặc [OpenJDK](https://jdk.java.net/21/)
2. Tải xuống JDK 21 phù hợp với OS của bạn
3. Cài đặt theo hướng dẫn
4. Kiểm tra cài đặt:
   ```bash
   java -version
   ```
   Kết quả nên hiển thị phiên bản **21** trở lên

### MongoDB
Dự án sử dụng **MongoDB** làm cơ sở dữ liệu chính.

**Cấu hình MongoDB Connection String trong `application.yaml`:**
```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://username:password@cluster.mongodb.net/database
```

**Quản lý Database với VSCode Database Client Extension:**
1. Cài đặt extension: [Database Client](https://marketplace.visualstudio.com/items?itemName=cweijan.vscode-mysql-client2)
2. Mở Command Palette: `Ctrl + Shift + P`
3. Chọn **Database Client: Add Connection**
4. Chọn **MongoDB** và nhập Connection String
5. Kết nối và quản lý database qua giao diện người dùng

### Gradle
Dự án sử dụng **Gradle 8.x** để build và chạy ứng dụng.

```bash
# Kiểm tra Gradle version
./gradlew --version
```

---

## Cấu trúc dự án

Dự án được tổ chức theo **Feature-Based Architecture** để dễ bảo trì và mở rộng.

```
src/
├── main/java/com/backend/
│   ├── BackendApplication.java           # Điểm khởi động ứng dụng
│   ├── common/
│   │   ├── config/                       # Spring configuration
│   │   ├── exception/                    # Exception handlers
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── ResourceNotFoundException.java
│   │   └── util/                         # Utility classes
│   └── features/
│       ├── auth/                         # Tính năng xác thực & JWT
│       │   ├── controller/               # REST endpoints
│       │   ├── service/                  # Business logic
│       │   ├── repository/               # Data access
│       │   └── dto/                      # Data transfer objects
│       ├── user/                         # Quản lý người dùng
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── entity/
│       │   └── dto/
│       ├── course/                       # Quản lý khóa học
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── entity/
│       │   └── dto/
│       ├── feedback/                     # Phản hồi phiên học
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── entity/
│       │   └── dto/
│       └── schedule/                     # Lên lịch
│           ├── controller/
│           ├── service/
│           ├── repository/
│           ├── entity/
│           └── dto/
├── test/java/com/backend/
│   ├── controller/                       # Controller unit tests
│   │   ├── auth/
│   │   ├── user/
│   │   ├── course/
│   │   ├── feedback/
│   │   └── schedule/
│   └── service/                          # Service unit tests
└── resources/
    └── application.yaml                  # Spring Boot configuration
```

---

## Cài đặt và chạy

### 1. Sao chép kho lưu trữ
```bash
git clone <repository-url>
cd backend
```

### 2. Biên dịch ứng dụng
```bash
./gradlew clean build
```

### 3. Chạy ứng dụng

**Cách 1: VSCode (Khuyến nghị)**
1. Mở thư mục dự án trong VSCode
2. Mở file `src/main/java/com/backend/BackendApplication.java`
3. Nhấn chuột phải → **Chạy Java**
4. Ứng dụng sẽ chạy trên `http://localhost:8080`

**Cách 2: Terminal**
```bash
./gradlew bootRun
```

**Cách 3: Sử dụng tệp JAR**
```bash
./gradlew bootJar
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

**Lưu ý:** Đảm bảo MongoDB đã chạy trước khi khởi động ứng dụng.

---

## Các API

### Authentication API
```
POST /api/auth/login
```

### Admin API
```
GET /api/admin/feedback/{sectionId}
GET /api/admin/attendants
GET /api/admin/attendants/count
```

### Course Section API
```
POST /api/course/section/cancel
POST /api/course/section/update-capacity
POST /api/course/section/update-name
GET /api/course/section/{sectionId}/feedbacks
GET /api/course/section/{sectionId}/reports
GET /api/course/section/{sectionId}/mentees
```

### Session Feedback API
```
GET /api/feedbacks/section/{sectionId}
PUT /api/feedbacks/{id}
DELETE /api/feedbacks/{id}
```

### Schedule API
```
PUT /api/schedule/available-slots/location
PUT /api/schedule/available-slots/time
PUT /api/schedule/available-slots/status
DELETE /api/schedule/available-slots/{slotId}
```

---

## Dependencies

### Core Dependencies
- **Spring Boot 3.5.8** - Framework chính
- **Spring Data MongoDB** - Database access layer
- **Spring Security + JWT** - Authentication & authorization
- **Lombok** - Code generation
- **Jackson** - JSON processing

### Test Dependencies
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **Spring Boot Test** - Integration testing
- **MockMvc** - HTTP request testing
- **JaCoCo** - Code coverage

---

## Troubleshooting

### Problem: Tests failed
**Solution:**
```bash
# Clean build cache
./gradlew clean

# Run tests with verbose output
./gradlew test jacocoSummary --rerun-tasks

# Check test report
build/reports/tests/test/index.html
```

### Problem: Port 8080 đã được sử dụng
**Solution:**
```bash
# Thay đổi port trong application.yaml
server:
  port: 8080
```

---

## References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [JUnit 5 Guide](https://junit.org/junit5/docs/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)

---
