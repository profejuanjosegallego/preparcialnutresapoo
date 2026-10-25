# Examen práctico POO: El nacimiento del ligre 🦁 + 🐯 = 🦁🐯

| | |
|---|---|
| **Duración** | 90 minutos |
| **Lenguaje** | Java 17 o superior |
| **Puntaje** | 100 puntos |
| **Modalidad** | Individual |

---

## 📖 Historia

El **Santuario POO** cuida felinos. Leo es un león, Tania es una tigresa y Simba es un león joven. El sistema del santuario debe registrar a sus animales, calcular cuánta comida necesitan al día y decidir si Leo y Tania pueden tener una cría. El hijo de un león macho y una tigresa hembra se llama **ligre**.

El problema: el programador anterior dejó el proyecto **roto**. No compila, varios métodos dan resultados equivocados y la parte más importante, el registro de nacimientos, quedó sin hacer. Tu trabajo es dejarlo funcionando.

---

## ⚠️ Lee esto antes de empezar

Este examen se resuelve **por fases y en orden**. No pases a la siguiente fase hasta terminar la anterior.

```
 FASE 1          FASE 2          FASE 3            FASE 4            FASE 5
 Sintaxis   →    Lógica     →    Encapsulamiento → Nacimiento   →    Pruebas y
 (compila)       (resultados     (validaciones)    (regla de         evidencias
                  correctos)                        negocio)
```

¿Por qué en este orden? Porque **no puedes probar la lógica de un programa que no compila**, y no puedes confiar en tu regla de nacimiento si los métodos que usa (`esAdulto()`, `buscarPorCodigo()`...) devuelven resultados equivocados.

> 💡 **Haz un commit al terminar cada fase** con un mensaje como `Fase 1: corrige errores de sintaxis`. El historial de commits forma parte de la evaluación.

---

## 🗂️ Estructura del proyecto

```text
src/
  Main.java                  ← programa principal y pruebas
  modelo/
    Identificable.java       ← interfaz
    Felino.java              ← clase abstracta (padre de todos)
    Leon.java                ← hereda de Felino
    Tigresa.java             ← hereda de Felino
    Ligre.java               ← hereda de Felino, tiene padre y madre
  servicio/
    Santuario.java           ← administra la lista de felinos
```

### Diagrama de clases

```text
          «interface»
         Identificable
     + getCodigo(): String
     + obtenerFicha(): String
                ▲
                │ implements
        «abstract» Felino
  - codigo: String          + esAdulto(): boolean
  - nombre: String          + cumplirAnios(): void
  - edad: int               + obtenerFicha(): String
  - pesoKg: double          + hacerSonido(): void            «abstract»
  - sano: boolean           + calcularRacionDiaria(): double «abstract»
                ▲
     ┌──────────┼───────────────┐
     │          │               │
   Leon      Tigresa          Ligre
 - macho    - hembra        - padre: Leon
 - tieneMelena - numeroRayas - madre: Tigresa
 ración 5%  ración 4%        ración 6%
 del peso   del peso         del peso


 Santuario
  - nombre: String
  - capacidadMaxima: int
  - felinos: ArrayList<Felino>
  - totalNacimientos: int
  + agregarFelino(Felino): boolean
  + buscarPorCodigo(String): Felino
  + contarSanos(): int
  + calcularAlimentoTotal(): double
  + mostrarFelinos(): void
  + registrarNacimiento(Leon, Tigresa, String): Ligre
```

---

## ▶️ Compilar y ejecutar

Desde la **raíz del proyecto** (la carpeta que contiene `src/`):

```bash
javac -encoding UTF-8 -d out src/modelo/*.java src/servicio/*.java src/Main.java
java -cp out Main
```

> Si usas un IDE (IntelliJ, VS Code, NetBeans, Eclipse) también puedes ejecutar `Main` directamente, pero **comprueba al menos una vez con los comandos de arriba**.

---

## 🔧 FASE 1: Errores de sintaxis (20 pts) · ⏱ ~20 min

El proyecto tiene **exactamente 12 errores de compilación** repartidos en los 7 archivos. Corrígelos hasta que el comando `javac` termine **sin ningún mensaje**.

**Cómo trabajar:**

1. Compila y lee el **primer** error: archivo, número de línea y mensaje.
2. Ve a esa línea, entiende qué dice el mensaje y corrige.
3. Vuelve a compilar. Repite.

> 🧠 **Los errores aparecen por oleadas.** Java primero revisa que el código esté bien escrito (`;`, paréntesis, `+`...). Solo cuando eso está bien revisa tipos, herencia e interfaces. Así que es normal ver 4 errores, corregirlos, y que aparezcan más de 10 nuevos (un mismo error puede generar varios mensajes). **No te asustes: vas avanzando.**

**Pistas de los tipos de error que vas a encontrar:**

