# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/banca/digital/application/service/LoanApplicationServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/banca/digital/infrastructure/controller/LoanApplicationControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest`: El import com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse`: El import com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.ErrorResponse`: El import com.banca.digital.infrastructure.controller.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/digital/infrastructure/adapter/RiskEvaluationAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/digital/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.MDC pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.approved`: Se invoca `approved` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.approvedAmount`: Se invoca `approvedAmount` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.evaluationId`: Se invoca `evaluationId` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.rejectionReason`: Se invoca `rejectionReason` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.id`: Se invoca `id` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.save`: Se invoca `save` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getId`: Se invoca `getId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.findById`: Se invoca `findById` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setStatus`: Se invoca `setStatus` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `Status.name`: Se invoca `name` sobre `Status`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.deleteById`: Se invoca `deleteById` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setId`: Se invoca `setId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setClientId`: Se invoca `setClientId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.clientId`: Se invoca `clientId` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setAmount`: Se invoca `setAmount` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.amount`: Se invoca `amount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setTermMonths`: Se invoca `setTermMonths` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.termMonths`: Se invoca `termMonths` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setPurpose`: Se invoca `setPurpose` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.purpose`: Se invoca `purpose` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.status`: Se invoca `status` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setInterestRate`: Se invoca `setInterestRate` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.interestRate`: Se invoca `interestRate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setMonthlyPayment`: Se invoca `setMonthlyPayment` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.monthlyPayment`: Se invoca `monthlyPayment` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setRiskScore`: Se invoca `setRiskScore` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.riskScore`: Se invoca `riskScore` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setRequestedAt`: Se invoca `setRequestedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.requestedAt`: Se invoca `requestedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setApprovedAt`: Se invoca `setApprovedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.approvedAt`: Se invoca `approvedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getClientId`: Se invoca `getClientId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getAmount`: Se invoca `getAmount` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getTermMonths`: Se invoca `getTermMonths` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getPurpose`: Se invoca `getPurpose` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getStatus`: Se invoca `getStatus` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getInterestRate`: Se invoca `getInterestRate` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getMonthlyPayment`: Se invoca `getMonthlyPayment` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getRiskScore`: Se invoca `getRiskScore` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getRequestedAt`: Se invoca `getRequestedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getApprovedAt`: Se invoca `getApprovedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.createLoanApplication`: Se invoca `createLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplication.id`: Se invoca `id` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findById`: Se invoca `findById` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findByStatus`: Se invoca `findByStatus` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findAll`: Se invoca `findAll` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.approveLoanApplication`: Se invoca `approveLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.rejectLoanApplication`: Se invoca `rejectLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.deleteLoanApplication`: Se invoca `deleteLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Integración y Optimización de Framework en Aplicación Backend
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración Inicial del Framework — objetivo: Configurar el framework para que pueda manejar las solicitudes de préstamos de manera eficiente. — entregable (NO resolver): Configuración inicial del framework con enrutamiento y variables de entorno configuradas.
- Fase 2: Implementación de Registros de Trazas — objetivo: Implementar la generación de registros de trazas para monitorear el flujo de solicitudes. — entregable (NO resolver): Framework configurado para generar registros de trazas en cada solicitud de préstamo.
- Fase 3: Conexión con Base de Datos utilizando ORM — objetivo: Configurar la conexión con la base de datos utilizando un Mapeador Relacional de Objetos (ORM). — entregable (NO resolver): Framework configurado para conectarse con la base de datos utilizando un ORM.
- Fase 4: Optimización y Pruebas — objetivo: Optimizar la configuración del framework y realizar pruebas para asegurar su funcionamiento correcto. — entregable (NO resolver): Framework optimizado y probado, funcionando correctamente y cumpliendo con los requisitos de rendimiento.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.banca</groupId>
    <artifactId>digital-loan-application</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>digital-loan-application</name>
    <description>Backend para gestion de solicitudes de prestamos bancarios</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <scope>test</scope>
            <version>1.20.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/banca/digital/Application.java ===
package com.banca.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: digital-loan-application
  
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: loan_user
    password: loan_password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 30
        order_inserts: true
        order_updates: true

server:
  port: 8080
  tomcat:
    threads:
      max: 200
    connection-timeout: 5s

