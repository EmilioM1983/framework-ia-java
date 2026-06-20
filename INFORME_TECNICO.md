# 📋 INFORME TÉCNICO — Proyecto AI-DeepSeek (v8)

---

## 1. Identificación del Proyecto

| Campo | Valor |
|---|---|
| **Nombre** | AI-DeepSeek |
| **GroupId** | `org.ehmsoft` |
| **ArtifactId** | `AI-DeepSeek` |
| **Versión** | `1.0-SNAPSHOT` |
| **Lenguaje** | Java 21 |
| **Sistema de Build** | Apache Maven |
| **IDE de desarrollo** | IntelliJ IDEA Ultimate |

---

## 2. Estructura del Proyecto

```
AI-DeepSeek/
├── .gitignore
├── pom.xml
├── README.md
├── INFORME_TECNICO.md
└── src/
    ├── main/
    │   ├── java/org/ehmsoft/
    │   │   ├── Main.java                                (Punto de entrada - 6 opciones)
    │   │   ├── agents/
    │   │   │   ├── IAgent.java                          (Interfaz de agentes)
    │   │   │   ├── texto/
    │   │   │   │   ├── SummarizerAgent.java             (Agente de resumen)
    │   │   │   │   ├── GeneratedTextAgent.java          (Agente generador de artículos)
    │   │   │   │   └── TranslatorTextAgent.java         (Agente traductor)
    │   │   │   ├── agentes/
    │   │   │   │   └── ProductRecommendationAgent.java  (Agente recomendador E-commerce) ⭐
    │   │   │   └── negocios/
    │   │   │       └── FeedbackAnalyzerAgent.java       (Agente analizador de feedback) ⭐
    │   │   ├── client/
    │   │   │   └── OllamaClient.java                    (Cliente HTTP para Ollama)
    │   │   ├── config/
    │   │   │   ├── OllamaConfig.java                    (Configuración desde properties)
    │   │   │   └── ProductCatalog.java                  (Catálogo de productos) ⭐
    │   │   ├── dtos/
    │   │   │   ├── OllamaRequestDto.java                (DTO de petición)
    │   │   │   └── OllamaResponseDto.java               (DTO de respuesta)
    │   │   ├── factory/
    │   │   │   └── AgentFactory.java                    (Fábrica de agentes - 5 casos)
    │   │   └── prompts/
    │   │       ├── texto/
    │   │       │   ├── SummarizePrompt.java             (Prompt de resumen)
    │   │       │   ├── GeneratedTextPrompt.java         (Prompt de artículos)
    │   │       │   └── TranslatorTextPrompt.java        (Prompt de traducción)
    │   │       ├── agentes/
    │   │       │   └── ProductRecommendationPrompt.java (Prompt de recomendación) ⭐
    │   │       └── negocios/
    │   │           └── FeedbackAnalyzerPrompt.java      (Prompt de análisis de feedback) ⭐
    │   └── resources/
    │       └── application.properties                   (Configuración externa)
    └── test/
        └── java/                                        (Vacío)
```

**Total de archivos Java:** 13

---

## 3. Dependencias

### 3.1 Dependencias del proyecto

| Dependencia | Versión | Propósito |
|---|---|---|
| `com.fasterxml.jackson.core:jackson-databind` | 2.20.0 | Serialización/deserialización JSON |
| `org.slf4j:slf4j-api` | 2.0.17 | API de logging (SLF4J) |
| `ch.qos.logback:logback-classic` | 1.5.18 | Implementación de logging (Logback) |

### 3.2 Dependencias del JDK

| Clase | Propósito |
|---|---|
| `java.net.http.HttpClient` | Cliente HTTP nativo (Java 11+) |
| `java.net.http.HttpRequest` | Construcción de peticiones HTTP |
| `java.net.http.HttpResponse` | Manejo de respuestas HTTP |
| `java.util.Scanner` | Lectura de input de consola |
| `java.net.URI` | Construcción de URIs |
| `java.util.Properties` | Lectura de archivos de configuración |
| `java.io.InputStream` | Lectura de archivos de resources |
| `java.util.Map`, `java.util.List` | Estructuras de datos para catálogo |

