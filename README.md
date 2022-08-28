# ICEPEAK: Товары для активного отдыха

*Интернет-магазин на Spring Boot + Angular*

Демонстрационный проект

## Технологии

REST-API (/icepeak): Spring MVC

Безопасность: Spring Security (аутентификация по токену, авторизация по url)

Доступ к данным: Spring Data JPA

БД: PostgreSQL

Миграции: Flyway

Утилита для загрузки данных (/utils/batch-sample-data):  Spring Batch

Веб-клиент: Angular, компоненты Angular Material (см. https://github.com/asp2403/icepeak-angular)

## Установка

```
cd icepeak
docker-compose up -d
```

## Роли пользователей

Реализована поддержка следующих ролей пользователей:

Роль|Описание
---|---
ANONYMOUS|Незарегистрированный пользователь
CUSTOMER|Покупатель
MANAGER|Продавец

Тестовые пользователи, загружаемые утилитой batch-sample-data

Логин|Пароль|Полное имя|Роль
-----|------|----------|----
vasya@mail.ru|!vasya123|Василий Пупкин|CUSTOMER
petya@mail.ru|!petya123|Петя Батарейкин|CUSTOMER
katz@icepeak.com|!katz123|Таня Кац|MANAGER
ishnip@icepeak.com|!ishnip123|Изя Шниперсон|MANAGER