| Mensaje del compilador | Qué suele significar |
|---|---|
| `';' expected` | Falta un punto y coma (fíjate en la línea anterior también) |
| `not a statement` | Algo mal escrito en una expresión, por ejemplo una concatenación incompleta |
| `cannot find symbol` | Un nombre mal escrito, o falta un `import` |
| `abstract methods cannot have a body` | Un método abstracto no lleva `{ }` |
| `incompatible types: possible lossy conversion` | Intentas guardar un `double` en un `int` |
| `incompatible types: X cannot be converted to Y` | El tipo que retornas no coincide con el declarado |
| `method does not override or implement a method from a supertype` | El `@Override` no coincide con la firma del método padre |
| `is not abstract and does not override abstract method` | Una clase concreta no implementó un método abstracto |
| `constructor X in class X cannot be applied to given types` | Llamas a un constructor con argumentos de más o de menos |
| `variable X might not have been initialized` | Un atributo `final` nunca recibió valor en el constructor |

**Reglas de la fase 1:**

- ❌ No borres clases, métodos, atributos ni interfaces para "hacer que compile".
- ❌ No cambies `abstract`, `extends` ni `implements`.
- ✅ Corrige el error **respetando la intención** del código (lee los comentarios `/** ... */`, te dicen qué debe hacer cada método).

✔️ **Terminaste la fase 1 cuando:** `javac` no muestra errores y `java -cp out Main` ejecuta.

---

## 🐞 FASE 2: Errores de lógica (20 pts) · ⏱ ~20 min

Ahora el programa compila... pero **miente**. Hay **exactamente 5 errores de lógica**: código que Java acepta, pero que no hace lo que dice su comentario. Están en `Felino.java` y `Santuario.java`.

Ejecuta el programa y compara la sección `=== 1 ===` y `=== 2 ===` con la tabla. Cada fila que no coincida es una pista.

| Qué se prueba | Resultado esperado |
|---|---|
| Peso que muestran las fichas de Leo, Tania y Simba | `190.0 kg`, `140.0 kg`, `160.0 kg` |
| `¿Se aceptó un código repetido?` | `false` (y el santuario queda con **3** felinos, no 4) |
| `¿Simba (3 años) es adulto?` | `true` (adulto es **3 años o más**) |
| `Buscar TIG-001` | `Tania` |
| `Felinos sanos` | `3` |
| `Alimento diario total` | `23.10 kg` (Leo 9.5 + Tania 5.6 + Simba 8.0) |
| `¿Cabe otro felino?` (sección 5, con el santuario lleno 4/4) | `false` |

> 📝 Algunos errores **esconden** a otros: cuando arregles uno, vuelve a ejecutar y revisa toda la tabla de nuevo.
>
> 💡 Según la configuración de tu computador, los decimales pueden verse con coma (`23,10`). Eso es correcto.
>
> ℹ️ La fila `¿Cabe otro felino?` solo se puede comprobar cuando termines la fase 4 (Milo debe nacer para que el santuario quede lleno). Puedes adelantarla probando tú mismo con un santuario pequeño.

**Para cada error lógico encontrado**, escribe en `EVIDENCIAS.md`: el archivo y método, qué hacía mal y cómo lo corregiste (una o dos líneas).

✔️ **Terminaste la fase 2 cuando:** las secciones 1 y 2 de la salida coinciden con la tabla.

---

## 🔒 FASE 3: Encapsulamiento (15 pts) · ⏱ ~10 min

Un objeto debe **protegerse a sí mismo** de datos inválidos. En `Felino.java`, completa los `TODO (Fase 3)`:

| Setter | Regla | Si se viola |
|---|---|---|
| `setNombre` | No puede ser `null`, vacío ni solo espacios | `IllegalArgumentException` |
| `setEdad` | No puede ser negativa | `IllegalArgumentException` |
| `setPesoKg` | Debe ser mayor que 0 | `IllegalArgumentException` |

Usa como modelo la validación del `codigo` que ya está en el constructor:

```java
if (codigo == null || codigo.trim().isEmpty()) {
    throw new IllegalArgumentException("El código no puede estar vacío");
}
```

**Reglas:**

- Los atributos deben seguir siendo `private`.
- El constructor ya usa los setters: así, **ningún felino puede crearse con datos inválidos**. No cambies eso.
- `cumplirAnios()` debe seguir funcionando a través de `setEdad`.

✔️ **Terminaste la fase 3 cuando:** la sección `=== 4 ===` muestra `Rechazado: ...` en lugar de `ERROR: ...`.

---

## 🧬 FASE 4: Reto lógico, el nacimiento (25 pts) · ⏱ ~25 min

Implementa `Santuario.registrarNacimiento(Leon padre, Tigresa madre, String nombreCria)`.

### Reglas para que nazca un ligre

Deben cumplirse **todas**:

1. Ni el `padre` ni la `madre` son `null`.
2. `nombreCria` no es `null`, ni vacío, ni solo espacios.
3. El padre es **macho** y la madre es **hembra**.
4. Ambos son **adultos** (usa `esAdulto()`, ya corregido en la fase 2).
5. Ambos están **sanos**.
6. Ambos están **registrados en este santuario** (usa `buscarPorCodigo()`).
7. El santuario **no está lleno** (la cría también ocupa un lugar).

### Si alguna regla falla

- Imprime por consola un mensaje que explique **cuál regla falló**, por ejemplo `No puede nacer: ambos progenitores deben estar sanos.`
- Retorna `null`. **No se crea ningún objeto.**

