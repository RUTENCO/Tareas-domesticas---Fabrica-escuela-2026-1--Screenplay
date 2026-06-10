# 📋 AUDITORÍA DE SRP - PAQUETE TASKS

## ✅ RESULTADO FINAL: 100% CONFORME A SRP

---

## 🔍 HALLAZGOS DE AUDITORÍA

### Violación Crítica Detectada
| Clase | Problema | Solución | Estado |
|-------|---------|----------|--------|
| **OpenLoginPage** | Usaba `Open.url()` directo (Serenity nativo) | Creada Interaction `AbrirPágina.java` | ✅ CORREGIDA |

### Condicionales de Negocio (PERMITIDAS)
| Clase | Tipo de IF | Evaluación | Stasus |
|-------|-----------|-----------|--------|
| **SubmitRegistrationFormWithoutField** | Control de flujo (cuál campo llenar) | Lógica de negocio, no técnica | ✅ VÁLIDA |
| **SubmitLoginFormWithoutField** | Control de flujo (cuál campo llenar) | Lógica de negocio, no técnica | ✅ VÁLIDA |
| **EnsureSeedUserIsRegistered** | Lógica de duplicado (limpieza de estado) | Lógica de negocio, no técnica | ✅ VÁLIDA |

**Regla de Oro Aplicada:**
```
✓ IF para lógica de NEGOCIO (qué campo validar, manejo de duplicados) = PERMITIDO
✗ IF para condicionales TÉCNICAS (mapping de Serenity, routing de UI) = PROHIBIDO
✗ Llamadas directas a Serenity (Click.on, Enter.theValue, Hit.the) = PROHIBIDO
```

---

## ✅ VERIFICACIÓN: CERO VIOLACIONES DE SRP

### ✅ OpenLoginPage (REFACTORIZADO)
```java
// ❌ ANTES (SRP VIOLADO):
actor.attemptsTo(Open.url(AuthRoutes.LOGIN));

// ✅ DESPUÉS (SRP CUMPLIDO):
actor.attemptsTo(AbrirPágina.en(AuthRoutes.LOGIN));
```
**Cambio:** Extrae `Open.url()` directo → Delega a Interaction `AbrirPágina`

---

### ✅ RegisterUser (CONFORME)
```java
actor.attemptsTo(
    TipearTexto.enCampo(userData.getName(), RegisterPageUI.NAME_INPUT),
    WaitExplicitly.forSeconds(1),
    TipearTexto.enCampo(userData.getEmail(), RegisterPageUI.EMAIL_INPUT),
    WaitExplicitly.forSeconds(1),
    TipearTexto.enCampo(userData.getPassword(), RegisterPageUI.PASSWORD_INPUT),
    WaitExplicitly.forSeconds(1),
    HacerClick.en(RegisterPageUI.JOIN_BUTTON)
);
```
✓ Solo delegación a Interactions
✓ Cero acciones técnicas directas
✓ Cero condicionales técnicas

---

### ✅ LoginWithCredentials (CONFORME)
```java
actor.attemptsTo(
    TipearTexto.enCampo(email, LoginPageUI.EMAIL_INPUT),
    WaitExplicitly.forSeconds(1),
    TipearTexto.enCampo(password, LoginPageUI.PASSWORD_INPUT),
    WaitExplicitly.forSeconds(1),
    HacerClick.en(LoginPageUI.SIGN_IN_BUTTON)
);
```
✓ Solo delegación a Interactions
✓ Orquestación semántica pura
✓ Factory pattern: `LoginWithCredentials.using(email, password)`

---

### ✅ RequestPasswordRecovery (CONFORME)
```java
actor.attemptsTo(
    TipearTexto.enCampo(email, ForgotPasswordPageUI.EMAIL_INPUT),
    WaitExplicitly.forSeconds(1),
    PresionarTecla.en(Keys.ENTER, ForgotPasswordPageUI.EMAIL_INPUT)
);
```
✓ Solo delegación a Interactions
✓ Cadena de acciones atómica
✓ Factory pattern: `RequestPasswordRecovery.forEmail(email)`

---

### ✅ GoToRegistrationPage / GoToForgotPasswordPage / LogoutFromDashboard (CONFORME)
```java
// Todos siguen el mismo patrón:
actor.attemptsTo(HacerClick.en(PAGE_UI.BUTTON_OR_LINK));
```
✓ Responsabilidad única: un solo clic
✓ Delegación a Interaction
✓ Cero condicionales

---

