# CityServices Rest Api

#### _Сервис для получения и занесения информации о сервисных центрах_

Инструкция:

* Для начала нужно собрать проект выполнив комманду следующую команду.  
>> _gradle clean build task fillDb update_   

  Cоберётся проект,а затем liquibase создаст таблицы и внесёт в них данные.

* Далее можно запустить приложение. После запуска будет поднят сервер на
>> _server.address=127.0.0.1  
    server.port=8787_ 

* После старта сервера можно делать HTTP запросы по следующим URL:  
   * _http://localhost:8787/serviceList_ - Информация о всех сервисных центрах
   * _http://localhost:8787/getCities_ - Список городов в которых располагаются сервисные центры
   * _http://localhost:8787/getMaintaincies_ - Список возможных услуг
   * _http://localhost:8787/getServiceCenterById_ - _GET_   Запрос на получение информации по сервисному центру по ID
     * >>_http://localhost:8787/getServiceCenterById?id=2_ 
   * _http://localhost:8787/updateServiceCenter_ - _PUT_ Запрос на обновление данных сервисного центра. Все следующие запросы будут выполнены  
   При помощи консольного _http_ клиента _curl_
     * >> curl -X PUT localhost:8787/updateServiceCenter -H 'Content-type:application/json' -d '{"id": 100,"name": "ШинПро","cityName": "Томск","countryName": "Россия","address": "Переулок 1905 г 15а","maintenances": []}'  
     
       Запрос удаляет все услуги сервисного центра.
   *  _http://localhost:8787/addServiceCenter_ - _POST_ Запрос на добавления нового сервисного центра. 
        * >> curl -X POST localhost:8787/addServiceCenter -H 'Content-type:application/json' -d '{"name":"OбоD","cityName":"Астрахань","countryName":"Россия","address":"Кулёва 19 29а","maintenances":[{"id":3,"descr":"Установка жгута"},{"id":2,"descr":"Перебортовка"},{"id":1,"descr":"Вулканизация"},{"id":4,"descr":"Балансировка"}]}'
   
   * _http://localhost:8787/updateServiceCenter_ - _DELETE_ запрос на удаление сервисного центра.  
       * >>curl -X DELETE localhost:8787/deleteServiceCenter -H 'Content-type:application/json' -d '{"id":100}'
