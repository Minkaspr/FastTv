

## Validaciones
1. Validación a nivel de aplicación: <br>
   Se realiza en la capa de aplicación antes de que los datos lleguen a la base de datos. Se utiliza Bean Validation (como @NotNull, @Size, etc.) para esta validación.
2. Validación a nivel de base de datos: <br>
   Se realiza en la base de datos misma. Se utilizan anotaciones de JPA (como @Column) para definir las restricciones a nivel de columna en la base de datos.

### Aqui tenemos algunas validaciones tanto para JPA y Validation Bean:
1. **No nulo**: Asegura que un campo no puede ser nulo.
   - Bean Validation: `@NotNull`
   - JPA: `@Column(nullable = false)`
2. **Tamaño máximo**: Asegura que la longitud de un String no exceda un cierto límite.
   - Bean Validation: `@Size(max = X)`
   - JPA: `@Column(length = X)`
3. **Valor mínimo y máximo**: Asegura que un valor numérico esté dentro de un cierto rango.
   - Bean Validation: `@Min(X), @Max(X)`
4. **Patrones**: Asegura que un String cumpla con un cierto patrón (por ejemplo, una expresión regular).
   - Bean Validation: `@Pattern(regexp = "X")`
5. **Futuro y pasado**: Asegura que una fecha esté en el futuro o en el pasado.
   - Bean Validation: `@Future`, `@FutureOrPresent`, `@Past`, `@PastOrPresent`
6. **Nombre de la tabla**: Especifica el nombre de la tabla en la base de datos a la que se mapea la entidad.
   - JPA: `@Table(name = "X")`
7. **Restricción única**: Especifica que una columna o un conjunto de columnas deben tener valores únicos.
   - JPA: `@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"X", "Y"}))` | `@Column(unique = true)`
8. **Correo electrónico**: Valida que un campo de texto tenga formato de correo electrónico.
   - Bean Validation: `@Email`
9. **Positivo o cero**: Valida que un número sea positivo o cero.
   - Bean Validation: `@PositiveOrZero` | `@Positive`
10. **Negativo o cero**: Valida que un número sea negativo o cero.
    - Bean Validation: `@NegativeOrZero` | `@Negative`
11. **Dígitos**: Valida que un número sea de una cierta longitud. 
    - Bean Validation: `@Digits(integer = X, fraction = Y)`
12. **No en blanco**: Valida que un campo de texto no esté vacío ni solo contenga espacios en blanco.
    - Bean Validation: `@NotBlank`
13. **Nombre de la columna**: Se especifica el nombre de la columna en la base de datos a la que se mapea el campo
    - JPA: `@Column(name = “X”)`
14. **Operaciones a la columna**: Indica que la columna no debe ser modificada por operaciones de inserción o actualización. Esto puede ser útil para columnas que son gestionadas por la base de datos, como las columnas de timestamp.
    - JPA: `@Column(insertable = false, updatable = false)`
15. **Definición de la columna**: Permite especificar la definición de la columna tal como se usaría en la sentencia SQL CREATE TABLE.
    - JPA: `@Column(name = “X”)`, x → `TEXT | INTEGER | VARCHAR(255) | DECIMAL (10,2) | DATE | BOOLEAN | etc.`