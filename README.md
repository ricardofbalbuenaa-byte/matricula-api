# API Matrícula - Spring Boot

Proyecto académico para la gestión de matrículas, desarrollado con:

- Spring Boot
- JPA / Hibernate
- SQL Server
- Java 21
- Stream API y Map<K,V>

---

## Funcionalidades

### Estudiantes
- CRUD completo
- Validación de DNI único
- Listado ordenado por edad (descendente)

### Cursos
- CRUD completo
- Campos: nombre, siglas, estado

### Matrículas
- Registro de matrícula por estudiante
- Detalle por cursos con aula
- Validaciones:
  - No matricular al mismo estudiante en el mismo curso
  - Estudiante debe existir
  - Curso debe existir

### Consultas especiales
- Listar cursos con sus estudiantes (Map<K,V>)
- Uso de Streams y sorted

---

## Configuración de Base de Datos

1. Abrir SQL Server Management Studio  
2. Ejecutar el script incluido en el proyecto:

  Ruta del script:
```
src/main/resources/db/01_creacion.sql
```

Este script crea:
- Base de datos academia  
- Usuario SQL matricula_user
  
## Endpoints principales
- Estudiantes
  POST /estudiantes
  {
    "nombres": "Carlos",
    "apellidos": "Lopez",
    "dni": "77777777",
    "edad": 22
  }
  GET /estudiantes
  GET /estudiantes/ordenados-edad (ordenen por edad de manera descendente)
  PUT /estudiantes/{id}
  {
    "nombres": "Carlos",
    "apellidos": "Lopez",
    "dni": "77777777",
    "edad": 23
  }
  DELETE /estudiantes/{id}

- Cursos
  POST /cursos
  {
    "nombre": "Programación Avanzada",
    "siglas": "PROG2",
    "estado": true
  }
  GET /cursos
  PUT /cursos/{id}
  {
    "nombre": "Programación Avanzada",
    "siglas": "PROG2",
    "estado": false
  }
  DELETE /cursos/{id}

- Matrículas
  POST /matriculas
  {
    "estudianteId": 1,
    "detalle": [
      {
        "cursoId": 1,
        "aula": "A101"
      }
    ]
  }
  GET /matriculas
  GET /reportes/cursos-estudiantes (muestra una lista de cursos y los estudiantes registrados)
