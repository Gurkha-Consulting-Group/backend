# Quick Start Guide - Profile Configuration

## ✅ What Was Done

Your Spring Boot application now has **two separate database configurations**:

### 🏠 Local Profile (H2 Database)
- **Database**: H2 in-memory
- **Auto-activated**: Default when you run locally
- **H2 Console**: Available at `http://localhost:8080/h2-console`
- **Schema**: Auto-created/updated on startup
- **Data**: Reset on each restart

### 🚀 Production Profile (Supabase PostgreSQL)
- **Database**: Supabase PostgreSQL
- **Auto-activated**: In Docker containers
- **Credentials**: From environment variables
- **Schema**: Validated (not auto-changed)
- **Data**: Persistent

---

## 🎯 Quick Commands

### Local Development (H2)
```bash
# Start the app - uses H2 automatically
./mvnw spring-boot:run

# Access H2 Console
# URL: http://localhost:8080/h2-console
# JDBC: jdbc:h2:mem:contactrequestdb
# User: sa
# Pass: (empty)
```

### Production Deployment
```bash
# Build Docker image
docker build -t gurkha-backend:latest .

# Run locally with production DB
docker run -p 8080:8080 \
	-e SPRING_DATASOURCE_URL="jdbc:postgresql://db.rbzgdacifytwcedrfmtc.supabase.co:5432/postgres?sslmode=require" \
	-e SPRING_DATASOURCE_USERNAME="postgres" \
	-e SPRING_DATASOURCE_PASSWORD="your-password" \
	gurkha-backend:latest
```

### Render Deployment
1. **Build Command**: `docker build -t gurkha-backend .`
2. **Start Command**: `docker run -p 8080:8080 gurkha-backend`
3. **Environment Variables** (set in Render dashboard):
	 ```
	 SPRING_PROFILES_ACTIVE=prod
	 SPRING_DATASOURCE_URL=jdbc:postgresql://db.rbzgdacifytwcedrfmtc.supabase.co:5432/postgres?sslmode=require
	 SPRING_DATASOURCE_USERNAME=postgres
	 SPRING_DATASOURCE_PASSWORD=<your-password>
	 ```

---

## 📁 Configuration Files

### application.properties (Common)
- Application name, version, developer info
- Default profile: `local`
- Server port: 8080
- Common JPA and logging settings

### application-local.properties (H2)
- H2 in-memory database
- H2 Console enabled
- Debug logging
- Auto-schema updates

### application-prod.properties (Supabase)
- PostgreSQL configuration
- Environment variable substitution
- Schema validation only
- Production logging levels

---

## 🔍 How to Verify It Works

### Test Local Profile
```bash
# Run the app
./mvnw spring-boot:run

# Look for in logs:
# "The following profiles are active: local"

# Test the app
curl http://localhost:8080/health

# Check H2 Console
# Open: http://localhost:8080/h2-console
```

### Test Production Profile
```bash
# Run with prod profile
./mvnw spring-boot:run -Dspring.profiles.active=prod

# Look for in logs:
# "The following profiles are active: prod"
# Should connect to Supabase PostgreSQL

# Test the app
curl http://localhost:8080/health
```

### Run Automated Tests
```bash
# Make script executable (if not already)
chmod +x test-profiles.sh

# Run verification script
./test-profiles.sh
```

---

## 🎨 Visual Overview

```
┌─────────────────────────────────────────────────────────┐
│         GURKHA BACKEND - PROFILE CONFIGURATION          │
└─────────────────────────────────────────────────────────┘

LOCAL DEVELOPMENT                    PRODUCTION
==================                   ============

┌──────────────────┐                ┌──────────────────┐
│   Developer      │                │   Render/Cloud   │
│   Workstation    │                │    Platform      │
└────────┬─────────┘                └────────┬─────────┘
				 │                                   │
				 │ ./mvnw spring-boot:run            │ Docker Container
				 │                                   │
				 ▼                                   ▼
┌──────────────────┐                ┌──────────────────┐
│  Spring Boot App │                │  Spring Boot App │
│  Profile: local  │                │  Profile: prod   │
└────────┬─────────┘                └────────┬─────────┘
				 │                                   │
				 │ JDBC                              │ JDBC
				 │                                   │
				 ▼                                   ▼
┌──────────────────┐                ┌──────────────────┐
│   H2 Database    │                │    Supabase      │
│   (In-Memory)    │                │   PostgreSQL     │
│                  │                │   (Persistent)   │
│ • Auto-created   │                │ • Schema         │
│ • Reset on       │                │   validated      │
│   restart        │                │ • Data persists  │
│ • Fast           │                │ • Scalable       │
└──────────────────┘                └──────────────────┘

H2 Console:                          Environment Vars:
http://localhost:8080/               SPRING_PROFILES_ACTIVE=prod
h2-console                           SPRING_DATASOURCE_URL=...
																		 SPRING_DATASOURCE_USERNAME=...
																		 SPRING_DATASOURCE_PASSWORD=...
```

---

## 🔒 Security Notes

### ⚠️ IMPORTANT
- The production credentials in `application-prod.properties` are **fallback values**
- **Always** override with environment variables in production
- **Never** commit real production passwords to version control
- The Dockerfile automatically sets `SPRING_PROFILES_ACTIVE=prod`

### Best Practices
1. Use environment variables for all production credentials
2. Rotate database passwords regularly
3. Use secrets management in production platforms
4. Monitor database connections and access logs

---

## 📚 Additional Documentation

- **PROFILES-README.md** - Comprehensive profile documentation
- **REFACTORING-SUMMARY.md** - Detailed list of changes made
- **README-Docker.md** - Docker-specific documentation

---

## 🐛 Troubleshooting

### Problem: App uses wrong database
**Solution:** Check which profile is active in the logs:
```
The following profiles are active: local
```

### Problem: Can't access H2 Console
**Solution:** 
- Make sure you're using `local` profile
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:contactrequestdb`
- Username: `sa`, Password: (empty)

### Problem: Production database connection fails
**Solution:**
- Verify `SPRING_PROFILES_ACTIVE=prod` is set
- Check environment variables are correct
- Verify Supabase credentials
- Check network connectivity and firewall rules

### Problem: Build fails with Java version error
**Solution:**
- Docker uses Java 17 (correct)
- If building locally, you may have Java 23+
- The pom.xml uses Lombok edge-SNAPSHOT for compatibility
- Docker build will work correctly

---

## ✨ Summary

✅ **Local profile** uses H2 - automatic, fast, no setup  
✅ **Production profile** uses Supabase - secure, persistent  
✅ **Docker** automatically uses production profile  
✅ **Environment variables** override defaults in production  
✅ **H2 Console** available in local mode only  
✅ **Schema validation** in production prevents accidents  

---

**Your app is now configured for both local development and production deployment!** 🎉