# Tareas-domesticas - Fabrica escuela 2026-1 - Screenplay

Proyecto de automatización QA con Serenity BDD + Screenplay para la Feature 1 (Autenticación y Gestión de Acceso).

## Tecnologías usadas

- Java 17
- Gradle
- Serenity BDD 4.1.0
- Serenity Screenplay
- Serenity Cucumber
- JUnit 4
- Selenium WebDriver (a través de Serenity Screenplay WebDriver)
- WebDriverManager

## Prerrequisitos

- JDK 17 instalado y configurado en `JAVA_HOME`
- Gradle Wrapper del proyecto (`gradlew.bat`)
- Conexión a internet para descargar dependencias y acceder al frontend:
  - https://project-tdwx8.vercel.app/login

## Ejecutar todos los escenarios

Ejecuta toda la suite de features de autenticación:

```powershell
.\gradlew.bat clean test
```

Comando recomendado para ejecutar y dejar el reporte agregado listo:

```powershell
.\gradlew.bat clean test aggregate
```

## Ejecutar un escenario específico

Puedes filtrar por nombre exacto (o parcial) del Scenario/Scenario Outline.

En PowerShell, cuando el valor tiene espacios, usa comillas simples alrededor de toda la propiedad `-D`:

```powershell
.\gradlew.bat test '-Dcucumber.filter.name=CP-006 Successful login with valid credentials'
```

Alternativa recomendada en PowerShell (modo literal con `--%`):

```powershell
.\gradlew.bat --% test -Dcucumber.filter.name="CP-006 Successful login with valid credentials"
```

También funciona con una parte del nombre, por ejemplo:

```powershell
.\gradlew.bat test '-Dcucumber.filter.name=CP-009'
```

Si Gradle muestra `BUILD SUCCESSFUL` y `up-to-date` sin abrir navegador, no ejecutó pruebas (usó caché). Para forzar la ejecución real:

```powershell
.\gradlew.bat test '-Dcucumber.filter.name=CP-006 Successful login with valid credentials' --rerun-tasks
```

## Modificar los tiempos de espera visual

La espera visual está centralizada para evitar usar `Thread.sleep()` en Tasks o StepDefinitions.

### 1) Utilidad de tiempo

- Clase: `TimeWait`
- Ubicación: `src/main/java/co/edu/udea/certificacion/caso13/caso13/utils/TimeWait.java`

Método principal:

- `TimeWait.forSeconds(int seconds)`

### 2) Interacción Screenplay de espera

- Clase: `WaitExplicitly`
- Ubicación: `src/main/java/co/edu/udea/certificacion/caso13/caso13/interactions/WaitExplicitly.java`

Uso estándar:

```java
actor.attemptsTo(WaitExplicitly.forSeconds(2));
```

### 3) Cómo ajustar la velocidad de ejecución

Tienes dos alternativas:

- Ajuste puntual: cambia los valores `WaitExplicitly.forSeconds(1)` por el tiempo deseado en Tasks/Steps clave.
- Ajuste global rápido: modifica la lógica de `TimeWait.forSeconds(...)` si quieres aplicar una política uniforme de depuración visual.

## Generar el reporte y dónde verlo

### Generar reporte

```powershell
.\gradlew.bat aggregate
```

Si quieres ejecutar pruebas y luego generar reporte en un solo comando:

```powershell
.\gradlew.bat clean test aggregate
```

### Ubicación del reporte

Reporte principal de Serenity:

- `target/site/serenity/index.html`

Archivos adicionales de ejecución:

- `target/site/serenity/*.json`
- `build/test-results/test/`
- `build/reports/tests/test/`

## Estructura funcional automatizada

Cobertura de la Feature 1:

- HU 3: User Registration (CP-001 a CP-005)
- HU 4: User Login (CP-006 a CP-008)
- HU 8: Logout (CP-009)
- HU 5: Password Recovery (CP-010 a CP-013)

Features ubicadas en:

- `src/test/resources/features/authentication/`

Runner Cucumber:

- `src/test/java/co/edu/udea/certificacion/caso13/caso13/runners/Runner.java`

## Notas

- Los escenarios están escritos en inglés (Gherkin estándar) y organizados con `Scenario Outline` donde aplica.
- La automatización sigue el patrón Screenplay y separa responsabilidades entre Interactions, Tasks y Questions.
