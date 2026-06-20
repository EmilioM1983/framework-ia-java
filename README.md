# AI-DeepSeek

Framework de agentes de IA en Java 21 que interactúa con modelos de lenguaje locales a través de [Ollama](https://ollama.com/).

---

## Descripción

AI-DeepSeek es una aplicación de consola que permite utilizar agentes de inteligencia artificial para procesar texto. Cuenta con una arquitectura extensible de agentes organizados por categorías (texto, agentes, negocios). Actualmente cuenta con **5 agentes**: resumidor, generador de artículos, traductor, recomendador E-commerce y analizador de feedback.

---

## Características

- **5 agentes funcionales**: Resumidor, generador, traductor, recomendador E-commerce y analizador de feedback
- **Categorías de agentes**: Organización por dominios (texto, agentes, negocios)
- **Lógica híbrida**: El recomendador E-commerce combina IA con catálogo local de productos
- **Inyección de dependencias**: Los agentes reciben `OllamaClient` por constructor
- **Singleton**: Una única instancia de `OllamaClient` en toda la aplicación
- **Validación de respuesta**: Se valida que la respuesta de Ollama no sea nula ni vacía
- **Entrada multilínea**: Soporte para ingresar texto extenso con delimitador `FIN`
- **Manejo de errores**: Validación de entrada numérica y respuesta vacía controlados
- Arquitectura basada en agentes con patrón **Strategy** y **Factory**
- Configuración externa vía `application.properties`
- Logging con **SLF4J** + **Logback**
- DTOs inmutables con Java Records
- Cliente HTTP nativo de Java (sin dependencias adicionales)

---

## Prerrequisitos

| Requisito | Versión mínima |
|---|---|
| Java | 21+ |
| Apache Maven | 3.6+ |
| Ollama | Última versión |

---

## Instalación

### 1. Instalar Ollama

```bash
# macOS / Linux
curl -fsSL https://ollama.com/install.sh | sh

# Windows: descargar desde https://ollama.com/download
```

### 2. Descargar el modelo

```bash
ollama pull deepseek-r1:8b
```

### 3. Iniciar Ollama

```bash
ollama serve
```

El servidor debe estar disponible en `http://localhost:11434`.

### 4. Compilar y ejecutar

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java -Dexec.mainClass="org.ehmsoft.Main"
```

O ejecutar directamente desde IntelliJ IDEA ejecutando la clase `Main.java`.

---

## Uso

Al ejecutar la aplicación se presenta el siguiente menú:

```
Seleccione el agente deseado
1. Resumidor de texto
2. Generador de texto
3. Traductor de textos ingles-español
4. Recomendador Ecommerce
5. Analizador de Feedback
6. Exit
```

### 1. Resumidor de texto
Resume un texto ingresado a 25 palabras.

### 2. Generador de texto
Genera un artículo de 100 palabras sobre un tema.

### 3. Traductor de textos (inglés-español)
Traduce texto multilínea (finalizar con `FIN`) de inglés a español.

### 4. Recomendador Ecommerce ⭐ NUEVO
Recomienda productos según la consulta del usuario. Utiliza un catálogo local con 3 categorías: notebooks, smartphones y auriculares.

```
Seleccione el agente deseado
1. Resumidor de texto
2. Generador de texto
3. Traductor de textos ingles-español
4. Recomendador Ecommerce
5. Analizador de Feedback
6. Exit
4
Que producto desea consultar?
notebook
Agente: Apple MacBook Air M2
Dell XPS 13
Lenovo ThinkPad X1 Carbon
```

### 5. Analizador de Feedback ⭐ NUEVO
Analiza el feedback de un cliente y devuelve:
1. Sentimiento (Positivo, Neutral o Negativo)
2. Problemas detectados
3. Aspectos destacados
4. Recomendaciones de mejora

---

## Arquitectura

```
src/main/java/org/ehmsoft/
├── Main.java                    # Punto de entrada (6 opciones)
├── agents/
│   ├── IAgent.java              # Interfaz de agentes
│   ├── texto/                   # Agentes de procesamiento de texto
│   │   ├── SummarizerAgent.java
│   │   ├── GeneratedTextAgent.java
│   │   └── TranslatorTextAgent.java
│   ├── agentes/                 # Agentes de recomendación
│   │   └── ProductRecommendationAgent.java
│   └── negocios/                # Agentes de negocios
│       └── FeedbackAnalyzerAgent.java
├── client/
│   └── OllamaClient.java        # Cliente HTTP para Ollama API
├── config/
│   ├── OllamaConfig.java        # Configuración desde properties
│   └── ProductCatalog.java      # Catálogo de productos
├── dtos/
│   ├── OllamaRequestDto.java
│   └── OllamaResponseDto.java
├── factory/
│   └── AgentFactory.java        # Fábrica de agentes (5 casos)
└── prompts/
    ├── texto/
    │   ├── SummarizePrompt.java
    │   ├── GeneratedTextPrompt.java
    │   └── TranslatorTextPrompt.java
    ├── agentes/
    │   └── ProductRecommendationPrompt.java
    └── negocios/
        └── FeedbackAnalyzerPrompt.java
```

### Patrones de diseño

| Patrón | Implementación |
|---|---|
| **Strategy** | `IAgent` interfaz con 5 implementaciones concretas |
| **Factory Method** | `AgentFactory.create()` centraliza creación de agentes |
| **Singleton** | Una única instancia de `OllamaClient` en `AgentFactory` |
| **Dependency Injection** | `OllamaClient` inyectado por constructor a todos los agentes |
| **Configuration Object** | `OllamaConfig` carga properties externas |

---

## Configuración

```properties
ollama.url=http://localhost:11434/api/generate
ollama.model=deepseek-r1:8b
```

| Propiedad | Descripción | Valor por defecto |
|---|---|---|
| `ollama.url` | Endpoint de la API de Ollama | `http://localhost:11434/api/generate` |
| `ollama.model` | Modelo a utilizar | `deepseek-r1:8b` |

---

## Testing

### Tests unitarios

El proyecto incluye **17 tests unitarios** que cubren los 3 componentes críticos:

| Componente | Archivo de Test | Tests |
|---|---|---|
| `OllamaClient` | `OllamaClientTest.java` | 3 |
| `ProductRecommendationAgent` | `ProductRecommendationAgentTest.java` | 7 |
| `AgentFactory` | `AgentFactoryTest.java` | 7 |

**Stack de testing:** JUnit Jupiter 5.11.4 + Mockito 5.14.2

### Ejecutar tests

```bash
mvn test
```

El reporte detallado se encuentra en [`TEST_REPORT.md`](TEST_REPORT.md).

---

## Stack Tecnológico

| Componente | Tecnología |
|---|---|
| Lenguaje | Java 21 (LTS) |
| Build | Apache Maven |
| Serialización JSON | Jackson Databind 2.20.0 |
| HTTP Client | java.net.http.HttpClient (nativo) |
| Logging | SLF4J 2.0.17 + Logback 1.5.18 |
| Testing | JUnit 5.11.4 + Mockito 5.14.2 |
| Motor de IA | Ollama (local) |
| Modelo LLM | DeepSeek R1 (8B) |

---

## Licencia

Proyecto privado — `org.ehmsoft`