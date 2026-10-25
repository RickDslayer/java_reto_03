# Reto 3 · Brigada de reciclaje escolar

Una brigada registra entregas de papel y plástico, calcula puntos y necesita retirar registros cargados por error. Construyan un programa que no duplique entregas y recalcule sus totales cuando se elimina una.

**Modalidad:** trabajo grupal en casa. El docente asignará el reto, el tamaño del grupo y la fecha de entrega. Estos cinco retos son alternativas; cada grupo desarrolla el que le corresponda.

## Antes de escribir código

1. En el README del grupo, **identifiquen el problema**: ¿quién necesita la aplicación?, ¿qué dificultad tiene?, ¿qué debe resolver y qué queda fuera? Enumeren entradas, salidas y reglas que siempre deben cumplirse.
2. Identifiquen las responsabilidades y el estado de cada objeto. Propongan una base abstracta con dos variantes y una interfaz pequeña para producir fichas o reportes. Justifiquen «es un» frente a «usa». No creen herencia solo para compartir dos campos.
3. **Elaboren el diagrama UML de clases antes de programar.** Inclúyanlo en su README con Mermaid `classDiagram` o como imagen legible guardada en el repositorio. Debe mostrar clases, atributos importantes con tipos y visibilidad, métodos principales con parámetros y retornos, herencia, interfaz, relaciones de uso y la clase genérica con su parámetro de tipo. Ustedes diseñan el diagrama: no hay un UML resuelto para copiar.
4. Conserven el UML inicial en el historial Git o en una sección/archivo identificado. Al finalizar, actualicen el diagrama para que coincida con el código y expliquen brevemente los cambios, o indiquen que el diseño inicial sigue vigente.
5. Justifiquen una estructura para cada necesidad: **arreglo de cinco datos fijos**, **List tipada de objetos de cantidad variable** y **clase genérica propia**. No usen arreglos paralelos para separar los atributos de un mismo objeto.

## Condiciones de implementación

- Usen IntelliJ IDEA, JDK 21 o 17 y clases con constructor, campos privados y operaciones que protejan sus reglas. Main prepara los escenarios y muestra resultados; no debe contener toda la lógica.
- Una base abstracta y dos subclases deben expresar la variación indicada en el reto. Una interfaz de ficha o reporte debe tener al menos una implementación efectiva. Recorran objetos mediante referencias base y usen un cliente que reciba el contrato; eviten `if` para decidir qué subtipo ejecutar.
- Usen variables, decisiones, ciclos, operaciones y métodos de String. Programen contra `List<T>`, con `ArrayList<>` como opción inicial; expliquen por qué la eligen. No necesitan implementar también LinkedList.
- Diseñen una clase genérica propia, por ejemplo `Evidencia<T>`, que guarde una descripción y un contenido inicializado. Úsenla con `Integer` para el resumen numérico indicado y con `String` para una conclusión del reto; consulten y muestren ambos. Usar solo `List<Entidad>` no cumple este requisito. No usen tipos raw ni casts para mezclar tipos.
- Los IDs son obligatorios, no nulos ni en blanco. Normalicen con `strip()` y `toUpperCase(Locale.ROOT)` al registrar **y al buscar**. Rechacen IDs repetidos tras normalizar. Los nombres pueden repetirse; los IDs no se cambian después del registro. Busquen por ID con un ciclo y `equals`, sin depender de `contains` sobre objetos.
- Rechacen datos inválidos antes de agregarlos y operaciones inválidas antes de cambiar el estado. Las acciones aceptadas o rechazadas deben indicar su resultado, por ejemplo con `boolean`. Si una búsqueda no encuentra el ID, muestren `No encontrado` y no intenten usar un objeto inexistente.
- La lista vacía debe producir contadores y totales cero. Si no hay elementos para calcular un promedio, máximo o líder, indiquen `Sin datos`. No dividan entre cero. No entreguen acceso modificable a las listas o arreglos internos.
- Registren los cinco valores del arreglo en orden de días 1–5, correspondientes a índices 0–4. Cero es un dato válido. Antes de corregir o consultar una posición, comprueben su rango; rechacen posiciones inexistentes sin alterar el arreglo. Si empatan máximos, informen el **primer día**. No ordenen el arreglo: cada posición representa un día.
- Menús y Scanner son opcionales. Debe existir un Main que reproduzca las pruebas del enunciado con datos fijos. Si añaden entrada interactiva, validen los rangos; recuperar texto no numérico es opcional.
- No usen Map, Set, enum, streams, lambdas, patrones, bases de datos, archivos de persistencia, interfaces gráficas, Maven/Gradle ni dependencias externas. No se exige manejo formal de excepciones ni JUnit.

