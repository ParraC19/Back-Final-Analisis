# MetricSales — Backend

API REST desarrollada en **Spring Boot** que forma parte de un sistema de análisis de ventas. El backend se encarga del **CRUD de usuarios vendedores** y del **registro y consulta de ventas y productos**, implementado bajo **arquitectura hexagonal**.

---

## Tecnologías utilizadas

- **Java 25** con **Spring Boot 3.5**
- **Spring Data JPA** — Persistencia con Hibernate
- **H2 Database** — Base de datos en memoria
- **Spring Security Crypto** — Encriptación de contraseñas con BCrypt
- **Lombok** — Reducción de boilerplate
- **SpringDoc OpenAPI** — Documentación automática con Swagger
- **Maven** — Gestión de dependencias

---

## Arquitectura hexagonal

El proyecto sigue el patrón de **puertos y adaptadores** (Hexagonal Architecture), separando claramente el dominio de la infraestructura:

```
src/main/java/com/example/Back_Final_Analisis/
├── domain/
│   ├── model/
│   │   ├── User.java          # Entidad de dominio: usuario vendedor
│   │   ├── Product.java       # Entidad de dominio: producto
│   │   └── Sale.java          # Entidad de dominio: venta (con SaleItem)
│   ├── port/
│   │   ├── UserRepositoryPort.java     # Puerto de salida para usuarios
│   │   ├── ProductRepositoryPort.java  # Puerto de salida para productos
│   │   └── SaleRepositoryPort.java     # Puerto de salida para ventas
│   └── enums/
│       ├── Role.java           # VENDEDOR
│       ├── SaleType.java       # LOCAL, DOMICILIO
│       └── Tiendas.java        # AMERICANINO, CHEVIGNON, RIFLE...
├── application/
│   └── usecase/
│       ├── UserUseCase/
│       │   ├── CreateUserUseCase.java   # Registro: encripta clave, asigna rol y código
│       │   ├── LoginUseCase.java        # Autenticación con BCrypt
│       │   ├── GetAllUsersUseCase.java
│       │   ├── GetUserByIdUseCase.java
│       │   └── GetVendorByCodeUseCase.java
│       ├── ProductUseCase/
│       │   ├── GetAllProductsUseCase.java
│       │   └── GetProductByIdUseCase.java
│       └── SaleUseCase/
│           ├── CreateSaleUseCase.java   # Valida stock, enriquece items, calcula total
│           ├── GetAllSalesUseCase.java
│           └── GetSaleByIdUseCase.java
└── infrastructure/
    ├── adapter/
    │   ├── input/
    │   │   ├── controller/
    │   │   │   ├── AuthController.java      # POST /api/auth/login
    │   │   │   ├── UserController.java      # CRUD /api/users
    │   │   │   ├── ProductController.java   # GET /api/products
    │   │   │   ├── SaleController.java      # /api/sales
    │   │   │   ├── CorsConfig.java          # Configuración CORS
    │   │   │   └── GlobalExceptionHandler.java
    │   │   └── dto/
    │   │       ├── LoginRequestDTO.java
    │   │       └── LoginResponseDTO.java
    │   └── output/persistence/
    │       ├── Entity/
    │       │   ├── UserEntity.java
    │       │   ├── ProductEntity.java
    │       │   ├── SaleEntity.java
    │       │   └── SaleItemEmbeddable.java  # @Embeddable para items de venta
    │       ├── JpaRepo/
    │       │   ├── JpaUserRepository.java
    │       │   ├── JpaProductRepository.java
    │       │   └── JpaSaleRepository.java
    │       └── RepositoryAdapter/
    │           ├── UserRepositoryAdapter.java
    │           ├── ProductRepositoryAdapter.java
    │           └── SaleRepositoryAdapter.java
    └── config/
        └── PasswordEncoderConfig.java       # Bean de BCryptPasswordEncoder
```

---

## Endpoints disponibles

### Autenticación
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/login` | Inicia sesión con email y contraseña |

### Usuarios
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/users` | Registrar nuevo vendedor |
| GET | `/api/users` | Listar todos los usuarios |
| GET | `/api/users/{id}` | Obtener usuario por ID |
| GET | `/api/users/vendor/{vendorCode}` | Obtener vendedor por código |

### Productos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/products` | Listar todos los productos |
| GET | `/api/products/{id}` | Obtener producto por ID |

### Ventas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/sales` | Registrar una venta |
| GET | `/api/sales` | Listar todas las ventas |
| GET | `/api/sales/{id}` | Obtener venta por ID |

---

## Lógica de negocio destacada

### Registro de usuario (`CreateUserUseCase`)
- Verifica que el email no esté registrado
- Encripta la contraseña con **BCrypt**
- Asigna automáticamente el rol `VENDEDOR`
- Genera un código único: `VEN-{id}` (ej: `VEN-1`, `VEN-2`)

### Registro de venta (`CreateSaleUseCase`)
- Valida que ventas LOCAL tengan tienda y DOMICILIO tengan dirección
- Enriquece cada ítem con nombre del producto, precio unitario y subtotal
- Verifica y descuenta el stock de cada producto vendido
- Calcula el total de la venta automáticamente

---

## Base de datos

Se usa **H2 en memoria**. Las tablas se crean automáticamente con Hibernate (`ddl-auto=update`) y los **24 productos iniciales** se insertan desde `src/main/resources/data.sql` al arrancar la aplicación.

Consola H2 disponible en: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa` / Contraseña: *(vacía)*

---

## Documentación Swagger

Disponible en: `http://localhost:8080/swagger-ui.html`

---

## Cómo ejecutar

### Requisitos
- Java 17+
- Maven 3.8+

### Pasos

```bash
# Compilar y ejecutar
mvn spring-boot:run
```

El servidor arranca en `http://localhost:8080`.

> Si el puerto 8080 está ocupado, ejecuta en terminal:
> ```bash
> FOR /F "tokens=5" %a IN ('netstat -ano ^| findstr :8080') DO taskkill /PID %a /F
> ```
