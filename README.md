# security_demo

A small Spring Boot project I built **for learning**. Three things in one place:

1. **Simple security** — Spring Security with HTTP Basic, BCrypt, and role-based `@PreAuthorize`.
2. **Events** — `@TransactionalEventListener` writes an audit trail when users are registered, deleted, or change role.
3. **Caching** — `@Cacheable` / `@CacheEvict` on read-heavy services, invalidated on writes.

Bonus: Bucket4j rate limiting on register and admin endpoints.

Not for production — uses HTTP Basic, in-memory H2, and an in-memory cache.

## How to run

Requires Java 17.

```bash
git clone git@github.com:rimapaulione/security_demo.git
cd security_demo
./mvnw spring-boot:run
```

Then open:

- Swagger UI — http://localhost:8080/swagger-ui/index.html
- H2 console — http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, no password)

Seed users are loaded from `src/main/resources/users.json` on startup. Use Swagger's **Authorize** button (HTTP Basic) to call protected endpoints.

## Stack

Java 17 · Spring Boot 3.4 · Spring Security · Spring Data JPA · Spring Cache · H2 · Lombok · MapStruct · springdoc-openapi · Bucket4j