### 3.3 Dependencias externas (runtime)

| Componente | Descripción |
|---|---|
| **Ollama** | Servidor local de inferencia de modelos de lenguaje |
| **Puerto** | `http://localhost:11434` |
| **Modelo** | `deepseek-r1:8b` (DeepSeek R1 de 8 mil millones de parámetros) |

---

## 4. Cambios respecto a la versión anterior (v7 → v8)

| Aspecto | v7 | v8 |
|---|---|---|
| **Agentes** | 3 (solo texto) | 5 (texto + recomendador + feedback) |
| **Menú** | 4 opciones | 6 opciones |
| **Prompts** | 3 en raíz de `prompts/` | 5 organizados en subpaquetes (`texto/`, `agentes/`, `negocios/`) |
| **Archivos Java** | 10 | 13 |
| **Catálogo de datos** | No existía | `ProductCatalog.java` con 3 categorías de productos |

---

## 5. Nuevos Agentes

### 5.1 `ProductRecommendationAgent` ⭐ NUEVO
- **Paquete:** `org.ehmsoft.agents.agentes`
- **Rol:** Recomendador de productos E-commerce.
- **Lógica híbrida:**
  1. Consulta a Ollama con el prompt de recomendación para categorizar la consulta
  2. Busca en `ProductCatalog` productos de la categoría detectada
  3. Si encuentra coincidencia, retorna los productos del catálogo
  4. Si no, retorna la respuesta generada por Ollama
- **Prompt:** "Analiza la consulta del usuario... Categorías disponibles: notebook, smartphone, auriculares..."

### 5.2 `FeedbackAnalyzerAgent` ⭐ NUEVO
- **Paquete:** `org.ehmsoft.agents.negocios`
- **Rol:** Analizador de feedback de clientes.
- **Prompt estructurado:** Solicita devolver:
  1. Sentimiento (Positivo, Neutral o Negativo)
  2. Problemas detectados
  3. Aspectos destacados
  4. Recomendaciones de mejora

---

## 6. Nuevos Archivos de Configuración

### 6.1 `ProductCatalog.java` ⭐ NUEVO
- **Paquete:** `org.ehmsoft.config`
- **Rol:** Catálogo estático de productos con 3 categorías:
  - `notebook` → Apple MacBook Air M2, Dell XPS 13, Lenovo ThinkPad X1 Carbon
  - `smartphone` → iPhone 14, Samsung Galaxy S23, Google Pixel 7
  - `auriculares` → Sony WH-1000XM4, Bose QuietComfort 45, Anker Soundcore Life Q30
- **Estructura:** `Map<String, List<String>>` con clave = categoría, valor = lista de productos

---

## 7. Arquitectura y Patrones de Diseño

### 7.1 Patrón: Strategy Pattern

```
IAgent (Interfaz)
    ├── agents.texto.SummarizerAgent              (Resumen)
    ├── agents.texto.GeneratedTextAgent           (Generación de artículos)
    ├── agents.texto.TranslatorTextAgent          (Traducción)
    ├── agents.agentes.ProductRecommendationAgent (Recomendación E-commerce) ⭐
    └── agents.negocios.FeedbackAnalyzerAgent     (Análisis de feedback) ⭐
```

### 7.2 Patrón: Factory Method + Singleton + DI

```java
private static final OllamaClient CLIENT = new OllamaClient();

public static IAgent create(int option) {
    return switch (option) {
        case 1 -> new SummarizerAgent(CLIENT);
        case 2 -> new GeneratedTextAgent(CLIENT);
        case 3 -> new TranslatorTextAgent(CLIENT);
        case 4 -> new ProductRecommendationAgent(CLIENT);   // ⭐
        case 5 -> new FeedbackAnalyzerAgent(CLIENT);        // ⭐
        default -> throw new IllegalArgumentException("Agente no válido");
    };
}
```

### 7.3 Patrón: Configuration Object

```
application.properties → OllamaConfig → OllamaClient
ProductCatalog → (usado directamente por ProductRecommendationAgent)
```

### 7.4 Flujo de ejecución