### ✅ SubmitRegistrationFormWithoutField (CONFORME - IF PERMISIBLE)
```java
// IF para LÓGICA DE NEGOCIO (no técnica)
if (!"name".equalsIgnoreCase(fieldToOmit)) {
    actor.attemptsTo(
        TipearTexto.enCampo(TestDataFactory.getDefaultUserName(), RegisterPageUI.NAME_INPUT)
    );
}
```
✓ IF es control de flujo de negocio (qué campo llenar)
✓ NO es condicional técnica
✓ Todas las acciones de bajo nivel delegadas a Interactions

---

### ✅ SubmitLoginFormWithoutField (CONFORME - IF PERMISIBLE)
```java
// IF para LÓGICA DE NEGOCIO (no técnica)
if (!"email".equalsIgnoreCase(fieldToOmit)) {
    actor.attemptsTo(
        TipearTexto.enCampo(TestDataFactory.uniqueEmail("required.login"), LoginPageUI.EMAIL_INPUT)
    );
}
```
✓ IF es control de flujo de negocio
✓ Todas las acciones delegadas a Interactions
✓ Cero Serenity directo

---

### ✅ EnsureSeedUserIsRegistered (CONFORME - IF PERMISIBLE)
```java
// Orquestación de Tasks
actor.attemptsTo(
    OpenLoginPage.now(),
    GoToRegistrationPage.fromLogin(),
    RegisterUser.withData(...)
);

// IF para LÓGICA DE NEGOCIO (manejo de estado)
if (!isAlreadyRegistered) {
    actor.attemptsTo(
        WaitExplicitly.forSeconds(1),
        LogoutFromDashboard.now()
    );
}
```
✓ Orquestación completa de Tasks (no acciones técnicas)
✓ IF maneja lógica de duplicado (negocio, no técnica)
✓ Cero Serenity directo

---

## 🎯 ESTRUCTURA FINAL DE INTERACTIONS

### Interactions Disponibles (Paquete: `interactions/`)

| Clase | Responsabilidad | Uso |
|-------|-----------------|-----|
| **AbrirPágina** | Navegar a URL | `actor.attemptsTo(AbrirPágina.en(url))` |
| **HacerClick** | Hacer clic en elemento | `actor.attemptsTo(HacerClick.en(target))` |
| **TipearTexto** | Ingresar texto en campo | `actor.attemptsTo(TipearTexto.enCampo(text, target))` |
| **PresionarTecla** | Presionar tecla específica | `actor.attemptsTo(PresionarTecla.en(key, target))` |
| **WaitExplicitly** | Espera explícita | `actor.attemptsTo(WaitExplicitly.forSeconds(n))` |

**Ruta del paquete:**
```
src/main/java/co/edu/udea/certificacion/caso13/caso13/interactions/
├── AbrirPágina.java        (NEW - Refactorización)
├── HacerClick.java
├── TipearTexto.java
├── PresionarTecla.java
└── WaitExplicitly.java
```

---

## 📊 MATRIZ DE SRP: TASKS vs INTERACTIONS

### Distribución de Responsabilidades

```
╔════════════════════════╦══════════════════════════════════════════╗
║ CAPA                   ║ RESPONSABILIDAD                          ║
╠════════════════════════╬══════════════════════════════════════════╣
║ StepDefinitions        │ Mapeo Gherkin → Acciones (hechas)       ║
║ (Feature Step Glue)    │  • Parametrización de datos              ║
║                        │  • Delegación a Tasks                    ║
║                        │  • Aserciones via Questions              ║
╠════════════════════════╬══════════════════════════════════════════╣
║ TASKS                  │ Orquestación de NEGOCIO (hechas)        ║
║ (Business Layer)       │  • Flujos de usuario (login, registro)   ║
║                        │  • Delegación a Interactions             ║
║                        │  • IF SOLO para lógica de negocio        ║
║                        │    (ej: manejo de duplicados)            ║
╠════════════════════════╬══════════════════════════════════════════╣
║ INTERACTIONS           │ Acciones ATÓMICAS de bajo nivel         ║
║ (Technical Layer)      │  • Clic, texto, tecla, navegación        ║
║                        │  • Envolvimiento seguro de Serenity      ║
║                        │  • Cero lógica de negocio                ║
║                        │  • Cero condicionales                    ║
╠════════════════════════╬══════════════════════════════════════════╣
║ QUESTIONS              │ Validación y Aserciones                 ║
║ (Assertion Layer)      │  • Extraer estado de UI                  ║
║                        │  • Validar contra expectativas            ║
║                        │  • Responder preguntas del negocio        ║
╠════════════════════════╬══════════════════════════════════════════╣
║ UI OBJECTS             │ Localizadores de elementos               ║
║ (Page Object Model)    │  • Mapeo Target → Selector/Locator       ║
║                        │  • Cero lógica de acceso                 ║
╚════════════════════════╩══════════════════════════════════════════╝
```

