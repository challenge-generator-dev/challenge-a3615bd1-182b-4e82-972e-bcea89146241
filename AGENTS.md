# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Integración y Optimización de Framework en Aplicación Backend**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-logging n/a
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.34
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core 5.12.0
- org.testcontainers:postgresql 1.20.1
- io.micrometer:micrometer-registry-prometheus n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración Inicial del Framework**: Configuración inicial del framework con enrutamiento y variables de entorno configuradas.
- **Fase 2 — Implementación de Registros de Trazas**: Framework configurado para generar registros de trazas en cada solicitud de préstamo.
- **Fase 3 — Conexión con Base de Datos utilizando ORM**: Framework configurado para conectarse con la base de datos utilizando un ORM.
- **Fase 4 — Optimización y Pruebas**: Framework optimizado y probado, funcionando correctamente y cumpliendo con los requisitos de rendimiento.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/banca/digital/application/service/LoanApplicationServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/digital/infrastructure/controller/LoanApplicationControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (55)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest`
      El import com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse`
      El import com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `com.banca.digital.infrastructure.controller.dto.ErrorResponse`
      El import com.banca.digital.infrastructure.controller.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/RiskEvaluationAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banca/digital/infrastructure/config/LoggingConfig.java` — `org.slf4j`
      El import org.slf4j.MDC pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.approved`
      Se invoca `approved` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.approvedAmount`
      Se invoca `approvedAmount` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.evaluationId`
      Se invoca `evaluationId` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/application/service/LoanApplicationService.java` — `RiskEvaluationResult.rejectionReason`
      Se invoca `rejectionReason` sobre `RiskEvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.id`
      Se invoca `id` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.save`
      Se invoca `save` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getId`
      Se invoca `getId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.findById`
      Se invoca `findById` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setStatus`
      Se invoca `setStatus` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `Status.name`
      Se invoca `name` sobre `Status`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationJpaRepository.deleteById`
      Se invoca `deleteById` sobre `LoanApplicationJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setId`
      Se invoca `setId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setClientId`
      Se invoca `setClientId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.clientId`
      Se invoca `clientId` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setAmount`
      Se invoca `setAmount` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.amount`
      Se invoca `amount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setTermMonths`
      Se invoca `setTermMonths` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.termMonths`
      Se invoca `termMonths` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setPurpose`
      Se invoca `setPurpose` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.purpose`
      Se invoca `purpose` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.status`
      Se invoca `status` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setInterestRate`
      Se invoca `setInterestRate` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.interestRate`
      Se invoca `interestRate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setMonthlyPayment`
      Se invoca `setMonthlyPayment` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.monthlyPayment`
      Se invoca `monthlyPayment` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setRiskScore`
      Se invoca `setRiskScore` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.riskScore`
      Se invoca `riskScore` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setRequestedAt`
      Se invoca `setRequestedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.requestedAt`
      Se invoca `requestedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.setApprovedAt`
      Se invoca `setApprovedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplication.approvedAt`
      Se invoca `approvedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getClientId`
      Se invoca `getClientId` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getAmount`
      Se invoca `getAmount` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getTermMonths`
      Se invoca `getTermMonths` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getPurpose`
      Se invoca `getPurpose` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getStatus`
      Se invoca `getStatus` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getInterestRate`
      Se invoca `getInterestRate` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getMonthlyPayment`
      Se invoca `getMonthlyPayment` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getRiskScore`
      Se invoca `getRiskScore` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getRequestedAt`
      Se invoca `getRequestedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java` — `LoanApplicationEntity.getApprovedAt`
      Se invoca `getApprovedAt` sobre `LoanApplicationEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.createLoanApplication`
      Se invoca `createLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplication.id`
      Se invoca `id` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findById`
      Se invoca `findById` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findByStatus`
      Se invoca `findByStatus` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.findAll`
      Se invoca `findAll` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.approveLoanApplication`
      Se invoca `approveLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.rejectLoanApplication`
      Se invoca `rejectLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java` — `LoanApplicationService.deleteLoanApplication`
      Se invoca `deleteLoanApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (15)

- `pom.xml`
- `src/main/java/com/banca/digital/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/banca/digital/domain/port/RiskEvaluationPort.java`
- `src/main/java/com/banca/digital/domain/port/LoanApplicationRepositoryPort.java`
- `src/main/java/com/banca/digital/infrastructure/repository/LoanApplicationJpaRepository.java`
- `src/main/java/com/banca/digital/domain/model/LoanApplication.java`
- `src/main/java/com/banca/digital/application/service/LoanApplicationService.java`
- `src/main/java/com/banca/digital/infrastructure/adapter/RiskEvaluationAdapter.java`
- `src/main/java/com/banca/digital/infrastructure/adapter/LoanApplicationRepositoryAdapter.java`
- `src/main/java/com/banca/digital/infrastructure/config/LoggingConfig.java`
- `src/main/java/com/banca/digital/infrastructure/controller/LoanApplicationController.java`
- `src/main/java/com/banca/digital/infrastructure/repository/entity/LoanApplicationEntity.java`
- `src/test/java/com/banca/digital/application/service/LoanApplicationServiceTest.java`
- `src/test/java/com/banca/digital/infrastructure/controller/LoanApplicationControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/banca/digital`
- `src/main/java/com/banca/digital/domain`
- `src/main/java/com/banca/digital/domain/model`
- `src/main/java/com/banca/digital/domain/port`
- `src/main/java/com/banca/digital/application`
- `src/main/java/com/banca/digital/application/service`
- `src/main/java/com/banca/digital/infrastructure`
- `src/main/java/com/banca/digital/infrastructure/adapter`
- `src/main/java/com/banca/digital/infrastructure/config`
- `src/main/java/com/banca/digital/infrastructure/controller`
- `src/main/java/com/banca/digital/infrastructure/repository`
- `src/main/resources`
- `src/test/java/com/banca/digital`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