```
Main (Consola)
   │
   ├── 1: Resumir texto
   ├── 2: Generar artículo
   ├── 3: Traducir texto (inglés → español)
   ├── 4: Recomendar producto (E-commerce) ⭐
   ├── 5: Analizar feedback ⭐
   └── 6: Salir
   │
   ▼
AgentFactory.create(opcion)
   │   └── CLIENT (Singleton) → inyectado a los agentes
   ▼
IAgent → (según opción)
   │
   ▼
OllamaClient.generate(prompt)
   │
   ├── OllamaConfig
   ├── Serializa → JSON
   ├── POST → Ollama API
   ├── Deserializa → OllamaResponseDto
   ├── Validación de respuesta (null/blank → excepción)
   │
   ▼
Respuesta del modelo
```

---

## 8. Descripción Detallada de Cada Clase

### 8.1 `Main.java`
- **Menú:** 6 opciones
- **Opciones nuevas:** case 4 (Recomendador), case 5 (Analizador Feedback)

### 8.2 `IAgent.java`
- Interfaz base para todos los agentes.

### 8.3 Agentes de texto
- `SummarizerAgent` → Resumen a 25 palabras
- `GeneratedTextAgent` → Artículos de 100 palabras
- `TranslatorTextAgent` → Traducción inglés → español

### 8.4 `ProductRecommendationAgent.java` ⭐ NUEVO
- Lógica híbrida: IA + catálogo local
- Usa `ProductCatalog` para buscar productos por categoría

### 8.5 `FeedbackAnalyzerAgent.java` ⭐ NUEVO
- Analiza feedback con respuesta estructurada (sentimiento, problemas, aspectos destacados, mejoras)

### 8.6 `AgentFactory.java`
- 5 casos en el switch
- Singleton de `OllamaClient` inyectado a todos los agentes

### 8.7 `OllamaConfig.java`
- Carga configuración desde `application.properties`

### 8.8 `ProductCatalog.java` ⭐ NUEVO
- Mapa estático con 3 categorías y 3 productos cada una

### 8.9 `OllamaClient.java`
- Cliente HTTP con logging y validación de respuesta

### 8.10 DTOs
- `OllamaRequestDto` (model, prompt, stream)
- `OllamaResponseDto` (response)

### 8.11 Prompts (5 total)
| Prompt | Paquete | Función |
|---|---|---|
| `SummarizePrompt` | `prompts.texto` | Resumir en 25 palabras |
| `GeneratedTextPrompt` | `prompts.texto` | Generar artículo de 100 palabras |
| `TranslatorTextPrompt` | `prompts.texto` | Traducir al español |
| `ProductRecommendationPrompt` | `prompts.agentes` | Recomendar producto por categoría |
| `FeedbackAnalyzerPrompt` | `prompts.negocios` | Analizar feedback estructurado |

---

## 9. Configuración

### 9.1 Archivo `application.properties`

```properties
ollama.url=http://localhost:11434/api/generate
ollama.model=deepseek-r1:8b
```

---

## 10. Stack Tecnológico

| Capa | Tecnología | Versión |
|---|---|---|
| **Lenguaje** | Java | 21 (LTS) |
| **Build** | Apache Maven | 3.6+ |
| **Serialización JSON** | Jackson Databind | 2.20.0 |
| **HTTP Client** | java.net.http.HttpClient | Nativo (Java 11+) |
| **Logging API** | SLF4J | 2.0.17 |
| **Logging Impl** | Logback Classic | 1.5.18 |
| **Configuración** | java.util.Properties | Nativo |
| **Motor de IA** | Ollama | Local |
| **Modelo LLM** | DeepSeek R1 | 8B parámetros |
| **VCS** | Git | — |

---

## 11. Análisis de Calidad

### ✅ Fortalezas
1. **5 agentes funcionales** — Texto (3), E-commerce (1), Negocios (1)
2. **Lógica híbrida** — `ProductRecommendationAgent` combina IA + catálogo local
3. **Organización por dominios** — Paquetes `texto/`, `agentes/`, `negocios/` tanto en agents como prompts
4. **Inyección de dependencias** — Todos los agentes reciben `OllamaClient` por constructor
5. **Singleton** — Una única instancia de `OllamaClient` en toda la aplicación
6. **Validación de respuesta** — Control de null/vacío en OllamaClient
7. **Arquitectura extensible** — Nuevos agentes se agregan sin modificar código existente