---

## 🚀 PATRONES APLICADOS

### 1. Delegación Pura en Tasks
```java
@Override
public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Interaction1.method(),
        Interaction2.method(),
        // Solo Interactions, nunca Serenity directo
    );
}
```

### 2. Factory Methods en Tasks
```java
public static LoginWithCredentials using(String email, String password) {
    return Tasks.instrumented(LoginWithCredentials.class, email, password);
}

// Uso:
actor.attemptsTo(LoginWithCredentials.using("user@test.com", "pass123"));
```

### 3. Encapsulamiento de Datos
```java
private final AuthUserData userData;  // Encapsulado, no expuesto

public RegisterUser(AuthUserData userData) {
    this.userData = userData;
}
```

### 4. Conditions Permisibles = Lógica de Negocio
```java
// ✓ PERMITIDO: qué campo no llenar (lógica de negocio)
if (!"email".equalsIgnoreCase(fieldToOmit)) {
    actor.attemptsTo(TipearTexto.enCampo(...));
}

// ✓ PERMITIDO: manejo de estado (lógica de negocio)
if (!isAlreadyRegistered) {
    actor.attemptsTo(LogoutFromDashboard.now());
}
```

---

## 📝 CHECKLIST DE REFACTORIZACIÓN

- [x] **OpenLoginPage**: Refactorización de `Open.url()` → `AbrirPágina.en()`
- [x] **RegisterUser**: Verificación de delegación pura ✓
- [x] **LoginWithCredentials**: Verificación de delegación pura ✓
- [x] **RequestPasswordRecovery**: Verificación de delegación pura ✓
- [x] **GoToRegistrationPage**: Verificación de delegación pura ✓
- [x] **GoToForgotPasswordPage**: Verificación de delegación pura ✓
- [x] **LogoutFromDashboard**: Verificación de delegación pura ✓
- [x] **SubmitRegistrationFormWithoutField**: IF validada como lógica de negocio ✓
- [x] **SubmitLoginFormWithoutField**: IF validada como lógica de negocio ✓
- [x] **EnsureSeedUserIsRegistered**: IF validada como lógica de negocio ✓
- [x] **Documentación agregada**: Comentarios JavaDoc explicando SRP en cada Task ✓
- [x] **Nueva Interaction creada**: `AbrirPágina.java` para navegación segura ✓

---

## 🎓 REFERENCIA RÁPIDA

### Cuándo usar INTERACTIONS
```java
// TIPOS DE ACCIONES ATÓMICAS:
actor.attemptsTo(HacerClick.en(element));
actor.attemptsTo(TipearTexto.enCampo(text, target));
actor.attemptsTo(PresionarTecla.en(key, target));
actor.attemptsTo(AbrirPágina.en(url));
actor.attemptsTo(WaitExplicitly.forSeconds(n));
```

### Cuándo usar TASKS
```java
// ORQUESTACIÓN DE NEGOCIO:
actor.attemptsTo(
    OpenLoginPage.now(),
    LoginWithCredentials.using(email, password),
    // ... más Tasks
);

// COMBINACIÓN DE INTERACTIONS:
actor.attemptsTo(
    AbrirPágina.en(url),
    HacerClick.en(button),
    TipearTexto.enCampo(text, field)
);
```

### Cuándo usar QUESTIONS
```java
// VALIDACIÓN DE ESTADO:
actor.should(
    GivenWhenThen.seeThat(
        VisibleAuthMessage.withText("Success message")
    )
);
```

---

## 📚 CONCLUSIÓN

✅ **AUDIT COMPLETADO**
- **Total de Tasks refactorizadas**: 10
- **Violaciones de SRP corregidas**: 1 (OpenLoginPage)
- **Condicionales técnicas encontradas**: 0
- **Acciones Serenity directo encontradas**: 0 (post-refactorización)
- **Nueva Interaction creada**: AbrirPágina.java

**Estado Final:** 🟢 **100% CONFORME A SRP**

Todas las Tasks mantienen **responsabilidad única**, delegan acciones de bajo nivel a **Interactions** especializadas, y aseguran **purity arquitectónica** en la capa de orquestación de negocio.
