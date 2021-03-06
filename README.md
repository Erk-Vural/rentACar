# Rent A Car Application

RentACar is a layered Architecture Back-end Application. It's developed as a starter/basics project for Spring
framework, back-end development and Java clean code exercises.

_**Crow's foot diagram for RentACar**_
![rentacar-crow's foot diagram](rentacar(crow's%20foot%20diagram).png)

**_UML Class diagram for RentACar_**
![rentacar-uml class diagram](rentacar(UML%20class%20diagram).png)

### Notes:

- Enabled caching and implemented it in Controller's getAll() methods for increasing performance. getAll() method' s
  response not going to change much, so it is a good method to experiment with. Created **_ApplicationCacheConfig_** in
  core/config to create a cacheManager in it, I created **_ConcurrentMapCaches_** for every data set that I want to
  cache.

## Technologies Used

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-test
- postgresql
- lombok
- modelmapper
- openapi-ui
- spring-boot-starter-cache
- spring-context
