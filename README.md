---
## Введение:
---
Обозначение    | Уровень важности
--------|-------------------------------------------------------------------------------
**(\*\*\*\*)**   | Без изучения этого материала невозможно успешное прохождение темы
**(\*\*\*)**     | Материал, без которого сложно успешно завершить изучение темы
**(\*\*)**       | Важный материал, рекомендуемый к изучению
**(\*)**         | Полезная литература, улучшающая навыки

---
## Правила логирования времени и прохождения программы обучения
---
### Логирование времени
Для ментора первостепенно понимать, сколько именно времени тратится на ту или иную задачу. Поэтому логирование времени осуществяется следующим образом.

1. Логируем в саму задачу:
+ Время, затраченное на выполнение практического задания;
+ Время на прочтение теории, необходимой для выполнения практического задания;

2. Логируем в Разработка/Прочая активность/Другое:
+ Время, затраченное на ожидание проверки выполненного задания ментором;
+ Время на прочтение прочей учебной литературы (список рекомендуемой литературы представлен в отдельном разделе);

### Прохождение программы обучения
 Программа обучения разделена на секции. Каждая секция состоит из
 + Теоретической части;
 + Базовой части (опционально)
 + Практической части;
 + Теста;
 + Интервью;

 Для каждой секции выполняется практическое задание. Теоретический материал изучается по мере необходимости для выполнения практики. После завершения практического задания необходимо в gitlab создать merge request на ментора, чтобы он смог проверить задание. Если задание выполнено успешно, то ментор предоставляет тест по пройденной секции. Для успешного прохождения теста в большинстве случаев достаточно знаний, полученных в ходе выполнения практического задания и прочтения необходимой для него теории.
 После прохождения теста ментор проводит небольшое интервью, чтобы убедиться в том, что материал успешно осознан и отложился в голове. В случае неудовлетворительного прохождения интервью ментор может направить доучивать материал, а потом снова провести интервью.
 Если присутствует задание на реализацию методов, их можно проверять Unit тестами на правильность выполнения, до отправки на проверку ментору.

 Стоит отметить, что ментор в силу различных обстоятельств не всегда может оперативно проверять merge request'ы и предоставлять тесты. Поэтому, если ментор вам говорит, что сможет проверить задание/предоставить тест только через несколько часов - приступайте к выполнению следующей секции программы обучения.
 **Важно** одновременно непроверенным может быть не более одной секции программы обучения. То есть, чтобы приступить к 5ой секции, Ваше практическое задание по 3ей секции должно быть одобрено, а тест пройден.

 В случае возникновения вопросов во время выполнения практического задания, можно просить помощи у ментора. Однако не стоит подходить к ментору с недекомпозированной задачей из разряда "Я не понимаю, как сверстать экран". Декомпозируйте задачу, чтобы задать ментору более конкретный вопрос. Также не стоит сразу же спрашивать ментора, как только возникла трудность. Для начала попробуйте самостоятельно найти ответ на свой вопрос в интернете.



 ---
 ## I. Основные принципы разработки. Git. Flow проектов
 ---
 ### Теоретическая часть

 **1. ООП**
 + [Основные принципы](https://javarush.ru/groups/posts/principy-oop)  **(\*\*\*\*)**

 **2. Работа с Git, gitflow**
 + [Видео-лекция Яндекса: Школа мобильной разработки – Git & Workflow. Дмитрий Складнов](https://www.youtube.com/watch?v=_TiUg1-SUzI) Лектор описывает что такое VCS, какие они бывают и пример workflow для работы с репозиторием **(\*\*\*)**
 + [Основные команды](https://git-scm.com/book/ru/v2) : init, clone, add, status, stash, commit (-m, -am, --amend), fetch, pull, push, branch, checkout, merge **(\*\*\*\*)**
 + Что такое [git flow](https://kb.simbirsoft.com/article/gitflow-method-overview/) **(\*\*\*)**
 + [Первоначальная настройка](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%9F%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0-Git): конфигурация username и email **(\*\*\*)**
 + [Изменение истории в git](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History). Почему это может быть [опасно](https://spin.atomicobject.com/2018/05/08/modify-git-history/)? **(\*\*\*)**
 + Опасность использования команды force и её альтернатива - [force-with-lease](https://blog.developer.atlassian.com/force-with-lease/), а также [возможные варианты решений](https://medium.com/@vitaliystanyshevskyy/git-push-origin-master-force-eec683936622) **(\*\*\*)**
 + История действий в git - [reflog](https://git-scm.com/docs/git-reflog) **(\*\*\*)**

 **3. Ведение проектов**
 +  [Flow проекта, ведение в Power Steering](https://kb.simbirsoft.com/article/tasktracker/) **(\*\*\*)**

 **4. Создание проекта, среда разработки Android Studio**
 + [Установка Android Studio](https://developer.android.com/studio) **(\*\*\*\*)**
 + [Создание нового проекта](https://developer.android.com/training/basics/firstapp/index.html) **(\*\*\*\*)**
 + [Основы интерфейса Android Studio](https://developer.android.com/studio/intro/index.html) **(\*\*\*\*)**
 + [Горячие клавиши Android Studio](https://developer.android.com/studio/intro/keyboard-shortcuts) **(\*\*)**
 + [Горячие клавиши Android Studio, повышение производительности](https://habr.com/ru/post/359376/) **(\*\*\*)**

 **5. Gradle**
 + [Gradle](https://developer.android.com/studio/build/index.html) **(\*\*\*)**

 **6. Codelabs**
 + [Android Studio and Hello World](https://codelabs.developers.google.com/codelabs/android-training-hello-world/index.html?index=..%2F..index#0) **(\*\*)**


 ### Практическое задание
 1. При старте работ над практическим заданием, необходимо получить доступ к issue в системе [PS](https://ps.simbirsoft.com). Создать task с названием "I. Основные принципы разработки. Git. Flow проектов" и обновить статус задачи на "В процессе".
 2. В [корпоративном gitlab](http://gitlab.simbirsoft/) создать новый репозиторий и следуя инструкциям, склонировать его к себе на компьютер. В настройках репозитория дать доступ ментору и руководителю отдела.
 3. В глобальных конфигурациях git прописать корректное имя пользователя и e-mail, которые будут использоваться для подписи коммитов.
 4. Добавить `.gitignore`. Содержание файла можно взять с ресурса: https://www.gitignore.io/api/androidstudio. Cделать коммит и запушить изменения на remote-сервер в `master` ветку
 5. Переключиться на новую ветку `develop`.
 6. Создать новый android-проект (Phone and Tablet -> Empty Activity).
 7. Добавить в gradle-файл библиотеку retrofit http://square.github.io/retrofit/
 8. Запустить проект на симуляторе
 9. Запустить проект на телефоне
 10. Сделать коммит и запушить изменения на remote-сервер в `develop` ветку
 11. Пройти все задания на ресурсе [LearnGitBranching](https://learngitbranching.js.org/?locale=ru_RU). Обратите внимание, что эта часть практического задания также обязательна к выполнению в полном объёме.
 12. После завершения работ над заданием перевести задачу в PS в статус "Завершен" и залогировать затраченное время.



---
## II. Java. Часть 1
---
### Теоретическая часть

**Основы**
+ [Java-платформа](https://docs.oracle.com/javase/tutorial/getStarted/intro/definition.html) **(\*\*)**
+ [Типы данных и переменные](https://metanit.com/java/tutorial/2.1.php) **(\*\*\*\*)**
+ [Преобразования базовых типов данных](https://metanit.com/java/tutorial/2.2.php) **(\*\*\*\*)**
+ [Операции языка Java](https://metanit.com/java/tutorial/2.3.php) **(\*\*\*\*)**
+ [Массивы](https://metanit.com/java/tutorial/2.4.php) **(\*\*\*\*)**
+ [Условные конструкции](https://metanit.com/java/tutorial/2.5.php) **(\*\*\*\*)**
+ [Циклы](https://metanit.com/java/tutorial/2.6.php) **(\*\*\*\*)**
+ [Методы](https://metanit.com/java/tutorial/2.7.php) **(\*\*\*\*)**
+ [Рекурсивные функции](https://metanit.com/java/tutorial/2.8.php) **(\*\*\*)**
+ [Введение в обработку исключений](https://metanit.com/java/tutorial/2.10.php) **(\*\*)**
+ [Java Code Conventions - Oracle](http://www.oracle.com/technetwork/java/codeconventions-150003.pdf) **(\*\*\*\*)**
+ [Классы и объекты](https://metanit.com/java/tutorial/3.1.php)  **(\*\*\*\*)**
+ [Пакеты](https://metanit.com/java/tutorial/3.2.php)  **(\*\*\*\*)**
+ [Модификаторы доступа и инкапсуляция](https://metanit.com/java/tutorial/3.3.php)  **(\*\*\*\*)**
+ [Статические члены и модификатор static](https://metanit.com/java/tutorial/3.4.php)  **(\*\*\*\*)**
+ [Наследование, полиморфизм и ключевое слово super](https://metanit.com/java/tutorial/3.5.php)  **(\*\*\*\*)**
+ [Класс Object и его методы](https://habrahabr.ru/post/168195/)  **(\*\*\*)**
+ [Объекты как параметры методов](https://metanit.com/java/tutorial/3.14.php)  **(\*\*\*\*)**

**Отладка**
+ [Android Studio Debugging: Базовые понятия](https://medium.com/@artem_shevchenko/android-studio-debugging-%D0%B1%D0%B0%D0%B7%D0%BE%D0%B2%D1%8B%D0%B5-%D0%BF%D0%BE%D0%BD%D1%8F%D1%82%D0%B8%D1%8F-%D0%B8-%D0%B2%D0%BE%D0%B7%D0%BC%D0%BE%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D0%B8-658ee6dcc641) **(\*\*\*)**
+ [Android Studio Debugging: Продвинутый уровень](https://medium.com/@artem_shevchenko/android-studio-debugging-%D0%BF%D1%80%D0%BE%D0%B4%D0%B2%D0%B8%D0%BD%D1%83%D1%82%D1%8B%D0%B9-%D1%83%D1%80%D0%BE%D0%B2%D0%B5%D0%BD%D1%8C-e05dac22439f) **(\*\*\*)**


### Практическое задание
Все задачи должны быть выполнены в указанном репозитории в отдельной ветке, вида training/lastname_firstname_date. После выполнения должен быть создан merge request на ментора.

***Важно! Весь код должен быть написан по предоставленному Java Code Conventions***

1. Создать task в PS с заголовком "II. Java. Часть 1" и взять ее в работу.
2. [Работа с примитивными типами](http://gitlab.simbirsoft/mobile/javacoreTraining/blob/master/app/src/main/java/com/example/user/javacoretraining/training/ElementaryTraining.java)
3. [Работа с массивами](http://gitlab.simbirsoft/mobile/javacoreTraining/blob/master/app/src/main/java/com/example/user/javacoretraining/training/ArraysTraining.java)
4. Завершить task в PS и залогировать затраченное время.



---
## III. Java. Часть 2
---
### Теоретическая часть

**1. Классы**
+ [Абстрактные классы](https://metanit.com/java/tutorial/3.6.php)  **(\*\*\*\*)**
+ [Иерархия наследования и преобразование типов](https://metanit.com/java/tutorial/3.10.php)  **(\*\*\*\*)**
+ [Внутренние классы](https://metanit.com/java/tutorial/3.12.php)  **(\*\*\*\*)**
+ [Интерфейсы](https://metanit.com/java/tutorial/3.7.php)  **(\*\*\*\*)**
+ [Интерфейсы в механизме обратного вызова](https://metanit.com/java/tutorial/3.16.php)  **(\*\*\*)**
+ [Перечисления enum](https://metanit.com/java/tutorial/3.8.php)  **(\*\*\*\*)**
+ [Обобщенные типы и методы](https://metanit.com/java/tutorial/3.11.php)  **(\*\*\*)**
+ [Наследование и обобщения](https://metanit.com/java/tutorial/3.15.php)  **(\*\*\*)**
+ [Типы ссылок](https://javadevblog.com/tipy-ssy-lok-v-java-strongreference-weakreference-softreference-i-phantomreference.html)  **(\*\*\*)**
+ [Ссылочные типы и клонирование объектов](https://metanit.com/java/tutorial/3.13.php) **(\*\*\*)**

**2. Обработка исключений**
+ [Оператор throws](https://metanit.com/java/tutorial/4.1.php) **(\*\*\*\*)**
+ [Классы исключений](https://metanit.com/java/tutorial/4.2.php) **(\*\*\*\*)**
+ [try-with-resources](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html) **(\*\*\*\*)**
+ [Создание своих классов исключений](https://metanit.com/java/tutorial/4.3.php) **(\*\*)**

**3. Коллекции**
+ [Введение в коллекции в Java](https://metanit.com/java/tutorial/5.1.php) **(\*\*\*\*)**
+ [Класс ArrayList и интерфейс List](https://metanit.com/java/tutorial/5.2.php) **(\*\*\*\*)**
+ [Структуры данных в картинках. ArrayList](https://habr.com/en/post/128269/) **(\*\*\*\*)**
+ [Класс LinkedList](https://metanit.com/java/tutorial/5.3.php) **(\*\*)**
+ [Структуры данных в картинках. LinkedList](https://habr.com/en/post/127864/) **(\*\*)**
+ [Класс HashSet](https://metanit.com/java/tutorial/5.4.php) **(\*\*\*\*)**
+ [Класс TreeSet](https://metanit.com/java/tutorial/5.5.php) **(\*\*\*)**
+ [Интерфейсы Comparable и Comporator. Сортировка](https://metanit.com/java/tutorial/5.6.php) **(\*\*\*)**
+ [Очереди и класс ArrayDeque](https://metanit.com/java/tutorial/5.7.php) **(\*\*)**
+ [Отображения и класс HashMap](https://metanit.com/java/tutorial/5.8.php) **(\*\*\*\*)**
+ [Структуры данных в картинках. HashMap](https://habr.com/en/post/128017/) **(\*\*\*\*)**
+ [Класс TreeMap](https://metanit.com/java/tutorial/5.9.php) **(\*\*)**
+ [Итераторы](https://metanit.com/java/tutorial/5.10.php) **(\*\*)**
+ [SparseArray](https://developer.android.com/reference/android/util/SparseArray?hl=ru) **(\*\*)**
+ [ArrayMap](https://developer.android.com/reference/android/util/ArrayMap?hl=ru) **(\*\*)**
+ [ArrayMap&SparseArray](https://android.jlelse.eu/app-optimization-with-arraymap-sparsearray-in-android-c0b7de22541a) **(\*\*)**

**4. Работа со строками**
+ [Введение в строки. Класс String](https://metanit.com/java/tutorial/7.1.php) **(\*\*\*\*)**
+ [Основные операции со строками](https://metanit.com/java/tutorial/7.2.php) **(\*\*\*\*)**
+ [StringBuffer и StringBuilder](https://metanit.com/java/tutorial/7.3.php) **(\*\*\*)**
+ [Регулярные выражения](https://metanit.com/java/tutorial/7.4.php) **(\*\*)**

**5. Лямбда-выражения**
+ [Введение в лямбда-выражения](https://metanit.com/java/tutorial/9.1.php) **(\*\*\*\*)**
+ [Лямбды как параметры методов и ссылки на методы](https://metanit.com/java/tutorial/9.2.php) **(\*\*\*\*)**
+ [Встроенные функциональные интерфейсы](https://metanit.com/java/tutorial/9.3.php) **(\*\*)**


### Базовое задание
Все задачи должны быть выполнены в указанном репозитории в отдельной ветке, вида training/lastname_firstname_date. После выполнения должен быть создан merge request на ментора.

1. [Работа со списками](http://gitlab.simbirsoft/mobile/javacoreTraining/blob/master/app/src/main/java/com/example/user/javacoretraining/collections/CollectionsBlock.java)
2. [Работа с классами](http://gitlab.simbirsoft/mobile/javacoreTraining/blob/master/app/src/main/java/com/example/user/javacoretraining/classes/ClassesBlock.java)
3. [Работа со строками](http://gitlab.simbirsoft/mobile/javacoreTraining/blob/master/app/src/main/java/com/example/user/javacoretraining/training/StringsTraining.java)


### Практическое задание
Все задачи должны быть реализованы в одном файле и разделены комментариями, указывающими на номер или текст задания.

**Важно! Весь код должен быть написан по предоставленному Java Code Conventions**
1. Создать task в PS с заголовком "II. Java. Часть 2" и взять ее в работу.
2. Настроить проект для [java 8](https://developer.android.com/studio/write/java8-support.html?utm_source=android-studio)
3. Написать простое лямбда-выражение в переменной `myClosure`, лямбда-выражение должно выводить в консоль фразу "I love Java". Вызвать это лямбда-выражение. Далее написать функцию, которая будет запускать заданное лямбда-выражение заданное количество раз. Объявить функцию так: `public void repeatTask (int times, Runnable task)`. Функция должна запускать `times` раз лямбда-выражение `task` . Используйте эту функцию для печати "I love Java" 10 раз.
4. Условия: есть начальная позиция на двумерной плоскости, можно осуществлять последовательность шагов по четырем направлениям up, down, left, right. Размерность каждого шага равна 1. Задание:
  - Создать enum `Directions` с возможными направлениями движения
  - Создать метод, принимающий координаты и одно из направлений и возвращающий координаты после перехода по направлению
  - Создать метод, осуществляющий несколько переходов от первоначальных координат и выводящий координаты после каждого перехода. Для этого внутри метода следует определить переменную `location` с начальными координатами (0,0) и  массив направлений, содержащий элементы [up, up, left, down, left, down, down, right, right, down, right], и програмно вычислить какие будут координаты у переменной `location` после выполнения этой последовательности шагов. Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод перемещения на один шаг.
5. Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр и площадь фигуры соответственно, после чего реализовать и использовать для вывода периметра и площади следующие классы, реализующие интерфейс Shape:
  - `Rectangle` - прямоугольник с двумя свойствами: ширина и длина
  - `Square` - квадрат с одним свойством: длина стороны
  - `Circle` - круг с одним свойством: диаметр круга
6. Завершить task в PS и залогировать затраченное время.



---
## IV. Kotlin. Часть 1.
---
### Теоретическая часть

 **1. Основы**
 + [Введение в kotlin](https://kotlinlang.ru/docs/reference/basic-syntax.html) **(\*\*\*\*)**
 + [Основные типы](https://kotlinlang.ru/docs/reference/basic-types.html) **(\*\*\*\*)**
 + [Пакеты](https://kotlinlang.ru/docs/reference/packages.html) **(\*\*\*\*)**
 + [Условия, циклы](https://kotlinlang.ru/docs/reference/control-flow.html) **(\*\*\*\*)**
 + [Return, break, continue](https://kotlinlang.ru/docs/reference/returns.html) **(\*\*\*\*)**
 + [Классы и наследование](https://kotlinlang.ru/docs/reference/classes.html) **(\*\*\*\*)**
 + [Свойства и поля](https://kotlinlang.ru/docs/reference/properties.html) **(\*\*\*\*)**
 + [Интерфейсы](https://kotlinlang.ru/docs/reference/interfaces.html) **(\*\*\*\*)**
 + [Модификаторы доступа](https://kotlinlang.ru/docs/reference/visibility-modifiers.html) **(\*\*\*\*)**
 + [Функции](https://kotlinlang.ru/docs/reference/functions.html) **(\*\*\*\*)**
 + [Лямбда-выражения](https://kotlinlang.ru/docs/reference/lambdas.html) **(\*\*\*\*)**
 + [Ключевое слово this](https://kotlinlang.ru/docs/reference/this-expressions.html) **(\*\*\*\*)**
 + [Равенство](https://kotlinlang.ru/docs/reference/equality.html) **(\*\*\*\*)**
 + [Null безопасность](https://kotlinlang.ru/docs/reference/null-safety.html) **(\*\*\*\*)**
 + [Функции области видимости](https://kotlinlang.ru/docs/reference/scope-functions.html) **(\*\*\*\*)**

### Практическое задание
**Важно!**
Работа должна производится в созданном ранее проекте в отдельном файле. Задачи должны быть разделены комментариями, указывающими на номер или текст задания.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `kotlin-part-1` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Создать task в PS с заголовком "IV. Kotlin. Часть 1." и взять ее в работу.
2. Необходимо создать интерфейс `Publication`, у которого должно быть свойства – `price` и `wordCount`, а также метод `getType`, возвращающий строку. Создать два класса, реализующих данный интерфейс – Book и Magazine.
В методе `getType` для класса `Book` возвращаем строку с зависимости от количества слов. Если количество слов не превышает 1000, то вывести “Flash Fiction”, 7,500 –“Short Story”, всё, что выше – “Novel”. Для класса `Magazine` возвращаем строку “Magazine”.
3. Создать два объекта класса `Book` и один объект `Magazine`. Вывести в лог для каждого объекта тип, количество строк и цену в евро в отформатированном виде.
 У класса `Book` переопределить метод `equals` и произвести сравнение сначала по ссылке, затем используя метод `equals`. Результаты сравнений вывести в лог.
4. Создать метод `buy`, который в качестве параметра принимает `Publication` (notnull - значения) и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”. Создать две переменных класса `Book`, в которых могут находиться null значения. Присвоить одной null, а второй любое notnull значение и вызвать метод `buy` с каждой из переменных.
5. Создать переменную `sum` и присвоить ей лямбда-выражение, которое будет складывать два переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение с произвольными параметрами.



---
## V. Kotlin. Часть 2.
---
### Теоретическая часть

 **1. Коллекции**
 + [Коллекции](https://kotlinlang.org/docs/reference/collections-overview.html) **(\*\*\*\*)**
 + [Создание коллекций](https://kotlinlang.org/docs/reference/constructing-collections.html) **(\*\*\*\*)**
 + [Обзор операций для коллекций](https://kotlinlang.org/docs/reference/collection-operations.html) **(\*\*\*\*)**
 + [Преобразования коллекций](https://kotlinlang.org/docs/reference/collection-transformations.html) **(\*\*\*\*)**
 + [Фильтрация коллекций](https://kotlinlang.org/docs/reference/collection-filtering.html) **(\*\*\*\*)**
 + [Группировка коллекций](https://kotlinlang.org/docs/reference/collection-grouping.html) **(\*\*\*\*)**
 + [Получение частей коллекции](https://kotlinlang.org/docs/reference/collection-parts.html) **(\*\*\*\*)**
 + [Получение элементов коллекции](https://kotlinlang.org/docs/reference/collection-elements.html) **(\*\*\*\*)**
 + [Сортировка коллекции](https://kotlinlang.org/docs/reference/collection-ordering.html) **(\*\*\*\*)**
 + [Операторы записи](https://kotlinlang.org/docs/reference/collection-write.html) **(\*\*\*\*)**
 + [Итераторы](https://kotlinlang.org/docs/reference/iterators.html) **(\*\*\*)**
 + [Интервалы](https://kotlinlang.ru/docs/reference/ranges.html) **(\*\*\*)**
 + [Последовательности](https://kotlinlang.org/docs/reference/sequences.html) **(\*\*\*)**
 + [Операторы плюс и минус](https://kotlinlang.org/docs/reference/collection-plus-minus.html) **(\*\*\*)**
 + [Операторы объединения](https://kotlinlang.org/docs/reference/collection-aggregate.html) **(\*\*\*)**
 + [Особые операторы для списков](https://kotlinlang.org/docs/reference/list-operations.html) **(\*\*\*)**
 + [Особые операторы для множеств](https://kotlinlang.org/docs/reference/set-operations.html) **(\*\*\*)**
 + [Особые операторы для 'Map'](https://kotlinlang.org/docs/reference/map-operations.html) **(\*\*\*)**

 **2. Расширенный раздел**
 + [Исключения](https://kotlinlang.ru/docs/reference/exceptions.html) **(\*\*\*\*)**
 + [Расширения (функции и свойства)](https://kotlinlang.ru/docs/reference/extensions.html) **(\*\*\*\*)**
 + [Классы данных](https://kotlinlang.ru/docs/reference/data-classes.html) **(\*\*\*\*)**
 + [Изолированные классы](https://kotlinlang.ru/docs/reference/sealed-classes.html) **(\*\*\*\*)**
 + [Generics](https://kotlinlang.ru/docs/reference/generics.html) **(\*\*\*\*)**
 + [Вложенные классы](https://kotlinlang.ru/docs/reference/nested-classes.html) **(\*\*\*\*)**
 + [Enum](https://kotlinlang.ru/docs/reference/enum-classes.html) **(\*\*\*\*)**
 + [Объекты](https://kotlinlang.ru/docs/reference/object-declarations.html) **(\*\*\*\*)**
 + [Делегирование](https://kotlinlang.ru/docs/reference/delegation.html) **(\*\*\*\*)**
 + [Делегированные свойства](https://kotlinlang.ru/docs/reference/delegated-properties.html) **(\*\*\*\*)**
 + [Встроенные функции](https://kotlinlang.ru/docs/reference/inline-functions.html) **(\*\*\*\*)**
 + [Аннотации](https://kotlinlang.ru/docs/reference/annotations.html) **(\*\*\*)**
 + [Приведение и проверка типов](https://kotlinlang.ru/docs/reference/typecasts.html) **(\*\*\*)**
 + [Функциональные интерфейсы (SAM)](https://kotlinlang.org/docs/reference/fun-interfaces.html) **(\*\*)**
 + [Псевдонимы типов](https://kotlinlang.ru/docs/reference/type-aliases.html) **(\*\*)**
 + [Встроенные классы](https://kotlinlang.org/docs/reference/inline-classes.html) **(\*\*)**
 + [Мульти-декларации](https://kotlinlang.ru/docs/reference/multi-declarations.html) **(\*\*)**
 + [Перегрузка операторов](https://kotlinlang.ru/docs/reference/operator-overloading.html) **(\*\*)**
 + [Рефлексия](https://kotlinlang.ru/docs/reference/reflection.html) **(\*\*)**

 **3. Лучшие практики**
 + [Идиомы](https://kotlinlang.ru/docs/reference/idioms.html) **(\*\*\*)**
 + [Стилистика кода](https://kotlinlang.ru/docs/reference/coding-conventions.html) **(\*\*\*)**
 + [Неочевидный Kotlin. Советы, трюки, подводные камни](Материалы/Kotlin.pdf) **(\*\*\*)**
 + [Лучшие практики](https://phauer.com/2017/idiomatic-kotlin-best-practices/) **(\*\*\*)**
 + [Лучшие практики для написания чистого кода](https://blog.intive-fdv.com/best-practices-to-write-a-clean-code-with-kotlin-part-ii/) **(\*\*\*)**
 + [Использование последовательностей](https://blog.kotlin-academy.com/effective-kotlin-use-sequence-for-bigger-collections-with-more-than-one-processing-step-649a15bb4bf) **(\*\*\*)**
 + [Лучшие практики для написания тестов](https://resources.jetbrains.com/storage/products/kotlinconf2018/slides/4_Best%20Practices%20for%20Unit%20Testing%20in%20Kotlin.pdf) **(\*\*)**

 **4. Антипаттерны**
 + [Антипаттерны](https://github.com/Zhuinden/guide-to-kotlin/wiki), см. часть 6 - Антипаттерны **(\*\*\*)**
 + [Ещё немного антипаттернов](https://www.notion.so/Java-Kotlin-fdd36663b9e4452b9c605107761278b4) **(\*\*\*)**

 **5. Kotlin koans**
 + [Kotlin koans](https://www.jetbrains.com/help/education/learner-start-guide.html?section=Kotlin%20Koans)

### Практическое задание
**Важно!**
Работа должна производится в созданном ранее проекте в отдельном файле. Задачи должны быть разделены комментариями, указывающими на номер или текст задания.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `kotlin-part-2` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Создать task в PS с заголовком "V. Kotlin. Часть 2." и взять ее в работу.
2. Создать enum `Type` с константами `DEMO` и `FULL`.
3. Реализовать класс данных `User` с полями `id`, `name`, `age` и `type`. У класса `User` создать ленивое свойство `startTime`, в котором получаем текущее время.
4. Создать объект класса `User`, вывести в лог startTime данного юзера, после вызвать Thread.sleep(1000) и повторно вывести в лог startTime.
5. Создать список пользователей, содержащий в себе один объект класса `User`. Используя функцию **apply**, добавить ещё несколько объектов класса `User` в список пользователей.
6. Получить список пользователей, у которых имеется полный доступ (поле `type` имеет значение `FULL`).
7. Преобразовать список `User` в список имен пользователей. Получить первый и последний элементы списка и вывести их в лог.
8. Создать функцию-расширение класса `User`, которая проверяет, что юзер старше 18 лет, и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку.
9. Создать интерфейс `AuthCallback` с методами `authSuccess`, `authFailed` и реализовать анонимный объект данного интерфейса. В методах необходимо вывести в лог информацию о статусе авторизации.
10. Реализовать **inline** функцию `auth`, принимающую в качестве параметра функцию `updateCache`. Функция `updateCache `должна выводить в лог информацию об обновлении кэша.
11. Внутри функции `auth` вызвать метод коллбека `authSuccess` и переданный `updateCache`, если проверка возраста пользователя произошла без ошибки. В случае получения ошибки вызвать `authFailed`.
12. Реализовать изолированный класс `Action` и его наследников – `Registration`, `Login` и `Logout`. `Login` должен принимать в качестве параметра экземпляр класса User.
13. Реализовать метод `doAction`, принимающий экземпляр класса `Action`. В зависимости от переданного действия выводить в лог текст, к примеру “Auth started”. Для действия `Login` вызывать метод `auth`.



---
## VI. Верстка
---
### Теоретическая часть

В случае если по ссылке встречается пошаговый гайд - рекомендуется его выполнить в отдельном проекте.

**1. Начало разработки под Android**
+ [Начало разаработки](https://developer.android.com/training/index.html) **(\*\*\*\*)**

**2. Верстка**
+ [Уроки верстки из курсов](http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html) **(\*\*)**
+ [Создание макетов в XML и View groups](https://developer.android.com/guide/topics/ui/declaring-layout.html) **(\*\*\*)**

**3. Типы layout'ов**
+ [Frame Layout](http://developer.alexanderklimov.ru/android/layout/framelayout.php) **(\*\*\*\*)**
+ [Linear Layout](https://developer.android.com/guide/topics/ui/layout/linear.html) **(\*\*\*\*)**
+ [Relative Layout](https://developer.android.com/guide/topics/ui/layout/relative.html) (если есть возможность, лучше использовать Constraint) **(\*\*\*\*)**

**4. Splash Screen**
+ [Как правильно реализовать](https://habr.com/ru/post/345380/) **(\*\*\*\*)**

**5. BottomAppBar**
+ [Обзор](https://developer.android.com/reference/com/google/android/material/bottomappbar/BottomAppBar) **(\*\*\*\*)**

**6. App Bar**
+ [Обзор](https://developer.android.com/training/appbar) **(\*\*\*\*)**

**7. Constraint Layout**
+ [Документация](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) **(\*\*\*\*)**
+ [Работа с различными свойствами](https://habrahabr.ru/company/touchinstinct/blog/326814/) **(\*\*\*\*)**

**8. Coordinator Layout**
+ [Документация](https://developer.android.com/jetpack/androidx/releases/coordinatorlayout?hl=ru) **(\*\*\*\*)**
+ [Особенности использования](https://blog.mindorks.com/using-coordinator-layout-in-android) **(\*\*\*\*)**

**9. Ресурсы**
+ [Обзор](https://developer.android.com/guide/topics/resources/providing-resources) **(\*\*\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – MyFirstApp (Часть 1) Роман Григорьев](https://youtu.be/jVwGU3UJVPc) Описывает ресурсы андроид приложения, а так же различие релизной, дебажной сборки и signing config **(\*\*)**
+ [Локализация](https://developer.android.com/guide/topics/resources/localization) **(\*\*)**
+ [Типы ресурсов](https://developer.android.com/guide/topics/resources/available-resources) **(\*\*)**
+ [Шрифты в XML](https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml.html) **(\*\*)**
+ [Загружаемые шрифты](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html) **(\*\*)**
+ [Поддержка разных экранов](https://developer.android.com/guide/practices/screens_support.html) **(\*\*)**
+ [Zeplin](https://habrahabr.ru/company/uteam/blog/315542/) **(\*\*\*)**

**10. Codelabs**
+ [Your first interactive UI](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-a/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [The layout editor](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Text and scrolling views](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Drawables, styles, and themes](https://codelabs.developers.google.com/codelabs/android-training-drawables-styles-and-themes/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Clickable images](https://codelabs.developers.google.com/codelabs/android-training-clickable-images/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Input controls](https://codelabs.developers.google.com/codelabs/android-training-input-controls/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Menus and pickers](https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [User navigation](https://codelabs.developers.google.com/codelabs/android-training-provide-user-navigation/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Adaptive layouts](https://codelabs.developers.google.com/codelabs/android-training-adaptive-layouts/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**


**Важно** В компании при разработке любого мобильного приложения считается правилом хорошего тона придерживаться нефункциональных требований, описанных в [данной статье](http://kb.simbirsoft/nonfunctional-support/)

### Практическое задание
Работа должна производится в созданном ранее проекте из блока I.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `layouts` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Создать task в PS с заголовком "VI. Верстка" и взять ее в работу.
2. Сделать так, чтобы на домашнем экране Android отображалась иконка и название приложения "Хочу помочь". Ресурсы иконок [тут](https://zpl.io/2jkoMOp).
3. Реализовать Splash Screen согласно [макету](https://zpl.io/2jlk3Mm).
4. Реализовать экран "Профиль" согласно [макету](https://zpl.io/b6lQpZq).
 - Экран "Профиль" необходимо отображать после Splash Screen. По нажатию стрелки назад, приложение закрывается.
 - Необходимо реализовать нижний элемент навигации с помощью стандартного `BottomNavigationView`. Пункт "Помочь" визуально не должен отличаться от остальных четырех. Размеры иконок оставить стандартные для `BottomNavigationView` - 24dp.
 - В нижнем меню навигации по-умолчанию должен быть выбран пункт "Профиль".
 - Верстка должна быть реализована в xml.
 - Верстка должна быть выполнена с учетом "pixel perfect" - когда все элементы дизайна расположены и имеют размеры абсолютно идентичные макету для экрана с теми же размерами, что и макет, и адекватно масштабироваться для других размеров и разрешений.
 - Все переиспользуемые размеры в xml должны быть вынесены в dimes, цвета в colors, а строки в strings.
 - Никаких "магических чисел", все должно иметь понятные названия.
5. Реализовать поведение toolbar-a на экране профиля при скролле, используя CoordinatorLayout.
 - При прокрутке вниз toolbar вместе с изображением профиля должен постепенно скрываться.
 - При прокрутке наверх до конца toolbar вместе с изображением профиля должен полностью отображаться.
 - При полной прокрутке вниз и последующей прокрутке наверх сразу должен отобразиться toolbar без изображения профиля. По мере скролла наверх изображение профиля должно появиться.
6. Подключить статические анализаторы кода и исправить выявленные замечания
 - Подключить через gradle [ktlint](https://ktlint.github.io/)
 - Установить плагин [SonarLint](https://www.sonarlint.org/)
 - Запустить и исправить выявленные [lint](https://developer.android.com/studio/write/lint) замечания
 - Запустить и исправить выявленные ktlint замечания
 - Запустить и исправить выявленные SonarLint замечания
7. Завершить task в PS и залогировать затраченное время
