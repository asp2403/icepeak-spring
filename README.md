#ICEPEAK: Товары для активного отдыха

*Интернет-магазин на Spring Boot*

## Технологии

Web-API (/icepeak): Spring MVC

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
## Описание

ICEPEAK - небольшой магазин, занимающийся продажей горнолыжного оборудования.  

Пользователь через веб-приложение заказывает товар и оплачивает его в магазине. При заказе товар бронируется на складе в течение заданного времени. Если покупатель не пришел за товаром, бронь снимается. При покупке товар списывается со склада. Нельзя забронировать товар, отсутствующий на складе.
Ассортимент магазина состоит из 2 категорий товаров: горные лыжи и горнолыжные ботинки.

### Роли пользователей

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

### Разрешения

url|Описание|Роли, которым разрешен доступ
---|---------|----------------------------
/api/public/**|Публичная зона|Всем
/api/auth/login/**|Вход в систему|Всем
/api/auth/logout**|Выход из системы|Аутентифицированным пользователям
/api/work-area/**|Рабочая зона|MANAGER


### Бизнес-процессы

#### Поиск товара
Роли: Anonymous, Customer

1. Пользователь выбирает категорию (Лыжи или Ботинки) -> отображается полный список товаров выбранной категории.
2. Пользователь задает один или несколько поисковых признаков и нажимает кнопку поиска -> отображается выборка товаров, удовлетворяющих условию поиска. 
3. Пользователь может отсортировать выборку по возрастанию ил убыванию цены.
4. Товар из выборки может быть добавлен в корзину (корзина хранится в браузере).
5. Товар может быть удален из корзины или изменено его количество.
6. Если корзина содержит хотя бы один товар, пользователь может сделать заказ.
   
#### Заказ товара
   Роли: Anonymous, Customer

   Предусловие: корзина содержит хотя бы 1 товар.

1. Для каждого продукта из заказа уменьшается количество доступных товаров на складе и увеличивается количество зарезервированных товаров на количество товаров в заказе.
   Исключительная ситуация: На складе нет товара для резерва  -> информирование пользователя о невозможности создать заказ.
2. Из содержимого корзины на сервере  формируется Заказ. Записывается дата заказа.
3. Для каждого товара в поле «Цена продажи» заказа записывается актуальная цена. Если пользователь имеет роль Customer, записывается id_customer. Статус заказа устанавливается в «Новый».
4. Покупателю выдается идентификатор заказа.
   
#### Начало обработки заказа
   Роли: Manager

   Предусловие: заказ в статусе «Новый»

1. Менеджер берет заказ "в работу". 
2. Статус заказа переходит в "Комплектуется". 
3. Проставляется дата начала работы.
4. В заказ записываются данные менеджера.
5. Становится доступной операция "Завершить комплектацию".

#### Завершение комплектации - 1
Роли: Manager

Предусловие: заказ в статусе «Комплектуется», заказ записан на текущего менеджера

1. Менеджер "Завершает комплектацию". 
2. В системе для заказа выставляет статус «Готов к выдаче»
3. Записывается дата готовности заказа.
4. Становятся доступными операции: "Выдать заказ", "Вернуть на комплектацию"


#### Завершение комплектации - 2
Роли: Manager

Предусловие: заказ в статусе «Комплектуется», заказ записан на другого менеджера

1. Менеджер может  "взять в работу" заказ.
2. В заказ записываются данные текущего менеджера, становится доступной операция "Завершить комплектацию"
3. При "завершении комплектации" выполняются действия "Завершение комплектации - 1"


#### Выдача товара - 1

Роли: Manager

Предусловие: Заказ в статусе «Готов к выдаче», заказ записан на текущего менеджера, «покупатель пришел в магазин и оплатил заказ».

1. Менеджер выполняет операцию "Выдать заказ".
2. Статус заказа становится "Выполнен".
3. Записывается дата готовности заказа.
4. Для каждого товара в заказе количество зарезервированных на складе товаров уменьшается на количество в заказе.

#### Выдача товара - 2

Роли: Manager

Предусловие: Заказ в статусе «Готов к выдаче», заказ записан на другого менеджера, «покупатель пришел в магазин и оплатил заказ».

1. Менеджер может выполнить операцию "Взять в работу"
2. В заказ записываются данные текущего менеджера, становится доступной операция "Выдать заказ"
3. При "выдаче заказа" выполняются действия "Выдача заказа - 1"

#### Отмена заказа
   
Роли: выполняется системой по расписанию

Предусловие: Заказ в статусе «Готов к выдаче», прошло более <параметр> с момента формирования заказа.

1. Статус заказа устанавливается в «Отменен»
2. Для каждого товара из заказа уменьшается количество зарезервированных товаров и увеличивается количество доступных товаров на величину количества товара в заказе.
3. Записывается дата отмены заказа.




