# 💱 Conversor de Moedas — Java

Conversor de moedas via console desenvolvido em Java, com taxas de câmbio obtidas em **tempo real** através da [ExchangeRate API](https://www.exchangerate-api.com/).

Projeto desenvolvido como desafio do programa **Oracle Next Education (ONE)** em parceria com a **Alura**.

---

## 🚀 Funcionalidades

- Menu interativo no console com **8 opções de conversão**
- Taxas de câmbio **sempre atualizadas** via API REST
- Tratamento de erros para entradas inválidas
- Arquitetura orientada a objetos com **separação de responsabilidades**

### Moedas suportadas

| Código | Moeda                |
|--------|----------------------|
| USD    | Dólar Americano      |
| BRL    | Real Brasileiro      |
| ARS    | Peso Argentino       |
| BOB    | Boliviano Boliviano  |
| CLP    | Peso Chileno         |
| COP    | Peso Colombiano      |

---

## 🏗️ Arquitetura do Projeto

```
src/com/currencyconverter/
├── Main.java                          # Ponto de entrada
├── model/
│   └── ExchangeRateResponse.java      # Modelo da resposta da API (JSON → Java)
├── api/
│   └── ApiClient.java                 # Requisições HTTP (HttpClient / HttpRequest)
├── service/
│   └── CurrencyConverter.java         # Lógica de negócio e cálculo de conversão
└── ui/
    └── ConsoleMenu.java               # Interface textual (Scanner + menu)
```

### Responsabilidade de cada classe

| Classe | Camada | Responsabilidade |
|---|---|---|
| `Main` | Entrada | Instancia e inicia o `ConsoleMenu` |
| `ExchangeRateResponse` | Modelo | Representa o JSON da API como objeto Java |
| `ApiClient` | Dados | Realiza chamadas HTTP e retorna a resposta bruta |
| `CurrencyConverter` | Serviço | Parseia o JSON (Gson) e calcula o valor convertido |
| `ConsoleMenu` | Interface | Exibe o menu, lê entradas e apresenta resultados |

---

## ⚙️ Pré-requisitos

- **Java JDK 17+** — [Download](https://www.oracle.com/java/technologies/downloads/)
- **Gson 2.10.1** (já incluído em `lib/gson-2.10.1.jar`)

---

## ▶️ Como executar

### 1. Compilar

```bat
compile.bat
```

### 2. Executar

```bat
run.bat
```

> Ou manualmente:
> ```bat
> javac -cp "lib\gson-2.10.1.jar" -d out -sourcepath src src\com\currencyconverter\Main.java ...
> java -cp "out;lib\gson-2.10.1.jar" com.currencyconverter.Main
> ```

---

## 📸 Exemplo de uso

```
╔══════════════════════════════════════════╗
║        CONVERSOR DE MOEDAS  💱           ║
║   Taxas obtidas em tempo real via API    ║
╚══════════════════════════════════════════╝

─── Selecione uma conversão ───────────────
  1. Dólar Americano      →  Real Brasileiro
  2. Real Brasileiro      →  Dólar Americano
  3. Dólar Americano      →  Peso Argentino
  4. Dólar Americano      →  Boliviano Boliviano
  5. Dólar Americano      →  Peso Chileno
  6. Dólar Americano      →  Peso Colombiano
  7. Real Brasileiro      →  Peso Argentino
  8. Real Brasileiro      →  Peso Colombiano
  0. Sair
───────────────────────────────────────────
Escolha: 1

Digite o valor em USD a converter: 100

⏳ Buscando taxa de câmbio em tempo real...

✅ Resultado da Conversão:
   100,00 USD = 522,75 BRL  (taxa: 1 USD = 5,2275 BRL)
```

---

## 🛠️ Tecnologias utilizadas

- **Java 21** (compatível com Java 17+)
- **HttpClient / HttpRequest / HttpResponse** (java.net.http — nativo)
- **Gson 2.10.1** — parsing de JSON
- **ExchangeRate API v6** — taxas de câmbio em tempo real

---

## 👤 Autor

**Mattheus Carvalho**  
GitHub: [@MattheusCarvalho](https://github.com/MattheusCarvalho)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
