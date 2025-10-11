# University Scheduler API Documentation

## Overview
This is a Spring Boot REST API for managing university schedules with role-based access control.

## CORS Configuration
The application uses a centralized CORS configuration located in `CorsConfig.java` that:
- Allows credentials (cookies, authorization headers)
- Supports all standard HTTP methods (GET, POST, PUT, DELETE, PATCH, OPTIONS)
- Allows requests from localhost and file:// origins (configurable for production)
- Handles preflight requests with 1-hour cache

**Configuration Location:** `com.yettensyvus.orarUSM.config.CorsConfig`

## User Roles
The system supports three authenticated user roles:
1. **STUDENT** - Students who can view their schedules
2. **PROFESSOR** - Teachers who can view their teaching schedules
3. **ADMIN** - Administrators with full system access

**Guest Access:** Unauthenticated users (no login required) can access public endpoints for read-only schedule viewing.

## API Endpoints

### Authentication Endpoints (`/api/auth`)

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "userName": "john.doe",
  "password": "password123",
  "roles": ["STUDENT"]
}
```

**Response:**
```json
{
  "message": "User registered successfully",
  "user": {
    "id": 1,
    "userName": "john.doe",
    "roles": ["STUDENT"]
  },
  "token": "mock-jwt-token"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "userName": "john.doe",
  "password": "password123"
}
```

**Response:**
```json
{
  "message": "Login successful",
  "user": {
    "id": 1,
    "userName": "john.doe",
    "roles": ["STUDENT"]
  },
  "token": "mock-jwt-token"
}
```

---

### Public Endpoints (`/api/public`)
**Access:** Available to all users without authentication (guest/unauthenticated access)

#### Schedules
- `GET /api/public/schedules` - Get all schedules
- `GET /api/public/schedules/{id}` - Get schedule by ID
- `GET /api/public/schedules/{scheduleId}/lessons` - Get lessons for a schedule

#### Faculties & Groups
- `GET /api/public/faculties` - Get all faculties
- `GET /api/public/faculties/{facultyId}/groups` - Get groups by faculty
- `GET /api/public/groups/{groupId}/lessons` - Get lessons for a group

#### Teachers
- `GET /api/public/teachers` - Get all teachers
- `GET /api/public/teachers/{teacherId}/lessons` - Get lessons by teacher

#### Buildings & Classrooms
- `GET /api/public/buildings` - Get all buildings
- `GET /api/public/buildings/{buildingId}/classrooms` - Get classrooms by building
- `GET /api/public/classrooms/{classroomId}/lessons` - Get lessons in a classroom

---

### Student Endpoints (`/api/student`)
**Access:** STUDENT role required

#### My Schedule & Lessons
- `GET /api/student/{studentId}/lessons` - Get all my lessons
- `GET /api/student/{studentId}/schedule` - Get my schedule
- `GET /api/student/{studentId}/subgroup-lessons` - Get my subgroup lessons

---

### Professor Endpoints (`/api/professor`)
**Access:** PROFESSOR role required

#### My Teaching Schedule
- `GET /api/professor/{teacherId}/lessons` - Get all lessons I teach
- `GET /api/professor/{teacherId}/profile` - Get my profile
- `GET /api/professor/{teacherId}/groups` - Get groups I teach
- `GET /api/professor/classroom/{classroomId}/lessons` - Check classroom availability

---

### Admin Endpoints (`/api/admin`)
**Access:** ADMIN role required

#### User Management
- `GET /api/admin/users` - Get all users
- `GET /api/admin/users/{id}` - Get user by ID
- `DELETE /api/admin/users/{id}` - Delete user

#### System Management
- `GET /api/admin/statistics` - Get system statistics
- `GET /api/admin/health` - Health check

**Statistics Response:**
```json
{
  "totalUsers": 150,
  "totalStudents": 120,
  "totalTeachers": 25,
  "totalSchedules": 5,
  "totalLessons": 300,
  "totalFaculties": 8,
  "totalGroups": 30
}
```

---

### Standard CRUD Endpoints

All entities support standard CRUD operations at `/api/{entity}`:

#### Students (`/api/students`)
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `GET /api/students/group/{groupId}` - Get students by group
- `GET /api/students/subgroup/{subgroupId}` - Get students by subgroup
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student

#### Teachers (`/api/teachers`)
- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `GET /api/teachers/faculty/{facultyId}` - Get teachers by faculty
- `POST /api/teachers` - Create teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher

#### Schedules (`/api/schedules`)
- `GET /api/schedules` - Get all schedules
- `GET /api/schedules/{id}` - Get schedule by ID
- `GET /api/schedules/year/{year}` - Get schedules by year
- `POST /api/schedules` - Create schedule
- `PUT /api/schedules/{id}` - Update schedule
- `DELETE /api/schedules/{id}` - Delete schedule

#### Lessons (`/api/lessons`)
- `GET /api/lessons` - Get all lessons
- `GET /api/lessons/{id}` - Get lesson by ID
- `GET /api/lessons/teacher/{teacherId}` - Get lessons by teacher
- `GET /api/lessons/group/{groupId}` - Get lessons by group
- `GET /api/lessons/schedule/{scheduleId}` - Get lessons by schedule
- `GET /api/lessons/classroom/{classroomId}` - Get lessons by classroom
- `POST /api/lessons` - Create lesson
- `PUT /api/lessons/{id}` - Update lesson
- `DELETE /api/lessons/{id}` - Delete lesson

#### Groups (`/api/groups`)
- `GET /api/groups` - Get all groups
- `GET /api/groups/{id}` - Get group by ID
- `GET /api/groups/faculty/{facultyId}` - Get groups by faculty
- `POST /api/groups` - Create group
- `PUT /api/groups/{id}` - Update group
- `DELETE /api/groups/{id}` - Delete group

#### Faculties (`/api/faculties`)
- `GET /api/faculties` - Get all faculties
- `GET /api/faculties/{id}` - Get faculty by ID
- `POST /api/faculties` - Create faculty
- `PUT /api/faculties/{id}` - Update faculty
- `DELETE /api/faculties/{id}` - Delete faculty

#### Buildings (`/api/buildings`)
- `GET /api/buildings` - Get all buildings
- `GET /api/buildings/{id}` - Get building by ID
- `POST /api/buildings` - Create building
- `PUT /api/buildings/{id}` - Update building
- `DELETE /api/buildings/{id}` - Delete building

#### Classrooms (`/api/classrooms`)
- `GET /api/classrooms` - Get all classrooms
- `GET /api/classrooms/{id}` - Get classroom by ID
- `GET /api/classrooms/building/{buildingId}` - Get classrooms by building
- `POST /api/classrooms` - Create classroom
- `PUT /api/classrooms/{id}` - Update classroom
- `DELETE /api/classrooms/{id}` - Delete classroom

#### Subjects (`/api/subjects`)
- `GET /api/subjects` - Get all subjects
- `GET /api/subjects/{id}` - Get subject by ID
- `GET /api/subjects/type/{type}` - Get subjects by type
- `POST /api/subjects` - Create subject
- `PUT /api/subjects/{id}` - Update subject
- `DELETE /api/subjects/{id}` - Delete subject

#### Subgroups (`/api/subgroups`)
- `GET /api/subgroups` - Get all subgroups
- `GET /api/subgroups/{id}` - Get subgroup by ID
- `GET /api/subgroups/group/{groupId}` - Get subgroups by group
- `POST /api/subgroups` - Create subgroup
- `PUT /api/subgroups/{id}` - Update subgroup
- `DELETE /api/subgroups/{id}` - Delete subgroup

#### Time Slots (`/api/timeslots`)
- `GET /api/timeslots` - Get all time slots
- `GET /api/timeslots/{id}` - Get time slot by ID
- `GET /api/timeslots/day/{dayOfWeek}` - Get time slots by day
- `POST /api/timeslots` - Create time slot
- `PUT /api/timeslots/{id}` - Update time slot
- `DELETE /api/timeslots/{id}` - Delete time slot

#### Users (`/api/users`)
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{userName}` - Get user by username
- `DELETE /api/users/{id}` - Delete user