## Lo que deben construir

1. Modelen una entrega con ID y peso entero entre **100 y 10000 gramos**. Usen una base abstracta y dos variantes: papel y plástico.
2. Papel otorga **1 punto por cada 100 gramos completos**; plástico, **2 puntos por cada 100 completos**. El sobrante no da puntos. Por ejemplo, 450 gramos de plástico representan cuatro bloques completos y otorgan 8 puntos.
3. Registren entregas en una lista y permitan agregar, buscar, retirar por ID y listar comprobantes en orden de registro. Cada comprobante muestra ID, material, gramos y puntos. La búsqueda debe respetar la normalización común de IDs.
4. Rechacen duplicados y pesos inválidos antes de agregar. Retirar un ID inexistente se rechaza y conserva la lista. Una entrega retirada deja de participar en todos los totales.
5. Calculen cantidad de entregas, peso y puntos **de la lista actual**. No mantengan acumuladores históricos que sigan contando entregas retiradas.
6. Guarden gramos recolectados de cinco días en un arreglo, de **0 a 100000** por día. Informen total, promedio decimal, máximo y primer día del máximo. Son estadísticas históricas independientes de las entregas actualmente registradas.
7. Muestren evidencias genéricas con puntos vigentes del escenario como Integer y una conclusión textual como String. No hay pagos ni conversión de puntos a dinero.

## Escenario base obligatorio

Registren papel `R-01` de **750 gramos** y plástico `R-02` de **450 gramos**.

| Momento | Entregas | Peso total | Puntos |
| --- | --- | --- | --- |
| Antes de retirar | 2 | 1200 g | 15: papel 7 y plástico 8 |
| Retirar R-01: aceptado | 1 | 450 g | 8 |
| Retirar R-01 otra vez: rechazado | 1 | 450 g | 8 |

La evidencia numérica final contiene **8**. Los reportes no deben incluir el papel retirado.

Con el arreglo `[1200, 800, 0, 2000, 1000]`: total **5000 g**, promedio **1000.0 g**, máximo **2000 g**, día **4**.

**Pruebas adicionales:** pesos 99 y 10001 rechazados; 100 y 10000 aceptados en ejecuciones separadas. Para 100 gramos, papel otorga 1 punto y plástico 2. Prueben ID duplicado normalizado. En lista vacía: cero entregas, gramos y puntos.

## Pruebas y entrega del grupo

1. Ejecuten el escenario base y comparen sus resultados con los de este enunciado. No mezclen las pruebas adicionales con ese escenario: usen objetos o ejecuciones nuevas.
2. Prueben al menos un caso normal, un límite exacto, un dato inválido, una operación rechazada que conserve el estado y la lista vacía. Agreguen la búsqueda de un ID inexistente y una posición fuera del arreglo.
3. Registren en el README: entradas, resultado esperado, resultado obtenido y si coincide. Expliquen un breakpoint utilizado en una regla y qué variable observaron antes y después; las capturas son opcionales.
4. Entreguen **un repositorio del grupo** con fuentes Java y README: integrantes y aportes, identificación del problema, reglas, UML inicial, decisiones de estructuras, instrucciones reales de ejecución (paquete y Main), pruebas y UML final coherente con el código.
5. Cada integrante debe poder explicar el flujo principal y una regla aunque repartan las tareas. Compartan el enlace por Campus según indique el docente. No incluyan compilados ni configuraciones personales del IDE.

Pueden elegir nombres y diseños distintos si cumplen las reglas y pueden explicarlos. El reto requiere analizar y diseñar; no hay plantilla TODO para completar.
