# WeatherForecast API

API REST desenvolvida em Java com Spring Boot para consulta de previsão do tempo em tempo real, utilizando dados da [Open-Meteo](https://open-meteo.com/) — gratuita e sem necessidade de API key.

## Funcionalidades

- Busca de previsão do tempo por nome de cidade
- Temperatura atual e volume de chuva em tempo real
- Probabilidade média de chuva para o dia
- Endpoint com dados brutos da API para uso avançado
- Geocoding automático: basta informar o nome da cidade

## Endpoints

### Previsão simplificada
```
GET /weather?city={cidade}
```

Exemplo de resposta:
```json
{
  "city": "Medeiros Neto",
  "temperature": 28.4,
  "actualRain": 0.0,
  "rainProbability": 3.5
}
```

### Dados brutos
```
GET /weather/raw?city={cidade}
```
Retorna o JSON completo da Open-Meteo, incluindo temperaturas e probabilidades horárias para as 24 horas do dia.

## Como executar

### Pré-requisitos
- Java 21
- Maven

### Rodando o projeto
```bash
git clone https://github.com/Aureliano021/weather-forecast.git
cd weather-forecast
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Gson
- Open-Meteo Forecast API
- Open-Meteo Geocoding API

## Arquitetura

```
controller/   → recebe as requisições HTTP
service/      → orquestra a lógica de negócio
client/       → comunicação com APIs externas
model/        → mapeamento de dados (API e resposta)
```

## Autor

Aureliano — estudante de Engenharia de Software na Uniasselvi.  
[GitHub](https://github.com/Aureliano021)