### Si todas se cumplen

1. Crea un `Ligre` con un código generado por `generarCodigoCria()` (ya existe), el nombre recibido y sus padres.
2. Agrégalo a la lista del santuario.
3. Suma 1 a `totalNacimientos`.
4. Imprime **exactamente**: `¡Nació un ligre y se llama Milo!` (con el nombre real de la cría).
5. **Retorna el objeto creado.**

> ⚠️ **No basta con imprimir el mensaje.** Si tu método imprime "¡Nació...!" pero retorna `null` o no agrega la cría al santuario, la fase no cuenta.

✔️ **Terminaste la fase 4 cuando:** en la sección `=== 5 ===` aparece el mensaje del nacimiento, la ficha de Milo (`LIG-001`, `0 años`, `3.3 kg`, `hijo de Leo y Tania`) y `¿Cabe otro felino? false`.

---

## 🧪 FASE 5: Pruebas y demostración (10 pts) · ⏱ ~10 min

En `Main.java`, completa los `TODO (Fase 5)`:

1. **Encapsulamiento:** demuestra con `try/catch` (igual que el ejemplo de la edad) que también se rechazan un **nombre vacío** y un **peso de 0**.
2. **Nacimientos fallidos:** prueba **al menos 3 escenarios diferentes** que **no** permitan el nacimiento, cada uno por un motivo distinto. Ideas: un león de 2 años, una tigresa enferma (`setSano(false)`), un nombre vacío, un león que no está registrado, el santuario lleno...
3. Verifica que `Total de nacimientos` al final sea **1**.

> 💡 Ojo con el orden: después del nacimiento de Milo el santuario queda **lleno**. Piensa qué mensaje esperas en cada caso y comprueba que tu programa diga el motivo correcto.

---

## 📄 Entregable: `EVIDENCIAS.md` (10 pts)

Crea un archivo `EVIDENCIAS.md` en la raíz del proyecto con esta estructura:

```markdown
# Evidencias: <tu nombre completo>

## 1. Errores de sintaxis corregidos
| # | Archivo | Línea | Error | Corrección |
|---|---------|-------|-------|------------|
| 1 | ...     | ...   | ...   | ...        |
(las 12 filas)

## 2. Errores de lógica corregidos
| # | Archivo y método | Qué hacía mal | Cómo lo corregí |
|---|------------------|---------------|-----------------|
(las 5 filas)

## 3. Salida final del programa
(pega aquí la salida completa de `java -cp out Main`, o una captura)

## 4. Casos fallidos probados
- Caso 1: ... → mensaje obtenido: ...
- Caso 2: ...
- Caso 3: ...

## 5. Conceptos de POO en este proyecto
Explica brevemente (2-3 líneas cada uno) DÓNDE se usa en este código:
- **Encapsulamiento:**
- **Herencia:**
- **Abstracción (clase abstracta):**
- **Interfaz:**
- **Polimorfismo:**
```

---

## 📊 Rúbrica

| Fase | Criterio | Pts |
|---|---|---|
| 1 | El proyecto compila; los 12 errores están corregidos respetando la intención del código | 20 |
| 2 | Los 5 errores lógicos están corregidos; la salida coincide con la tabla esperada | 20 |
| 3 | Los 3 setters validan y lanzan `IllegalArgumentException`; atributos privados | 15 |
| 4 | `registrarNacimiento` cumple las 7 reglas, informa el motivo, crea, agrega y **retorna** el `Ligre` | 25 |
| 5 | `Main` prueba nombre vacío, peso 0 y al menos 3 nacimientos fallidos distintos | 10 |
| — | `EVIDENCIAS.md` completo y commits por fase | 10 |
| | **Total** | **100** |

**Penalizaciones:**

- Eliminar clases, métodos, interfaces o relaciones de herencia: **−10 por cada caso**.
- Reemplazar la lógica por mensajes fijos (ej. `System.out.println("¡Nació un ligre y se llama Milo!")` sin crear el objeto): **la fase 4 vale 0**.
- Cambiar los datos de prueba de `Main` para que "coincida" la tabla: **la fase 2 vale 0**.

---

## 📏 Reglas generales

- ✅ Puedes agregar métodos auxiliares (`private`) y pruebas propias en `Main`.
- ✅ Puedes consultar tus apuntes y la documentación oficial de Java.
- ❌ No cambies las **firmas públicas** de los métodos (nombre, parámetros y tipo de retorno), salvo cuando eso sea justamente el error de sintaxis.
- ❌ No uses bibliotecas externas, asistentes de IA, herramientas de reparación automática ni código de otra persona.
- ❌ No modifiques los datos de prueba que ya vienen en `Main` (puedes **agregar** nuevos).

---

## 🚀 Entrega

1. Sube tu proyecto a tu repositorio con **un commit por fase** (mínimo 5 commits).
2. Verifica que el repositorio tenga `src/` corregido y `EVIDENCIAS.md`.
3. Entrega el enlace del repositorio al docente antes de que termine el tiempo.

**¡Éxitos! 🦁🐯**
