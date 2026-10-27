# Integración y Optimización de Framework en Aplicación Backend

En una aplicación de banca digital, necesitas integrar y optimizar un framework en la capa de backend para mejorar la gestión de solicitudes de préstamos. Los actores involucrados son el 'originador de créditos', el'motor de evaluación de riesgos' y el 'core bancario'. La aplicación debe manejar un volumen de 1 500 solicitudes por segundo en hora pico, con una latencia máxima de 200ms y un tiempo de respuesta del 'motor de evaluación de riesgos' inferior a 50ms. El framework debe permitir el enrutamiento de solicitudes, el control de variables de entorno, la generación de registros de trazas y la conexión con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración Inicial del Framework

**Objetivo:** Configurar el framework para que pueda manejar las solicitudes de préstamos de manera eficiente.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identifica las dependencias necesarias para el framework.
- Configura el enrutamiento de solicitudes para que se dirijan al 'motor de evaluación de riesgos'.
- Establece las variables de entorno para la aplicación.

**Entregable:** Configuración inicial del framework con enrutamiento y variables de entorno configuradas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la latencia y el volumen de solicitudes al configurar el framework.
- Piensa en cómo el framework puede interactuar con el'motor de evaluación de riesgos'.

</details>

### Fase 2: Implementación de Registros de Trazas

**Objetivo:** Implementar la generación de registros de trazas para monitorear el flujo de solicitudes.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el framework para generar registros de trazas en cada solicitud de préstamo.
- Asegura que los registros incluyan información relevante como el ID de la solicitud, el tiempo de procesamiento y el resultado de la evaluación de riesgos.

**Entregable:** Framework configurado para generar registros de trazas en cada solicitud de préstamo.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la importancia de los registros de trazas para la depuración y monitoreo.
- Piensa en cómo la información de los registros puede ayudar a identificar problemas en el flujo de solicitudes.

</details>

### Fase 3: Conexión con Base de Datos utilizando ORM

**Objetivo:** Configurar la conexión con la base de datos utilizando un Mapeador Relacional de Objetos (ORM).

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el framework para conectarse con la base de datos utilizando un ORM.
- Asegura que la conexión sea estable y que los datos se puedan leer y escribir de manera eficiente.

**Entregable:** Framework configurado para conectarse con la base de datos utilizando un ORM.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la eficiencia y la estabilidad de la conexión con la base de datos.
- Piensa en cómo el ORM puede simplificar la manipulación de datos.

</details>

### Fase 4: Optimización y Pruebas

**Objetivo:** Optimizar la configuración del framework y realizar pruebas para asegurar su funcionamiento correcto.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Optimiza la configuración del framework para mejorar el rendimiento y la eficiencia.
- Realiza pruebas para asegurar que el framework funciona correctamente y cumple con los requisitos de latencia y volumen de solicitudes.

**Entregable:** Framework optimizado y probado, funcionando correctamente y cumpliendo con los requisitos de rendimiento.

<details>
<summary>Pistas de conocimiento</summary>

- Considera diferentes estrategias de optimización para mejorar el rendimiento del framework.
- Piensa en cómo las pruebas pueden ayudar a identificar y solucionar problemas en la configuración del framework.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué es importante en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve la configuración del enrutamiento de solicitudes en un framework?
- **comoSeUsa**: ¿Cómo se utiliza un Mapeador Relacional de Objetos (ORM) para conectarse con una base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar un framework y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización de un framework y cómo se pueden justificar?

## Criterios de Evaluacion

- Configuración inicial del framework con enrutamiento y variables de entorno configuradas.
- Framework configurado para generar registros de trazas en cada solicitud de préstamo.
- Framework configurado para conectarse con la base de datos utilizando un ORM.
- Framework optimizado y probado, funcionando correctamente y cumpliendo con los requisitos de rendimiento.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
