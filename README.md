# CityServices Rest Api

#### _Сервис для получения и занесения информации о сервисных центрах. Данные передаются и возвращаются в формате Json._

Инструкция:

* Для начала нужно собрать проект выполнив следующую команду.  
>> _gradle clean build  

  Cоберётся проект,а затем liquibase создаст таблицы и внесёт в них данные.  
  В качестве базы данных используется H2. Адрес:  
  >>_jdbc:h2:~/servicesBD_

* Далее можно запустить приложение. После запуска будет поднят сервер на
>> _server.address=127.0.0.1  
    server.port=8787_ 

* После старта сервера можно делать HTTP запросы по следующим URL:  
   * _http://localhost:8787/serviceList_ - Информация о всех сервисных центра  
   * _http://localhost:8787/citiesList - Список городов 
   * _http://localhost:8787/maintainciesList - Список возможных услуг
   * _http://localhost:8787/countriesList - Список стран
   * _http://localhost:8787/getServiceCenterById_ - _GET_   Запрос на получение информации по сервисному центру по ID
     * >>_http://localhost:8787/getServiceCenterById?id=1 
   * _http://localhost:8787/updateServiceCenter_ - _PUT_ Запрос на обновление данных сервисного центра. Все следующие запросы будут выполнены при помощи консольного _http_ клиента _curl_ 
     * >>{
          "name":"Альбатрос",
         "maintenancies":[
         {
            "id":1,
      "descr":"Вулканизация"
    },
    {
      "id":2,	
      "descr":"Перебортовка"
    },
             {
            "id":3,
      "descr":"Установка жгута"
    },
    {
      "id":5,	
      "descr":"Выравнивание обода"
    }
  ],
  "locations":[
    {
      "country":{
      	"id":1,
        "name":"Russia"
      },
      "city":{
      	"id":2,
        "name":"Moscow"
      },
      "cityAddress":{
      	"id":6,
        "streetName":"Lenina 5",
        "streetMark":"3"
      }
    }
  ]
}
     
   
   *  _http://localhost:8787/addServiceCenter_ - _POST_ Запрос на добавление нового сервисного центра. 
        * >> { {
  "name":"Альбатрос",
  "maintenancies":[
    {
      "id":1,
      "descr":"Вулканизация"
    },
    {
      "id":2,	
      "descr":"Перебортовка"
    }
  ],
  "locations":[
    {
      "country":{
      	"id":1,
        "name":"Russia"
      },
      "city":{
      	"id":1,
        "name":"Томск"
      },
      "cityAddress":{
      	"id":12,
        "streetName":"Chernih",
        "streetMark":"3"
      }
    }
  ]
}
   * _http://localhost:8787/updateServiceCenter_ - _DELETE_ запрос на удаление сервисного центра.  
       * >> localhost:8787/deleteServiceCenterById?id=1 
