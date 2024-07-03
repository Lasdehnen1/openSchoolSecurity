## Реализация аутентификации и авторизации с использованием Spring Security и JWT

<details>
  Цель задания: Создать базовое веб-приложение с использованием Spring Security и JWT для аутентификации и авторизации пользователей.

Шаги задания:
Настройка проекта:
Создайте новый проект Spring Boot.

Настройка конфигурации безопасности:
Настройте базовую конфигурацию Spring Security для вашего приложения.
Используйте JWT для аутентификации пользователей.
Создайте класс для генерации и проверки JWT токенов.

Реализация контроллеров:
Создайте контроллеры для аутентификации и регистрации пользователей.
Реализуйте методы для создания нового пользователя и генерации JWT токена при успешной аутентификации.
Реализуйте сохранение пользователей в базу данных PostgreSQL.
Добавьте поддержку ролей пользователей и настройте авторизацию на основе ролей.

Тестирование:
Напишите модульные тесты для контроллеров и сервисов.
Убедитесь, что аутентификация и авторизация работают корректно.
Проверьте, что только аутентифицированные пользователи имеют доступ к защищенным ресурсам.

Документация:
Добавьте краткую документацию к вашему API с использованием Swagger или OpenAPI.

Результат задания: 
Рабочее веб-приложение с базовой аутентификацией и авторизацией на основе Spring Security и JWT, сопровождаемое модульными тестами и краткой документацией к API.
</details>

http://localhost:8080/auth/sign-up - для регистации нового пользователя

http://localhost:8080/auth/sign-in - для аутентификации и получения токена

http://localhost:8080/example - результат доступен только аутентифицированному пользователю

http://localhost:8080/example/admin - результат доступен только аутентифицированному пользователю с ролью Админ

Для тестирования токена можно воспользоваться Postman. Во вкладке Authorization необходимо выбрать Auth Type и в выпадающем списке выбрать Bearer Token. Далее необходимо использовать полученный при аутентификации токен при тестировании example-эндпоинтов