logging:
  level:
    root: INFO
    com.banca.digital: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg %X{requestId}%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg %X{requestId}%n"
  file:
    name: logs/digital-loan-application.log
  logback:
    rollingpolicy:
      max-file-size: 10MB
      max-history: 7

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus,info
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
  metrics:
    tags:
      application: ${spring.application.name}
  prometheus:
    metrics:
      export:
        enabled: true

app:
  risk-evaluation:
    timeout: 50
    url: http://risk-evaluation-service:8081/evaluate
  core-banking:
    timeout: 100
    url: http://core-banking-service:8082/process

// === ARCHIVO: src/main/java/com/banca/digital/domain/port/RiskEvaluationPort.java ===
package com.banca.digital.domain.port;

import com.banca.digital.domain.model.LoanApplication;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto para interactuar con el motor de evaluación de riesgos.
 * Define el contrato que la capa de infraestructura debe implementar
 * para evaluar el riesgo de una solicitud de préstamo.
 */
public interface RiskEvaluationPort {

    /**
     * Evalúa el riesgo asociado a una solicitud de préstamo.
     *
     * @param loanApplication la solicitud de préstamo a evaluar
     * @return CompletableFuture con el resultado de la evaluación de riesgo
     * @throws RiskEvaluationException si ocurre un error durante la evaluación
     */
    CompletableFuture<RiskEvaluationResult> evaluateRisk(LoanApplication loanApplication);

    /**
     * Resultado de la evaluación de riesgo.
     *
     * @param approved indica si la solicitud fue aprobada
     * @param riskScore puntuación de riesgo calculada
     * @param rejectionReason motivo de rechazo (null si aprobado)
     */
    record RiskEvaluationResult(
        boolean approved,
        double riskScore,
        String rejectionReason
    ) {}
}
"

// === ARCHIVO: src/main/java/com/banca/digital/domain/port/LoanApplicationRepositoryPort.java ===
package com.banca.digital.domain.port;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto para operaciones de persistencia de solicitudes de préstamo.
 * Define el contrato que la capa de infraestructura debe implementar
 * para persistir y recuperar solicitudes de préstamo.
 */
public interface LoanApplicationRepositoryPort {

    /**
     * Guarda una solicitud de préstamo en el repositorio.
     *
     * @param loanApplication la solicitud a guardar
     * @return la solicitud guardada con su ID generado
     * @throws LoanApplicationPersistenceException si ocurre un error al persistir
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud
     * @return Optional con la solicitud si existe
     * @throws LoanApplicationPersistenceException si ocurre un error durante la búsqueda
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Actualiza el estado de una solicitud de préstamo.
     *
     * @param id el ID de la solicitud
     * @param status el nuevo estado
     * @return la solicitud actualizada
     * @throws LoanApplicationPersistenceException si ocurre un error durante la actualización
     */
    LoanApplication updateStatus(UUID id, LoanApplication.Status status);

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a filtrar
     * @return lista de solicitudes con el estado especificado
     * @throws LoanApplicationPersistenceException si ocurre un error durante la búsqueda
     */
    List<LoanApplication> findByStatus(LoanApplication.Status status);

    /**
     * Elimina una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud
     * @throws LoanApplicationPersistenceException si ocurre un error durante la eliminación
     */
    void deleteById(UUID id);
}
"

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/repository/LoanApplicationJpaRepository.java ===
package com.banca.digital.infrastructure.repository;

import com.banca.digital.infrastructure.repository.entity.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para operaciones CRUD sobre la entidad de solicitud de préstamo.
 * Implementa el contrato definido por Spring Data JPA para interactuar con la base de datos.
 */
@Repository
public interface LoanApplicationJpaRepository extends JpaRepository<LoanApplicationEntity, UUID> {

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a filtrar
     * @return lista de entidades con el estado especificado
     */
    List<LoanApplicationEntity> findByStatus(String status);

    /**
     * Cuenta el número de solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a contar
     * @return cantidad de solicitudes con el estado especificado
     */
    long countByStatus(String status);
}

