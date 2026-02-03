Diplom_3
Project: UI and API Test Automation with Selenium, REST Assured and Allure
Проект автоматизированного тестирования веб-приложения с использованием UI-тестов (Selenium) и API-тестов (REST Assured) на Java. Поддерживает генерацию отчётов через Allure Report.
[![Tests](https://github.com/moroz280294-spec/Diplom_3/actions/workflows/tests.yml/badge.svg)](https://github.com/moroz280294-spec/Diplom_3/actions)

##  Технологии и зависимости

| Компонент         | Версия |
|-------------------|-------|
| Java              | 11    |
| Maven             | 3.8+  |
| JUnit             | 4.13.2 |
| REST Assured      | 4.4.0 |
| Allure Framework  | 2.15.0 |
| AspectJ           | 1.9.7 |
| WebDriverManager  | 5.9.2 |
|Apache Commons Lang3| 3.18.0 |
| SLF4J (Simple Logger)| 2.0.13|


## ▶️ Как запустить тесты


Выполните в терминале из корня проекта:

```bash
mvn test -Dbrowser=chrome
