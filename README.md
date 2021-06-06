Endpoints
* {GET} /abonents - получение всех абонентов
    - Parameters:
    - sorted - сортировка абонентов по account
        - values:
        - (default) false
        - true
    - filter - фильтрация абонентов по account
        - values:
        - (default) ""
        - [1..MAX_LONG]
    - page - постраничное отображение абонентов
        - values:
        - (default) 0
        - [0..MAX_INTEGER]
    - size - количество абонентов на странице
        - values:
        - (default) 20
        - [1..MAX_INTEGER]
* {GET} /abonents/{idService} - получение абонентов по оказываемой услуге
    - Path:
        - idService - id услуги
            - Values: [1..MAX_LONG]
    - Parameters:
        - children - показ дочерних услуг
            - Values:
            - (default) false
            - true
        - page - постраничное отображение абонентов
            - values:
            - (default) 0
            - [0..MAX_INTEGER]
        - size - количество абонентов на странице
            - values:
            - (default) 20
            - [1..MAX_INTEGER]
* {POST} /abonent - создание абонента
    - Parameters:
    - fio - ФИО абонента
        - Values:
        - (default) "ФИО"
        - строка размером до 255 символов
    - account - лицевой счет абонента
        - Values:
        - [1..MAX_LONG]
    - id_service - номер услуги
        - Values:
        - (default) 1
        - [1..MAX_LONG]
* {GET} /hierarchy - получение иерархии услуг
* {DELETE} delete/{id} - удаление услуги по id
    - Path:
        - id - id услуги
            - Values: [1..MAX_LONG]
    - Parameters:
        - force - удаление каскадом
            - Values:
            - (default) false
            - true