---

## Security Notes

⚠️ **Important for Production:**

1. **Password Hashing:** Currently passwords are stored in plain text. Implement BCrypt password encoding:
   ```java
   // Add to UserServiceImpl
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   // In register method:
   user.setPassword(passwordEncoder.encode(request.getPassword()));
   
   // In login method:
   if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
       // Invalid password
   }
   ```

2. **JWT Implementation:** Replace "mock-jwt-token" with actual JWT token generation and validation.

3. **CORS Origins:** Update `CorsConfig.java` to specify exact production origins instead of wildcards.

4. **Role-Based Security:** Add Spring Security with method-level security:
   ```java
   @PreAuthorize("hasRole('ADMIN')")
   @PreAuthorize("hasRole('STUDENT')")
   @PreAuthorize("hasRole('PROFESSOR')")
   ```

5. **Authentication Verification:** Add authentication checks in role-specific controllers to verify the authenticated user matches the requested resource.

---

## Configuration

### CORS Settings
Edit `CorsConfig.java` to customize:
- Allowed origins
- Allowed methods
- Allowed headers
- Credentials support
- Max age for preflight requests

### Database
Configure database connection in `application.properties`:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

---

## Testing the API

### Using cURL

**Register a new user:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"student1","password":"pass123","roles":["STUDENT"]}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"student1","password":"pass123"}'
```

**Get public schedules:**
```bash
curl http://localhost:8080/api/public/schedules
```

**Get admin statistics:**
```bash
curl http://localhost:8080/api/admin/statistics
```

---

## Error Handling

The API returns standard HTTP status codes:
- `200 OK` - Successful GET/PUT request
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `400 Bad Request` - Invalid request data
- `401 Unauthorized` - Authentication failed
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

---

## Next Steps

1. Implement Spring Security with JWT authentication
2. Add password encryption with BCrypt
3. Implement role-based access control with `@PreAuthorize`
4. Add input validation with `@Valid` annotations
5. Implement pagination for large datasets
6. Add API versioning
7. Create integration tests
8. Add API rate limiting
9. Implement audit logging
10. Add Swagger/OpenAPI documentation
