# Запуск теста
## Запуск из консоли
Тест можно запустить перейдя в папку проекта и выполнив команду:
 + Для windows: `gradlew.bat clean runTest allureReport --continue`
 + Для linux: `sh gradlew clean runTest allureReport --continue`
 
 Первый запуск можно продлиться долго(~ 5 мин), так как потребуется скачать используемые библиотеки allure и впервые собрать проект.
 Последующие запуски занимают примерно 1,5 минуты на моей машине.
 
 ## Запуск из IDE
 Настройка IDE для запуска тестов задача не тривиальная(нужно хотя бы знать для какой IDE описывать  настройку:)), может потребоваться установка различных плагинов итп. Поэтому рассматривать не будем.
 
 # Просмотр результата
 После завершения теста в консоли выведется минимальный результат - сколько тестов прошло, сколько упало.
  
 Так же результат можно посмотреть в авто генерируемом отчете. Отчет находится здесь: `<путь до папки проекта>/RL-autotest-project/build/reports/tests/runTest/classes/CellListTests.html`
 
 Еще один вариант посмотреть сгенерированный отчет allure: `<путь до папки проекта>/RL-autotest-project/build/reports/allure-report/index.html`  
 С открытием отчета могу возникнуть проблемы, чтобы отчет корректно открывался нужно его подсунуть какому-нибудь веб-серверу(iis, nginx).  
 Для того чтобы не мучиться с веб-серверами, сделал скриншот готово allure-отчета: `<путь до папки проекта>/RL-autotest-project/allure_report_example.png`
 