## Задание: Многомодульный web проект

1. Создать приложение с 3 модулями
   - модель данных (модуль database)
   - бизнес логика (service)
   - представление (web)

2. Для примера взять проект с занятия:
   - Клонировать проект с [GitHub](https://github.com/AndreiBor/MVC-APP-ITA.git)
   - IDEA: File -> New -> Project From Version Control -> ввести URL 
   - затем указать SDK в настройках проекта

3. Настроить Tomcat 10 через Idea или добавить конфиг в maven plugin.
   - Версия java в системе 11 или выше, если что можно установить томкат не через архив, а как сервис, т.е. скачать 32-bit/64-bit Windows Service Installer (pgp, sha512)
   - Запустить проект и проверить, что приходит ответ от сервлета.

4. Самостоятельно сделать похожее многомодульное приложение.
   - Добавить в родительский pom dependencyManagment и pluginManagement.
   - Все версии зависимостей должны объявляться в родительском pom.
   - Проанализировать, когда зависимости подтягиваются дочерними модулями.

## Доп.задание: Добавление базы данных
1. Сделать в модуле database базу данных на основе json файлов:
   - Сейчас вся наша модель данных это класс User
   - Должен быть список пользователей
   - При запуске приложения должны создаваться тестовые данные и записываться в json
   - При работе приложения, мы читаем и меняем данные в json, т.е. надо делать сериализацию в json каждый раз когда меняется модель данных

2. Добавить обработку следующих запросов в модуль web:
   - Получить список всех пользователей
   - Вернуть информацию о пользователе по его Id
   - Изменить имя пользователя по его Id

