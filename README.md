# Курсовой проект. Сервис перевода денег.

## Описание проекта
Сервис представляет REST-интерфейс для интеграции с FRONT. Сервис реализовал все методы перевода денег с 
одной банковской карты на другую, по заданному [протоколу](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml).
Интеграция с FRONT. Использовалось уже развернутое приложения [тут](https://serp-ya.github.io/card-transfer/), для этого в файле application.properties задали порт 5500.
## Сруктура проекта
````
src
├───main
│   ├───java
│   │   └───ru
│   │       └───netology
│   │           └───moneytransferapp
│   │               ├───controller
│   │               ├───exception
│   │               ├───handler
│   │               ├───model
│   │               ├───repository
│   │               ├───service
│   │               └───validation
│   └───resources
│       ├───static
│       └───templates
└───test
      └───java
             └───ru
                  └───netology
                         └───moneytransferapp
````

### **_controller_**

Rest контроллер для обработки эндпоинтов;

### _**exception**_ 

ServerException - ошибка сервера, ответ 500 - Internal Server Error;

WrongInputData - ошибка данных при запросах, ответ 400 - Bad request;

### _**handler**_

Обработчик ошибок;

### _**model**_

Amount - сущность денег, состоит из количества и валюты;

TransferBetweenCards - сущность операции между картами;

OperationConfirmation - сущность подтверждения операции;

SuccessOK - объект ответа при положительном выполнении операции;

ErrorTransfer - объект ответа при невыполнении операции;

### _**repository**_

RepositoryTransferImpl - репозиторий для хранения операций(эмитация работы с БД);

### _**service**_

ServiceTransferImpl - сервис для выполнения операций между картами;

### _**validation**_

ServiceValidation - класс для определения валидности операций;

### **_resources_**

application.properties - файл для сетевых настроек 

log4j.properties - файл для логирования приложения. Перед запуском в файле `\src\main\resources\log4j.properties`
нужно изменить директорию сохранения файла логирования `log4j.appender.file.File="Ваш путь"\\log_file.log`.

### Запуск 

1. Клон репозитория\
2. Нужно собрать приложение, либо в меню Maven либо через командную строку командой `./mvnw clean package`
3. В терминале запустите приложения командой `docker-compose up`

### Запросы
Данный с формы отправляют `POST` запрос по адресу `http://localhost:5500/transfer` в формате `application/json` вида (Или используйте, например, POSTMAN для отправки запроса):
```
 {
    "cardFromNumber": "1234123412341234",
    "cardFromValidTill": "1225",
    "cardFromCVV": "324",
    "cardToNumber": "1234123412341234",
    "amount": {
        "value": 12,
        "currency":"rub"
    }
}
```
Далее происходит отправка `POST` запроса по адресу `http://localhost:5500/confirmOperation` в формате `application/json` вида (Или используйте, например, POSTMAN для отправки запроса):
```
{
    "operationId": "2",
    "code": "0000"
}