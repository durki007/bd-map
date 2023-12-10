# Bazy Danych II - Projekt - Mapa cyfrowa

---

## Uruchomienie:

1. Zainstaluj [Docker](https://docs.docker.com/get-docker/)
2. Uruchom bazę danych:
    ```bash
   cd postgresql
    docker-compose up -d
    ``` 
3. Uruchom aplikację:
    ```bash
   ./gradlew bootRun
   ```
4. Otwórz [http://localhost:8080/](http://localhost:8080/)