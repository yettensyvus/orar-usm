# CORS Implementation Guide

This document explains the CORS (Cross-Origin Resource Sharing) implementation for the University Scheduler application.

## What is CORS?
CORS is a security feature implemented by web browsers that restricts web pages from making requests to a different domain than the one serving the web page. This is known as the "same-origin policy."

## Why Do We Need CORS?
In this application:
- **Backend API** runs on `http://localhost:8080` (Spring Boot)
- **Frontend** may run on `http://localhost:3000`, `http://localhost:5500`, or be opened as a file (`file://`)
- **Guest users** can access public endpoints without authentication
- **Authenticated users** (STUDENT, PROFESSOR, ADMIN) have role-specific access

Without CORS configuration, the browser would block API requests from the frontend.

## Implementation Details

{{ ... }}
### Centralized Configuration
Location: `src/main/java/com/yettensyvus/orarUSM/config/CorsConfig.java`

This configuration:
1. **Replaces all individual `@CrossOrigin` annotations** on controllers
2. **Provides consistent CORS policy** across all endpoints
3. **Easier to maintain** - single place to update CORS settings

### Key Features

#### 1. Allowed Origins
```java
config.setAllowedOriginPatterns(Arrays.asList(
    "http://localhost:*",      // Any localhost port
    "http://127.0.0.1:*",      // Any 127.0.0.1 port
    "file://*"                 // Local file system
));
```

**For Production:** Replace with specific origins:
```java
config.setAllowedOriginPatterns(Arrays.asList(
    "https://yourdomain.com",
    "https://www.yourdomain.com"
));
```

#### 2. Allowed Methods
```java
config.setAllowedMethods(Arrays.asList(
    "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
));
```
Supports all standard HTTP methods needed for REST APIs.

#### 3. Allowed Headers
```java
config.setAllowedHeaders(Arrays.asList(
    "Authorization",           // For JWT tokens
    "Content-Type",           // For JSON payloads
    "Accept",                 // Content negotiation
    "Origin",                 // CORS requirement
    "X-Requested-With",       // AJAX requests
    "Access-Control-Request-Method",
    "Access-Control-Request-Headers"
));
```

#### 4. Credentials Support
```java
config.setAllowCredentials(true);
```
Allows cookies and authorization headers to be sent with requests.

#### 5. Exposed Headers
```java
config.setExposedHeaders(Arrays.asList(
    "Authorization",          // JWT tokens in response
    "Content-Disposition"     // File downloads
));
```

#### 6. Preflight Cache
```java
config.setMaxAge(3600L);  // 1 hour
```
Browsers cache preflight OPTIONS requests for 1 hour to reduce overhead.

### URL Pattern
```java
source.registerCorsConfiguration("/api/**", config);
```
Applies CORS configuration to all endpoints starting with `/api/`.

## Changes Made

### Removed Individual Annotations
Previously, each controller had:
```java
@CrossOrigin(origins = "*")
```

This has been **removed** from all controllers:
- ScheduleController
- StudentController
- TeacherController
- LessonController
- GroupController
- BuildingController
- SubjectController
- SubgroupController
- TimeSlotController
- ClassroomController
- FacultyController

### Benefits of Centralized Configuration

1. **Consistency:** All endpoints have the same CORS policy
2. **Security:** Easier to audit and secure
3. **Maintainability:** Single place to update
4. **Flexibility:** Can easily add different policies for different URL patterns
5. **Production-Ready:** Easy to switch between development and production configs

## Testing CORS

### From Browser Console
```javascript
fetch('http://localhost:8080/api/public/schedules')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('CORS Error:', error));
```

### From Frontend Application
```javascript
// With credentials (cookies, auth headers)
fetch('http://localhost:8080/api/student/1/lessons', {
  method: 'GET',
  credentials: 'include',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer your-jwt-token'
  }
})
.then(response => response.json())
.then(data => console.log(data));
```

### Expected Headers in Response
When CORS is working correctly, you'll see these headers in the response:
```
Access-Control-Allow-Origin: http://localhost:3000
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, PATCH, OPTIONS
Access-Control-Allow-Headers: Authorization, Content-Type, Accept, ...
Access-Control-Expose-Headers: Authorization, Content-Disposition
Access-Control-Max-Age: 3600
```

## Common CORS Issues & Solutions

### Issue 1: "No 'Access-Control-Allow-Origin' header"
**Cause:** CORS not configured or wrong origin
**Solution:** Check `CorsConfig.java` includes your origin

### Issue 2: "Credentials flag is true, but 'Access-Control-Allow-Credentials' header is ''"
**Cause:** `allowCredentials` not set
**Solution:** Already configured in `CorsConfig.java`

### Issue 3: Preflight request fails
**Cause:** OPTIONS method not allowed
**Solution:** Already included in allowed methods

### Issue 4: Custom headers blocked
**Cause:** Header not in allowed list
**Solution:** Add to `setAllowedHeaders()` in `CorsConfig.java`

## Production Deployment

### Step 1: Update Allowed Origins
```java
// Development
config.setAllowedOriginPatterns(Arrays.asList(
    "http://localhost:*",
    "http://127.0.0.1:*"
));

// Production
config.setAllowedOriginPatterns(Arrays.asList(
    "https://scheduler.university.edu",
    "https://www.scheduler.university.edu"
));
```

### Step 2: Environment-Based Configuration
```java
@Value("${cors.allowed.origins}")
private String allowedOrigins;

@Bean
public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOriginPatterns(
        Arrays.asList(allowedOrigins.split(","))
    );
    // ... rest of configuration
}
```

In `application.properties`:
```properties
# Development
cors.allowed.origins=http://localhost:*,http://127.0.0.1:*,file://*

# Production (application-prod.properties)
cors.allowed.origins=https://yourdomain.com,https://www.yourdomain.com
```

### Step 3: Security Considerations
1. **Never use `*` in production** with credentials enabled
2. **Use HTTPS** in production
3. **Specify exact origins** instead of patterns when possible
4. **Limit allowed methods** to only what's needed
5. **Monitor CORS errors** in production logs

## Alternative: Profile-Based Configuration

```java
@Configuration
public class CorsConfig {
    
    @Bean
    @Profile("dev")
    public CorsFilter devCorsFilter() {
        // Permissive settings for development
    }
    
    @Bean
    @Profile("prod")
    public CorsFilter prodCorsFilter() {
        // Strict settings for production
    }
}
```

## Verification Checklist

- [x] Centralized CORS configuration created
- [x] All `@CrossOrigin` annotations removed from controllers
- [x] Allowed origins configured for development
- [x] Credentials support enabled
- [x] All necessary HTTP methods allowed
- [x] Authorization header allowed
- [x] Preflight caching configured
- [ ] Production origins configured (when deploying)
- [ ] HTTPS enforced in production (when deploying)
- [ ] CORS tested with actual frontend

## Additional Resources

- [MDN CORS Documentation](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
- [Spring CORS Documentation](https://docs.spring.io/spring-framework/reference/web/webmvc-cors.html)
- [CORS Best Practices](https://web.dev/cross-origin-resource-sharing/)

## Summary

The CORS implementation in this application:
- ✅ Centralized and maintainable
- ✅ Supports all required HTTP methods
- ✅ Allows credentials for authentication
- ✅ Configured for development environment
- ✅ Ready to be customized for production
- ✅ Follows Spring Boot best practices