### ⚠️ Áreas de Mejora
1. **Sin tests unitarios** — `src/test/java` sigue vacío
2. **`desarrollo/` vacío** — Directorio preparado pero sin implementaciones
3. **Catálogo hardcodeado** — `ProductCatalog` tiene datos fijos en código (podría ser configurable)

---

## 12. Métricas del Código

| Métrica | Valor |
|---|---|
| **Archivos Java** | 13 |
| **Líneas totales** | ~370 |
| **Clases** | 8 (Main, OllamaClient, SummarizerAgent, GeneratedTextAgent, TranslatorTextAgent, ProductRecommendationAgent, FeedbackAnalyzerAgent, OllamaConfig, ProductCatalog) |
| **Interfaces** | 1 (IAgent) |
| **Records (DTOs)** | 2 (OllamaRequestDto, OllamaResponseDto) |
| **Clases utilitarias (prompts)** | 5 (SummarizePrompt, GeneratedTextPrompt, TranslatorTextPrompt, ProductRecommendationPrompt, FeedbackAnalyzerPrompt) |
| **Clases Factory** | 1 (AgentFactory) |
| **Paquetes con agentes** | 3 (texto/, agentes/, negocios/) |
| **Paquetes con prompts** | 3 (texto/, agentes/, negocios/) |
| **Dependencias externas** | 3 (Jackson, SLF4J, Logback) |
| **Archivos de config** | 1 (application.properties) |

---

## 13. Testing

### 13.1 Tests unitarios

El proyecto incluye **17 tests unitarios** que cubren los 3 componentes críticos:

| Clase de Test | Tests | Estado |
|---|---|---|
| `OllamaClientTest` | 3 | ✅ Todos exitosos |
| `ProductRecommendationAgentTest` | 7 | ✅ Todos exitosos |
| `AgentFactoryTest` | 7 | ✅ Todos exitosos |
| **Total** | **17** | **✅ 100% exitosos** |

### 13.2 Stack de testing

| Herramienta | Versión |
|---|---|
| JUnit Jupiter | 5.11.4 |
| Mockito | 5.14.2 |
| Maven Surefire Plugin | 3.5.2 |

### 13.3 Cobertura por componente

| Componente | Clase de Test | ¿Por qué se testea? |
|---|---|---|
| **OllamaClient** | `OllamaClientTest` | Validación de respuesta vacía (lógica crítica) |
| **ProductRecommendationAgent** | `ProductRecommendationAgentTest` | Lógica híbrida única (IA + catálogo local) |
| **AgentFactory** | `AgentFactoryTest` | Punto central de creación de agentes |
| ProductCatalog | — (cubierto por tests de agente) | Datos estáticos |
| Prompts | — | Solo formatean strings, sin lógica |
| Agentes de texto | — | Solo delegan a OllamaClient |
| FeedbackAnalyzerAgent | — | Solo delega a OllamaClient |

### 13.4 Ejecutar tests

```bash
mvn test
```

El reporte detallado se encuentra en [`TEST_REPORT.md`](TEST_REPORT.md).

---

## 14. Prerrequisitos para Ejecución

1. **Java 21+** instalado
2. **Apache Maven** instalado
3. **Ollama** ejecutándose localmente en el puerto 11434
4. Modelo **`deepseek-r1:8b`** descargado (`ollama pull deepseek-r1:8b`)

---

## 15. Conclusión

El proyecto AI-DeepSeek alcanza su **versión 8** con la incorporación de **2 nuevos agentes**: `ProductRecommendationAgent` (E-commerce) y `FeedbackAnalyzerAgent` (análisis de feedback), alcanzando un total de **5 agentes**. Se agregó un **catálogo de productos** (`ProductCatalog.java`) y se reorganizaron los prompts en subpaquetes por dominio. La arquitectura demuestra su madurez y extensibilidad, manteniendo la inyección de dependencias, singleton y validación de respuesta implementados en versiones anteriores.