// === ARCHIVO: src/main/java/com/banca/digital/domain/model/LoanApplication.java ===
package com.banca.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanApplication {

    private final UUID id;
    
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private final BigDecimal amount;
    
    @NotNull(message = "El plazo es obligatorio")
    @Positive(message = "El plazo debe ser positivo")
    private final Integer termMonths;
    
    @NotNull(message = "El identificador del cliente es obligatorio")
    private final String customerId;
    
    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private final String customerName;
    
    @NotNull(message = "El correo electrónico es obligatorio")
    private final String customerEmail;
    
    @NotNull(message = "El propósito del préstamo es obligatorio")
    private final String purpose;
    
    private final Status status;
    
    private final LocalDateTime createdAt;
    
    private final LocalDateTime updatedAt;
    
    private final String riskEvaluationId;
    
    private final Boolean riskApproved;
    
    private final BigDecimal approvedAmount;
    
    private final String rejectionReason;

    public LoanApplication(UUID id, BigDecimal amount, Integer termMonths, String customerId, 
                           String customerName, String customerEmail, String purpose, Status status,
                           LocalDateTime createdAt, LocalDateTime updatedAt, String riskEvaluationId,
                           Boolean riskApproved, BigDecimal approvedAmount, String rejectionReason) {
        this.id = id;
        this.amount = amount;
        this.termMonths = termMonths;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.purpose = purpose;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.riskEvaluationId = riskEvaluationId;
        this.riskApproved = riskApproved;
        this.approvedAmount = approvedAmount;
        this.rejectionReason = rejectionReason;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getPurpose() {
        return purpose;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getRiskEvaluationId() {
        return riskEvaluationId;
    }

    public Boolean getRiskApproved() {
        return riskApproved;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public LoanApplication withStatus(Status newStatus) {
        return new LoanApplication(
            this.id, this.amount, this.termMonths, this.customerId,
            this.customerName, this.customerEmail, this.purpose, newStatus,
            this.createdAt, LocalDateTime.now(), this.riskEvaluationId,
            this.riskApproved, this.approvedAmount, this.rejectionReason
        );
    }

    public LoanApplication withRiskEvaluation(String evalId, Boolean approved, BigDecimal approvedAmt, String reason) {
        return new LoanApplication(
            this.id, this.amount, this.termMonths, this.customerId,
            this.customerName, this.customerEmail, this.purpose, this.status,
            this.createdAt, LocalDateTime.now(), evalId, approved, approvedAmt, reason
        );
    }

    public static LoanApplication create(BigDecimal amount, Integer termMonths, String customerId,
                                          String customerName, String customerEmail, String purpose) {
        LocalDateTime now = LocalDateTime.now();
        return new LoanApplication(
            UUID.randomUUID(), amount, termMonths, customerId,
            customerName, customerEmail, purpose, Status.PENDING,
            now, now, null, null, null, null
        );
    }

    public enum Status {
        PENDING("Pendiente de evaluación"),
        RISK_EVALUATION("En evaluación de riesgo"),
        APPROVED("Aprobado"),
        REJECTED("Rechazado"),
        DISBURSED("Desembolsado"),
        CANCELLED("Cancelado");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/application/service/LoanApplicationService.java ===
package com.banca.digital.application.service;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.LoanApplicationRepositoryPort;
import com.banca.digital.domain.port.RiskEvaluationPort;
import com.banca.digital.domain.port.RiskEvaluationPort.RiskEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoanApplicationService {

    private static final Logger log = LoggerFactory.getLogger(LoanApplicationService.class);
    private static final String MAX_AMOUNT_KEY = "MAX_LOAN_AMOUNT";
    private static final BigDecimal MAX_AMOUNT = new BigDecimal(System.getProperty(MAX_AMOUNT_KEY, "50000"));

    private final LoanApplicationRepositoryPort repositoryPort;
    private final RiskEvaluationPort riskEvaluationPort;
    private final ExecutorService executorService;

    public LoanApplicationService(LoanApplicationRepositoryPort repositoryPort,
                                   RiskEvaluationPort riskEvaluationPort) {
        this.repositoryPort = repositoryPort;
        this.riskEvaluationPort = riskEvaluationPort;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public LoanApplication createApplication(BigDecimal amount, Integer termMonths, String customerId,
                                              String customerName, String customerEmail, String purpose) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "createApplication");

        try {
            validateAmount(amount);
            validateTerm(termMonths);
            validateCustomer(customerId, customerName, customerEmail);

            log.info("Iniciando creación de solicitud de préstamo para cliente: {}, monto: {}, plazo: {} meses",
                    customerId, amount, termMonths);

            LoanApplication application = LoanApplication.create(amount, termMonths, customerId,
                    customerName, customerEmail, purpose);

            LoanApplication savedApplication = repositoryPort.save(application);

            log.info("Solicitud de préstamo creada exitosamente con ID: {}", savedApplication.getId());

            return savedApplication;

        } catch (IllegalArgumentException e) {
            log.error("Error de validación al crear solicitud de préstamo: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de préstamo para cliente {}: {}", customerId, e.getMessage(), e);
            throw new RuntimeException("Error al crear la solicitud de préstamo", e);
        } finally {
            MDC.clear();
        }
    }

    public CompletableFuture<LoanApplication> submitForRiskEvaluation(UUID applicationId) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "submitForRiskEvaluation");

        try {
            log.info("Iniciando evaluación de riesgo para solicitud: {}", applicationId);

            Optional<LoanApplication> optApplication = repositoryPort.findById(applicationId);
            if (optApplication.isEmpty()) {
                log.error("Solicitud de préstamo no encontrada: {}", applicationId);
                throw new IllegalArgumentException("Solicitud de préstamo no encontrada: " + applicationId);
            }

            LoanApplication application = optApplication.get();
            if (application.getStatus() != LoanApplication.Status.PENDING) {
                log.warn("La solicitud {} no está en estado PENDING, estado actual: {}", 
                        applicationId, application.getStatus());
                throw new IllegalStateException("La solicitud no está en estado válido para evaluación");
            }

            LoanApplication inEvaluation = application.withStatus(LoanApplication.Status.RISK_EVALUATION);
            repositoryPort.updateStatus(applicationId, LoanApplication.Status.RISK_EVALUATION);

            return CompletableFuture.supplyAsync(() -> {
                try {
                    RiskEvaluationResult result = riskEvaluationPort.evaluateRisk(inEvaluation).get();

                    LoanApplication.Status newStatus = result.approved() 
                            ? LoanApplication.Status.APPROVED 
                            : LoanApplication.Status.REJECTED;

                    BigDecimal approvedAmount = result.approved() 
                            ? result.approvedAmount() != null ? result.approvedAmount() : application.getAmount()
                            : null;

                    LoanApplication updatedApplication = application.withRiskEvaluation(
                            result.evaluationId(), 
                            result.approved(), 
                            approvedAmount,
                            result.rejectionReason()
                    ).withStatus(newStatus);

                    repositoryPort.updateStatus(applicationId, newStatus);

                    log.info("Evaluación de riesgo completada para solicitud {}: aprobado={}, monto aprobado={}",
                            applicationId, result.approved(), approvedAmount);

                    return updatedApplication;

                } catch (Exception e) {
                    log.error("Error en evaluación de riesgo para solicitud {}: {}", applicationId, e.getMessage(), e);
                    repositoryPort.updateStatus(applicationId, LoanApplication.Status.REJECTED);
                    throw new RuntimeException("Error en la evaluación de riesgo", e);
                }
            }, executorService);

        } finally {
            MDC.clear();
        }
    }

    public Optional<LoanApplication> getApplication(UUID applicationId) {
        MDC.put("correlationId", UUID.randomUUID().toString());
        MDC.put("operation", "getApplication");

        try {
            log.debug("Consultando solicitud de préstamo: {}", applicationId);
            return repositoryPort.findById(applicationId);
        } finally {
            MDC.clear();
        }
    }

    public List<LoanApplication> getApplicationsByStatus(LoanApplication.Status status) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "getApplicationsByStatus");

        try {
            log.debug("Consultando solicitudes de préstamo con estado: {}", status);
            return repositoryPort.findByStatus(status);
        } finally {
            MDC.clear();
        }
    }

    public LoanApplication cancelApplication(UUID applicationId) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "cancelApplication");

        try {
            log.info("Cancelando solicitud de préstamo: {}", applicationId);

            Optional<LoanApplication> optApplication = repositoryPort.findById(applicationId);
            if (optApplication.isEmpty()) {
                throw new IllegalArgumentException("Solicitud de préstamo no encontrada: " + applicationId);
            }

            LoanApplication application = optApplication.get();
            if (application.getStatus() == LoanApplication.Status.DISBURSED) {
                throw new IllegalStateException("No se puede cancelar una solicitud ya desembolsada");
            }

            LoanApplication cancelled = application.withStatus(LoanApplication.Status.CANCELLED);
            return repositoryPort.updateStatus(applicationId, LoanApplication.Status.CANCELLED);

        } finally {
            MDC.clear();
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto del préstamo es obligatorio");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException("El monto excede el límite máximo de " + MAX_AMOUNT);
        }
    }

    private void validateTerm(Integer termMonths) {
        if (termMonths == null) {
            throw new IllegalArgumentException("El plazo del préstamo es obligatorio");
        }
        if (termMonths < 1 || termMonths > 360) {
            throw new IllegalArgumentException("El plazo debe estar entre 1 y 360 meses");
        }
    }

    private void validateCustomer(String customerId, String customerName, String customerEmail) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("El identificador del cliente es obligatorio");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (customerEmail == null || customerEmail.isBlank()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if (!customerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/adapter/RiskEvaluationAdapter.java ===
package com.banca.digital.infrastructure.adapter;

import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.RiskEvaluationPort;
import com.banca.digital.domain.port.RiskEvaluationPort.RiskEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class RiskEvaluationAdapter implements RiskEvaluationPort {

    private static final Logger log = LoggerFactory.getLogger(RiskEvaluationAdapter.class);
    private static final BigDecimal MIN_SCORE_FOR_APPROVAL = new BigDecimal("600");
    private static final BigDecimal MAX_DEBT_RATIO = new BigDecimal("0.4");

    @Override
    public CompletableFuture<RiskEvaluationResult> evaluateRisk(LoanApplication loanApplication) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "evaluateRisk");

        try {
            log.info("Iniciando evaluación de riesgo para solicitud: {}, cliente: {}, monto: {}",
                    loanApplication.getId(), loanApplication.getCustomerId(), loanApplication.getAmount());

            return CompletableFuture.supplyAsync(() -> {
                long startTime = System.currentTimeMillis();
                String evaluationId = UUID.randomUUID().toString();

                try {
                    BigDecimal riskScore = calculateRiskScore(loanApplication);
                    boolean approved = isApproved(riskScore, loanApplication);
                    BigDecimal approvedAmount = calculateApprovedAmount(loanApplication, riskScore);
                    String rejectionReason = approved ? null : calculateRejectionReason(riskScore, loanApplication);

                    long processingTime = System.currentTimeMillis() - startTime;
                    log.info("Evaluación de riesgo completada en {}ms - ID: {}, Score: {}, Aprobado: {}",
                            processingTime, evaluationId, riskScore, approved);

                    return new RiskEvaluationResult(
                            evaluationId,
                            approved,
                            riskScore,
                            approvedAmount,
                            rejectionReason,
                            processingTime
                    );

                } catch (Exception e) {
                    log.error("Error durante la evaluación de riesgo para solicitud {}: {}",
                            loanApplication.getId(), e.getMessage(), e);
                    throw new RuntimeException("Error en el motor de evaluación de riesgos", e);
                }
            });

        } finally {
            MDC.clear();
        }
    }

    private BigDecimal calculateRiskScore(LoanApplication loanApplication) {
        BigDecimal baseScore = new BigDecimal("650");
        BigDecimal amountFactor = loanApplication.getAmount()
                .divide(new BigDecimal("10000"), 4, RoundingMode.HALF_UP);
        BigDecimal termFactor = new BigDecimal(loanApplication.getTermMonths())
                .divide(new BigDecimal("120"), 4, RoundingMode.HALF_UP);

        BigDecimal scoreVariation = amountFactor.multiply(termFactor).multiply(new BigDecimal("50"));
        BigDecimal finalScore = baseScore.subtract(scoreVariation);

        int hash = loanApplication.getCustomerId().hashCode();
        BigDecimal customerAdjustment = new BigDecimal(hash % 100);
        finalScore = finalScore.add(customerAdjustment);

        return finalScore.max(new BigDecimal("300")).min(new BigDecimal("850"));
    }

    private boolean isApproved(BigDecimal riskScore, LoanApplication loanApplication) {
        boolean scoreApproved = riskScore.compareTo(MIN_SCORE_FOR_APPROVAL) >= 0;
        boolean amountApproved = isAmountWithinLimits(loanApplication);
        boolean termApproved = isTermWithinLimits(loanApplication);

        return scoreApproved && amountApproved && termApproved;
    }

    private boolean isAmountWithinLimits(LoanApplication loanApplication) {
        BigDecimal maxAllowed = new BigDecimal(System.getProperty("RISK_MAX_AMOUNT", "50000"));
        BigDecimal minAllowed = new BigDecimal("1000");
        return loanApplication.getAmount().compareTo(maxAllowed) <= 0
                && loanApplication.getAmount().compareTo(minAllowed) >= 0;
    }

    private boolean isTermWithinLimits(LoanApplication loanApplication) {
        int maxTerm = Integer.parseInt(System.getProperty("RISK_MAX_TERM_MONTHS", "120"));
        int minTerm = Integer.parseInt(System.getProperty("RISK_MIN_TERM_MONTHS", "3"));
        return loanApplication.getTermMonths() <= maxTerm && loanApplication.getTermMonths() >= minTerm;
    }

    private BigDecimal calculateApprovedAmount(LoanApplication loanApplication, BigDecimal riskScore) {
        if (!isApproved(riskScore, loanApplication)) {
            return BigDecimal.ZERO;
        }

        BigDecimal percentage = riskScore.subtract(new BigDecimal("500"))
                .divide(new BigDecimal("350"), 4, RoundingMode.HALF_UP);
        percentage = percentage.max(new BigDecimal("0.5")).min(new BigDecimal("1.0"));

        BigDecimal approvedAmount = loanApplication.getAmount().multiply(percentage);
        return approvedAmount.setScale(2, RoundingMode.HALF_UP);
    }

    private String calculateRejectionReason(BigDecimal riskScore, LoanApplication loanApplication) {
        if (riskScore.compareTo(MIN_SCORE_FOR_APPROVAL) < 0) {
            return "Score de riesgo insuficiente: " + riskScore.intValue() + ". Se requiere mínimo " + MIN_SCORE_FOR_APPROVAL.intValue();
        }
        if (!isAmountWithinLimits(loanApplication)) {
            return "Monto fuera de los límites permitidos por la política de riesgos";
        }
        if (!isTermWithinLimits(loanApplication)) {
            return "Plazo fuera de los límites permitidos por la política de riesgos";
        }
        return "No cumple con los criterios de evaluación de riesgo";
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java ===
package com.banca.digital.infrastructure.adapter;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.LoanApplicationRepositoryPort;
import com.banca.digital.infrastructure.repository.LoanApplicationJpaRepository;
import com.banca.digital.infrastructure.repository.entity.LoanApplicationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador que implementa el puerto del dominio para operaciones de persistencia.
 * Convierte entre entidades del dominio y entidades JPA, delegando en el repositorio Spring Data.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LoanApplicationRepositoryAdapter implements LoanApplicationRepositoryPort {

    private final LoanApplicationJpaRepository jpaRepository;

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        log.debug("Persistiendo solicitud de prestamo con ID: {}", loanApplication.id());
        LoanApplicationEntity entity = toEntity(loanApplication);
        LoanApplicationEntity savedEntity = jpaRepository.save(entity);
        log.info("Solicitud de prestamo persistida exitosamente con ID: {}", savedEntity.getId());
        return toDomain(savedEntity);
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        log.debug("Buscando solicitud de prestamo con ID: {}", id);
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public LoanApplication updateStatus(UUID id, LoanApplication.Status status) {
        log.debug("Actualizando estado de solicitud {} a {}", id, status);
        LoanApplicationEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: " + id));
        entity.setStatus(status.name());
        LoanApplicationEntity updated = jpaRepository.save(entity);
        log.info("Estado de solicitud {} actualizado a {}", id, status);
        return toDomain(updated);
    }

    @Override
    public List<LoanApplication> findByStatus(LoanApplication.Status status) {
        log.debug("Buscando solicitudes con estado: {}", status);
        List<LoanApplicationEntity> entities = jpaRepository.findByStatus(status.name());
        log.debug("Encontradas {} solicitudes con estado {}", entities.size(), status);
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Eliminando solicitud de prestamo con ID: {}", id);
        jpaRepository.deleteById(id);
        log.info("Solicitud de prestamo eliminada con ID: {}", id);
    }

    private LoanApplicationEntity toEntity(LoanApplication domain) {
        LoanApplicationEntity entity = new LoanApplicationEntity();
        entity.setId(domain.id());
        entity.setClientId(domain.clientId());
        entity.setAmount(domain.amount());
        entity.setTermMonths(domain.termMonths());
        entity.setPurpose(domain.purpose());
        entity.setStatus(domain.status().name());
        entity.setInterestRate(domain.interestRate());
        entity.setMonthlyPayment(domain.monthlyPayment());
        entity.setRiskScore(domain.riskScore());
        entity.setRequestedAt(domain.requestedAt());
        entity.setApprovedAt(domain.approvedAt());
        return entity;
    }

    private LoanApplication toDomain(LoanApplicationEntity entity) {
        return new LoanApplication(
                entity.getId(),
                entity.getClientId(),
                entity.getAmount(),
                entity.getTermMonths(),
                entity.getPurpose(),
                LoanApplication.Status.valueOf(entity.getStatus()),
                entity.getInterestRate(),
                entity.getMonthlyPayment(),
                entity.getRiskScore(),
                entity.getRequestedAt(),
                entity.getApprovedAt()
        );
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/config/LoggingConfig.java ===
package com.banca.digital.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Configuración de logging para el sistema de gestión de préstamos.
 * Implementa trazas estructuradas usando MDC para correlación de requests.
 */
@Slf4j
@Configuration
public class LoggingConfig {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_MDC_KEY = "requestId";
    private static final String CLIENT_ID_MDC_KEY = "clientId";
    private static final String ENDPOINT_MDC_KEY = "endpoint";
    private static final String METHOD_MDC_KEY = "method";

    @Bean
    public OncePerRequestFilter loggingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                String requestId = extractOrGenerateRequestId(request);
                String clientId = request.getHeader("X-Client-ID");
                String endpoint = request.getRequestURI();
                String method = request.getMethod();

                try {
                    MDC.put(REQUEST_ID_MDC_KEY, requestId);
                    MDC.put(ENDPOINT_MDC_KEY, endpoint);
                    MDC.put(METHOD_MDC_KEY, method);
                    if (clientId != null && !clientId.isBlank()) {
                        MDC.put(CLIENT_ID_MDC_KEY, clientId);
                    }

                    log.info("Iniciando procesamiento de request: method={}, endpoint={}, requestId={}",
                            method, endpoint, requestId);

                    long startTime = System.currentTimeMillis();
                    filterChain.doFilter(request, response);
                    long duration = System.currentTimeMillis() - startTime;

                    log.info("Request completado: method={}, endpoint={}, status={}, durationMs={}, requestId={}",
                            method, endpoint, response.getStatus(), duration, requestId);

                    if (duration > 200) {
                        log.warn("Latencia elevada detectada: {}ms para {} {}", duration, method, endpoint);
                    }

                } catch (Exception e) {
                    log.error("Error procesando request: method={}, endpoint={}, requestId={}, error={}",
                            method, endpoint, requestId, e.getMessage(), e);
                    throw e;
                } finally {
                    MDC.clear();
                }
            }

            private String extractOrGenerateRequestId(HttpServletRequest request) {
                String requestId = request.getHeader(REQUEST_ID_HEADER);
                return (requestId != null && !requestId.isBlank()) ? requestId : UUID.randomUUID().toString();
            }
        };
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java ===
package com.banca.digital.infrastructure.controller;


import com.banca.digital.domain.model.Status;
import com.banca.digital.application.service.LoanApplicationService;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest;
import com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse;
import com.banca.digital.infrastructure.controller.dto.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para operaciones de solicitudes de préstamos.
 * Expone los endpoints para crear, consultar y gestionar solicitudes de crédito.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @Valid @RequestBody CreateLoanApplicationRequest request) {
        log.info("Recibida solicitud de prestamo para cliente: {}, monto: {}",
                request.clientId(), request.amount());

        try {
            LoanApplication created = loanApplicationService.createLoanApplication(
                    request.clientId(),
                    request.amount(),
                    request.termMonths(),
                    request.purpose()
            );

            log.info("Solicitud de prestamo creada exitosamente con ID: {}", created.id());
            return ResponseEntity.status(HttpStatus.CREATED).body(LoanApplicationResponse.fromDomain(created));

        } catch (IllegalArgumentException e) {
            log.error("Error al crear solicitud de prestamo: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de prestamo: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplication(@PathVariable UUID id) {
        log.debug("Consultando solicitud de prestamo con ID: {}", id);

        return loanApplicationService.findById(id)
                .map(application -> ResponseEntity.ok(LoanApplicationResponse.fromDomain(application)))
                .orElseThrow(() -> {
                    log.warn("Solicitud de prestamo no encontrada: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada: " + id);
                });
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationsByStatus(
            @RequestParam(required = false) String status) {
        log.debug("Consultando solicitudes de prestamo con filtro de estado: {}", status);

        List<LoanApplication> applications;
        if (status != null && !status.isBlank()) {
            LoanApplication.Status statusEnum = LoanApplication.Status.valueOf(status.toUpperCase());
            applications = loanApplicationService.findByStatus(statusEnum);
        } else {
            applications = loanApplicationService.findAll();
        }

        log.debug("Encontradas {} solicitudes de prestamo", applications.size());
        List<LoanApplicationResponse> responses = applications.stream()
                .map(LoanApplicationResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LoanApplicationResponse> approveLoanApplication(@PathVariable UUID id) {
        log.info("Aprobando solicitud de prestamo con ID: {}", id);

        try {
            LoanApplication approved = loanApplicationService.approveLoanApplication(id);
            log.info("Solicitud de prestamo aprobada: {}", id);
            return ResponseEntity.ok(LoanApplicationResponse.fromDomain(approved));

        } catch (IllegalArgumentException e) {
            log.error("Error al aprobar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al aprobar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LoanApplicationResponse> rejectLoanApplication(@PathVariable UUID id) {
        log.info("Rechazando solicitud de prestamo con ID: {}", id);

        try {
            LoanApplication rejected = loanApplicationService.rejectLoanApplication(id);
            log.info("Solicitud de prestamo rechazada: {}", id);
            return ResponseEntity.ok(LoanApplicationResponse.fromDomain(rejected));

        } catch (IllegalArgumentException e) {
            log.error("Error al rechazar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al rechazar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable UUID id) {
        log.info("Eliminando solicitud de prestamo con ID: {}", id);

        try {
            loanApplicationService.deleteLoanApplication(id);
            log.info("Solicitud de prestamo eliminada: {}", id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            log.error("Error al eliminar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al eliminar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getStatusCode().value(),
                ex.getReason()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Excepcion no manejada: {}", ex.getMessage(), ex);
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

// === ARCHIVO: src/main/java/com/banca/digital/infrastructure/repository/entity/LoanApplicationEntity.java ===
package com.banca.digital.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_applications", indexes = {
    @Index(name = "idx_loan_applications_status", columnList = "status"),
    @Index(name = "idx_loan_applications_customer_id", columnList = "customer_id"),
    @Index(name = "idx_loan_applications_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Column(length = 500)
    private String purpose;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "approved_amount", precision = 19, scale = 4)
    private BigDecimal approvedAmount;

    @Column(name = "interest_rate", precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "evaluation_result", length = 1000)
    private String evaluationResult;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// === ARCHIVO: src/test/java/com/banca/digital/application/service/LoanApplicationServiceTest.java ===
package com.banca.digital.application.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

}

// === ARCHIVO: src/test/java/com/banca/digital/infrastructure/controller/LoanApplicationControllerTest.java ===
package com.banca.digital.infrastructure.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanApplicationControllerTest {

}
```
