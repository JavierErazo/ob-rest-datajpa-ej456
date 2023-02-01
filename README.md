## Spring Boot

Proyecto Spring Boot con las dependencias/starters:
    
Starters para Persistencia (almacenamiento en bd de la informacion): 
* H2
* Spring Data JPA
Starters para web:
* Spring Web
* Spring Boot DevTools

Aplicación API REST con acceso a BD H2 para persistir la información

El acceso se puede realizar desde Postman o navegador.

## Entidad Book

1. Book
2. BookRepository
3. BookController
   1. Buscar todos los libros
   2. Buscar un solo libro
   3. Crear un nuevo libro
   4. Actualizar un libro existente
   5. Borrar un libro
   6. Borrar todos los libros