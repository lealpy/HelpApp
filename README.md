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



---
## VII. Android OS. Activity. Fragments
---
### Теоретическая часть

**1. Android OS**
+ [История Android](https://www.android.com/history/#/marshmallow) **(\*\*)**
+ [Архитектура Android](https://source.android.com/devices/architecture/) **(\*\*)**

**2. Application**
+ [Application](https://developer.android.com/reference/android/app/Application.html)  **(\*\*\*)**
+ [Context](https://web.archive.org/web/20150329210012/https://possiblemobile.com/2013/06/context/)  **(\*\*\*)**
+ [Файл Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) **(\*\*\*\*)**

**3. Activity**
+ [Activity - основы](https://developer.android.com/guide/components/activities/intro-activities) **(\*\*\*\*)**
+ [Управление жизненным циклом Activity](https://developer.android.com/guide/components/activities/activity-lifecycle) **(\*\*\*\*)**
+ [Обработка изменений конфигурации экрана](https://developer.android.com/guide/topics/resources/runtime-changes.html) **(\*\*\*\*)**
+ [Task и Back Stack](https://habrahabr.ru/post/186434/) **(\*\*)**
+ [Передача данных между Activity. Обзор](https://developer.android.com/guide/components/activities/parcelables-and-bundles.html) **(\*\*\*\*)**
+ [Передача данных между Activity. Интерфейс Parcelable](https://metanit.com/java/android/2.13.php) **(\*\*\*\*)**
+ [Интерфейс Serializable](https://developer.android.com/reference/java/io/Serializable) **(\*\*)**
+ [Сравнение Parcelable и Serializable](https://android.jlelse.eu/parcelable-vs-serializable-6a2556d51538) **(\*\*)**

**4. Fragment**
+ [Fragment - основы](https://developer.android.com/guide/components/fragments.html) **(\*\*\*\*)**
+ [Диалоговые окна](https://developer.android.com/guide/topics/ui/dialogs.html) **(\*\*\*\*)**
+ [Передача данных между Fragment](https://developer.android.com/training/basics/fragments/communicating) **(\*\*\*\*)**
+ [Target fragment](https://habrahabr.ru/post/259805/) **(\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – Fragments (Часть 1). Денис Загаевский](https://youtu.be/3VXPsCUYioM?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz) Лектор весьма неплохо описывает концепцию фрагментов и как их использовать **(\*\*)**

**5. SearchView**
+ [Обзор](https://developer.android.com/guide/topics/search) **(\*\*\*\*)**

**6. ViewPager2**
+ [Обзор](https://developer.android.com/guide/navigation/navigation-swipe-view-2) **(\*\*\*\*)**

**7. Списки**
+ [ListView](http://developer.alexanderklimov.ru/android/views/listview.php) **(\*\*)**
+ [RecyclerView и Adapter](https://developer.android.com/training/material/lists-cards.html) **(\*\*\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – AdapterView. Владимир Тагаков](https://youtu.be/G35pcPv_tEA?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz)  Лектор рассказывает про recycleView, желательно смотреть только после того, как появится уже некоторый опыт в создании компонентов RecyclerView **(\*\*)**

**8. Работа со сторонними приложениями и permissions**
+ [Run-time permissions](https://developer.android.com/training/permissions/requesting.html)**(\*\*\*\*)**
+ [Intent и фильтры](https://developer.android.com/guide/components/intents-filters.html?hl=ru)**(\*\*\*\*)**
+ [Взаимодействие с другими приложениями](https://developer.android.com/training/basics/intents/index.html)**(\*\*\*\*)**
+ [Получение фото с камеры](https://developer.android.com/training/camera/photobasics)**(\*\*\*\*)**

**10. Codelabs**
+ [Activities and intents](https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Activity lifecycle and state](https://codelabs.developers.google.com/codelabs/android-training-activity-lifecycle-and-state/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Implicit intents](https://codelabs.developers.google.com/codelabs/android-training-activity-with-implicit-intent/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [RecyclerView](https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [Fragments](https://codelabs.developers.google.com/codelabs/advanced-android-training-fragments/index.html?index=..%2F..advanced-android-training#0) **(\*\*)**
+ [Fragment communication](https://codelabs.developers.google.com/codelabs/advanced-android-training-fragment-communication/index.html?index=..%2F..advanced-android-training#0) **(\*\*)**


### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `fragments` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Создать task в PS с заголовком "VII. Фрагменты" и взять ее в работу.
2. Реализовать диалог согласно [макету](https://zpl.io/brkmRYX)
 - Диалог необходимо открывать при нажатии на изображение пользователя на экране "Профиль".
 - По нажатию на кнопку "Выбрать фото" не должно ничего происходить.
 - По нажатию на кнопку "Сделать снимок" необходимо запускать камеру устройства. Сделанное фото должно заменять текущее на экране профиля
 - По нажатию на кнопку "Удалить" необходимо удалять текущее изображение пользователя на экране профиля
3. Реализовать экран "Категории помощи" приложения согласно [макету](https://zpl.io/b6lYE9d).
 - Стрелку назад верстать не надо.
 - Экран "Категории помощи" необходимо отображать после Splash Screen. Переход на экран профиля теперь происходит при выборе пункта "Профиль" в нижнем меню.
 - В нижнем меню навигации по-умолчанию должен быть выбран пункт "Помочь".
 - Экран должен представлять из себя fragment с `RecyclerView`.
4. Реализовать экран "Поиск" согласно [макету](https://zpl.io/bAGAPj8). Переход на этот экран осуществляется при выборе пункта "Поиск" в нижнем меню
 - Экран необходимо построить с использованием `SearchView`,`ViewPager2` и фрагментов.
 - У `SearchView` необходимо реализовать только верстку без логики.
 - Необходимо реализовать возможность изменять выбранную вкладку перелистыванием с плавной анимацией.
 - В качестве названий для результатов необходимо использовать произвольные случайные строки.
 - Данные для отображения результата необходимо генерировать случайным образом при каждом перелистывании `ViewPager2`.
5. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
6. Завершить task в PS и залогировать затраченное время



---
## VIII. Структуры данных. Работа с файлами
---
### Теоретическая часть

**1. Работа с файлами в java. Сериализация**
+ [Потоки ввода-вывода](https://metanit.com/java/tutorial/6.1.php)  **(\*\*\*\*)**
+ [Закрытие потоков](https://metanit.com/java/tutorial/6.2.php)  **(\*\*\*\*)**
+ [Чтение и запись файлов. FileInputStream и FileOutputStream](https://metanit.com/java/tutorial/6.3.php)  **(\*\*\*\*)**
+ [Классы ByteArrayInputStream и ByteArrayOutputStream](https://metanit.com/java/tutorial/6.4.php)  **(\*\*)**
+ [Буферизуемые потоки. Классы BufferedInputStream и BufferedOuputStream](https://metanit.com/java/tutorial/6.5.php)  **(\*\*\*\*)**
+ [Чтение и запись текстовых файлов. FileReader и FileWriter](https://metanit.com/java/tutorial/6.8.php)  **(\*\*)**
+ [Буферизируемые символьные потоки. BufferedReader и BufferedWriter](https://metanit.com/java/tutorial/6.9.php)  **(\*\*)**
+ [Сериализация объектов](https://metanit.com/java/tutorial/6.10.php)  **(\*\*)**
+ [Класс File. Работа с файлами и каталогами](https://metanit.com/java/tutorial/6.11.php)  **(\*\*\*\*)**
+ [Работа с ZIP-архивами](https://metanit.com/java/tutorial/6.12.php)  **(\*\*)**

**2. JSON**
+ [Понятие](https://ru.wikipedia.org/wiki/JSON)  **(\*\*\*\*)**
+ [Парсинг в Android](https://metanit.com/java/android/13.3.php)  **(\*\*\*\*)**
+ [Gson](https://habrahabr.ru/company/naumen/blog/228279/) **(\*\*\*\*)**
+ [Moshi](https://github.com/square/moshi?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1682) **(\*)**
+ [Jackson](https://github.com/FasterXML/jackson-core?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=230) **(\*)**

**3. Работа с файловой системой Android**
+ [SharedPreferences](https://developer.android.com/training/basics/data-storage/shared-preferences.html?hl=ru#GetSharedPreferences) **(\*\*\*\*)**
+ [Настройки через Preferences](https://developer.android.com/guide/topics/ui/settings.html) **(\*\*)**
+ [Android data storage](https://developer.android.com/training/basics/data-storage/files.html) **(\*\*\*\*)**
+ [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html) **(\*\*\*)**

**4. Работа с датой и временем**
+ [Date, Calendar](http://developer.alexanderklimov.ru/android/java/date.php) **(\*\*\*\*)**
+ [Date в Java 8](http://www.baeldung.com/java-8-date-time-intro) **(\*\*\*\*)**
+ [Работа со временем в java ](https://habrahabr.ru/post/274811/) **(\*\*)**

**5. DiffUtils**
+ [Обзор](https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00) **(\*\*\*\*)**
+ [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter.html?hl=en) **(\*\*\*\*)**

**6. Codelabs**
+ [Shared preferences](https://codelabs.developers.google.com/codelabs/android-training-shared-preferences/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [App settings](https://codelabs.developers.google.com/codelabs/android-training-adding-settings-to-app/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_structures` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. Верстка экранов должна быть выполнена по принципу pixel-perfect.

1. Создать task в PS с заголовком "VIII. Структуры данных" и взять ее в работу.
2. Доработать логику диалога на экране "Профиль".
 - По нажатию на кнопку "Выбрать фото" необходимо открывать галерею устройства. Выбранное в галерее фото должно заменять текущее на экране профиля
3. Подключить к проекту [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP). Все операции с датой и временем должны быть реализованы через классы данной библиотеки.
4. Сверстать экран "Новости" согласно [макету](https://zpl.io/brkm3we). Переход на этот экран осуществляется при выборе пункта "Новости" в нижнем меню. Экран содержит список благотворительных событий, относящихся к выбранным категориям.
 - Одно событие может относиться сразу к нескольких категориям помощи.
5. Сверстать экран "Фильтр" согласно [макету](https://zpl.io/2ZxPMeG). Переход на этот экран осуществляется при клике на иконку в правом верхнем углу экрана "Новости".
 - На экране вместо фразы "Как вы хотите помочь?" необходимо отображать "Категории помощи" и ниже список всех возможных категорий (категории, которые отображаются на главном экране - дети, пожилые и тд).
 - При смене категорий в фильтре обновлять список на экране "Новости" обязательно при помощи DiffUtils.
6. Сверстать экран "Детальное описание события" согласно [макету](https://zpl.io/adA93Z5). Переход на этот экран осуществляется при выборе любого события из списка. Данный экран должен получить информацию о том, какое событие было выбрано на предыдущем шаге.
7. Необходимо создать два json'а. Содержащих в себе массивы категорий и благотворительных событий. Информация об объектах должна быть достаточной для формирования отображений на экранах, а также для корректного разделения по категориям помощи. Каждый объект должен обладать уникальным (среди объектов своего типа) идентификатором. Проверить корректность созданных json-ов через [online-parser](http://json.parser.online.fr/). Записать их в 2 файла и поместить в папку assets проекта.
8. Необходимо создать сущности соответствующие понятиям Категория и Событие.
9. Создать класс, который будет читать созданные json из файлов, парсить их и преобразовывать в массивы.
10. Наполнить экраны полученными данными.
11. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
12. Завершить task в PS и залогировать затраченное время



---
## IX. Многопоточность
---
### Теоретическая часть

**1. Базовые понятия**
+ [Многопоточность. Определение](https://ru.wikipedia.org/wiki/%D0%9C%D0%BD%D0%BE%D0%B3%D0%BE%D0%BF%D0%BE%D1%82%D0%BE%D1%87%D0%BD%D0%BE%D1%81%D1%82%D1%8C)  **(\*\*\*\*)**
+ [Мьютекс](https://ru.wikipedia.org/wiki/%D0%9C%D1%8C%D1%8E%D1%82%D0%B5%D0%BA%D1%81)  **(\*\*)**
+ [Семафор](https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BC%D0%B0%D1%84%D0%BE%D1%80_(%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0))  **(\*\*)**
+ [Дэдлок](https://ru.wikipedia.org/wiki/Взаимная_блокировка)  **(\*\*\*)**
+ [Starvation and Livelock](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)  **(\*\*\*)**
+ [Атомарные операции](https://ru.wikipedia.org/wiki/%D0%90%D1%82%D0%BE%D0%BC%D0%B0%D1%80%D0%BD%D0%B0%D1%8F_%D0%BE%D0%BF%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D1%8F)  **(\*\*\*)**
+ [Atomic типы](http://java-online.ru/concurrent-atomic.xhtml)  **(\*\*\*)**

**2. Многопоточность в java**
+ [Java Memory Model](https://youtu.be/iB2N8aqwtxc?list=WL) **(\*\*\*\*)**
+ [Thread](https://habrahabr.ru/post/164487/) **(\*\*\*\*)**
+ [Синхронизация потоков. Оператор synchronized](https://metanit.com/java/tutorial/8.3.php) **(\*\*\*\*)**
+ [Синхронизированные коллекции](https://habrahabr.ru/company/luxoft/blog/157273/)  **(\*\*\*)**
+ [Volatile поля](http://tutorials.jenkov.com/java-concurrency/volatile.html)  **(\*\*\*)**
+ [Executors](http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) **(\*\*\*\*)**

**3. Фоновая работа в Android**
+ [Looper, Handler, and HandlerThread](https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a). [Видео](https://www.youtube.com/watch?v=gDvjU8HSuYE)  **(\*\*\*)**

+ [Loader (Deprecated)](https://habrahabr.ru/company/e-Legion/blog/265405/) **(\*\*)**
+ [AsyncTask (Deprecated)](https://developer.android.com/reference/android/os/AsyncTask.html)  **(\*\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – Background. Алексей Макаров](https://youtu.be/yyZh3ME7Jyk?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz) обозревает инструменты для взаимодействия с потоками в андроиде НО Нужно учитывать, что asyncTask и Loader помечены как deprecated **(\*\*)**

**4. Service**
+ [Service - основы](https://developer.android.com/guide/components/services.html)  **(\*\*\*\*)**
+ [IntentService](http://developer.alexanderklimov.ru/android/theory/intentservice.php)  **(\*\*\*\*)**
+ [Job Scheduler](http://ticketmastermobilestudio.com/blog/how-to-use-androids-job-scheduler) **(\*\*\*\*)**
+ [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) **(\*\*)**
+ [Background Execution Limits Android 8.0+](https://developer.android.com/about/versions/oreo/background.html) **(\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – Service & Broadcasts. Алексей Макаров](https://youtu.be/rxQYPxHkxi0?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz) Лектор рассказывает про сервисы, их сферу применения, какие флаги к ним применяются и как они работают достаточно подробно **(\*\*)**

**5. Codelabs**
+ [JobScheduler](https://codelabs.developers.google.com/codelabs/android-training-job-scheduler/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**
+ [WorkManager](https://codelabs.developers.google.com/codelabs/android-workmanager/index.html?index=..%2F..index#0) **(\*\*)**
+ [AsyncTask](https://codelabs.developers.google.com/codelabs/android-training-create-asynctask/index.html?index=..%2F..%2Fandroid-training#0) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `concurrency` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Создать task в PS с заголовком "IX. Многопоточность" и взять ее в работу.
2. В рамках предыдущего задания было реализовано чтение из файла. Реализовать чтение из файла и парсинг в background-потоке каждым из предложенных способов. С помощью AsyncTask, Executor, IntentService. При повороте экрана не должно происходить повторное чтение из файла.
3. Перед чтением данных остановить рабочий background-поток (Thread.sleep(5000)), для показательного отображения Progress Indicator (имитация запроса к сети).
4. Реализовать Progress Indicator на экранах "Категории помощи" и "Новости". Индикатор должен показываться с момента запроса данных до момента их отображения на экране. **Внимание!** Все действия c UI должны совершаться в главном потоке.
5. Для сохранения и восстановления данных при смене конфигурации использовать savedInstanceState.
6. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
7. Завершить task в PS и залогировать затраченное время



---
## X. Реактивное программирование
---
### Теоретическая часть

**1. Понятие реактивности**
+ [Концепция](https://habrahabr.ru/post/279715/)  **(\*\*\*\*)**
+ [RxMarbles](http://rxmarbles.com/)  **(\*\*\*)**

**2. RxJava**
+ [RxJava](https://github.com/ReactiveX/RxJava)  **(\*\*\*\*)**
+ [reactivex](http://reactivex.io/)  **(\*\*)**
+ [Введение в RxJava: Почему Rx?](https://habrahabr.ru/post/269417/)  **(\*\*)**
+ [Подробно о различиях между subscribeOn и observeOn](https://proandroiddev.com/understanding-rxjava-subscribeon-and-observeon-744b0c6a41ea)  **(\*\*)**

**3. Android расширения**
+ [RxAndroid (позволяет легко переключаться на main поток)](https://github.com/ReactiveX/RxAndroid)  **(\*\*\*)**
+ [RxBinding](https://github.com/JakeWharton/RxBinding)  **(\*\*\*)**
+ [Использование RxJava совместно с RxBinding](https://github.com/codepath/android_guides/wiki/RxJava-and-RxBinding)  **(\*\*\*)**


### Практическое задание
Работа должна производится в созданном ранее проекте.

**Важно! Для пункта 2 используется отдельный проект, указанный в задании**

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключиться на ветку `rx_java` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Создать task в PS с заголовком "X. Реактивное программирование" и взять ее в работу.
2. Клонировать [проект](http://gitlab.simbirsoft/artur.korchagin/rxtraining) и реализовать все методы так, чтобы тесты проходили.
 - Все методы должны быть реализованы в указанном репозитории в отдельной ветке, вида training/lastname_firstname_date. После выполнения должен быть создан merge request на ментора.
3. Подключить фреймворк `RxJava`, а так же библиотеки `RxAndroid` и `RxBinding`.
4. Создать экран "Авторизация" согласно [макету](https://zpl.io/25ge5Z3).
 - Кнопка "Войти" активна только в момент, когда в поля логин и пароль введено по 6 или более символов. В противном случае кнопка неактивна. Реализовать данное поведение с помощью `RxJava`.
 - В неактивном состоянии кнопка "Войти" имеет серый фон вместо зеленого.
 - При повороте экрана данные, введенные в поля ввода, должны сохраняться.
 - Экран "Авторизация" необходимо отображать после Splash Screen. По нажатию стрелки назад, приложение закрывается. Переход на экран "Категории помощи" происходит при нажатии на активную кнопку "Войти".
5. Доработать вкладку "По мероприятиям" экрана "Поиск"
 - Реализовать логику работы `SearchView`. Поиск происходит в тот момент, когда пользователь 500 мс ничего не вводил в строку и не удалял из нее. То есть, если пользователь быстро вводит слово в строку поиска, поиск отрабатывает только 1 раз для всего слова целиком. Реализовать данное поведение с помощью `RxJava`.
 - Данные для отображения результата необходимо брать из событий из созданных ранее json файлов.
 - Если в строку поиска ничего не ввели, то вместо результатов поиска отображаем заглушку как на [макете](https://zpl.io/2jlkOZp).
 - Если введенная в поиск строка не соответствует названию ни одного события, то отображаем пустой список результатов.
 - При повороте экрана, а также при перелистывании `ViewPager` на вкладку "По НКО" и обратно строка, введенная в поиске, список результатов и позиция списка должны сохраняться.
6. Реализовать счётчик непрочитанных новостей на пункте "Новости" в панели навигации (визуально можно сделать аналогично счетчику сообщений на иконке любого мессенджера).
 - В качестве шины данных необходимо использовать Subject.
 - Прочтение новости происходит при открытии экрана детального просмотра события.
 - После прочтения новости, а также при смене фильтра новостей счётчик должен сразу обновляться.
 - Обратите внимание, что данный функционал реализуем только локально. При выполнении следующих заданий, когда будет необходимо получать данные от сервера, информация о прочтении/не прочтении новости приходить не будет.
7. Обернуть получение данных из json файлов по предыдущему [заданию](http://gitlab.simbirsoft/internships/mobile/android_trainee_education/-/blob/master/Программа-обучения-android-разработчика/VI.-Структуры-данных.-Работа-с-файлами.md#vi-структуры-данных-работа-с-файлами) в Observable
 - Добавить операторы `subscribeOn` и `observeOn` с разными планировщиками (mainThread, io, computation, newThread).
 - Добавить выведение в лог текущего потока после использования данных операторов с разными планировщиками для наглядности их работы (используя doOnNext). Логирование можно также сделать при создании Observable (к примеру, в unsafeCreate). Обратить внимание на полученные логи.
 - Добавить оператор объединения двух Observable (например, Zip, CombineLatest). Второй Observable можно создать с рандомными данными. Использовать subscribeOn и observeOn сначала на каждом отдельном Observable, затем на Observable, полученном после объединения. Вывести в лог текущий поток (для каждого из Observable). Обратить внимание на полученные логи.
 - Добавить два observeOn в цепочке с разными планировщиками. Также добавить два subscribeOn. Вывести в лог текущий поток. Обратить внимание на полученные логи.
 - Выбрать наиболее подходящую под [задание](http://gitlab.simbirsoft/internships/mobile/android_trainee_education/-/blob/master/Программа-обучения-android-разработчика/VI.-Структуры-данных.-Работа-с-файлами.md#vi-структуры-данных-работа-с-файлами) комбинацию subscribeOn и observeOn
8. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
9. Завершить task в PS и залогировать затраченное время



---
## XI. Работа с сетью
---
### Теоретическая часть
**1. Базовые понятия**
+ [HTTP](https://ru.wikipedia.org/wiki/HTTP) **(\*\*\*)**
+ [HTTP codes](https://ru.wikipedia.org/wiki/Список_кодов_состояния_HTTP) **(\*\*\*)**
+ [RESTful](https://habrahabr.ru/company/hexlet/blog/274675/) **(\*\*\*)**
+ [RESTful API — ложь](https://habrahabr.ru/post/265845/) **(\*)**
+ [WebSocket](https://stfalcon.com/ru/blog/post/android-websocket) **(\*\*)**

**2. Библиотеки**
+ [OkHttp](http://square.github.io/okhttp/) **(\*\*\*\*)**
+ [Retrofit](http://square.github.io/retrofit/) **(\*\*\*\*)**
+ [Handling API calls using Retrofit 2 and RxJava 2](https://medium.com/3xplore/handling-api-calls-using-retrofit-2-and-rxjava-2-1871c891b6ae) **(\*\*\*\*)**
+ [OkHttp. Interceptors](https://github.com/square/okhttp/wiki/Interceptors) **(\*\*\*)**
+ [OkHttp which ignores all SSL errors](https://gist.github.com/chalup/8706740) **(\*\*\*)**
+ [Logging interceptor for okhttp](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) **(\*\*)**
+ [Glide](https://blog.mindorks.com/downloading-and-showing-image-with-glide-library-in-android), подробнее о [кешировании изображений](https://futurestud.io/tutorials/glide-caching-basics) **(\*\*\*\*)**
+ [Picasso](https://code.tutsplus.com/tutorials/code-an-image-gallery-android-app-with-picasso--cms-30966), подробнее о [кешировании изображений](https://futurestud.io/tutorials/picasso-influencing-image-caching) **(\*\*\*\*)**
+ [Coil](https://medium.com/better-programming/how-to-use-coil-kotlins-native-image-loader-d6715dda7d26), подробнее о [кешировании изображений](https://coil-kt.github.io/coil/getting_started/#preloading) **(\*\*\*\*)**
+ [Сравнение Glide, Picasso и Coil](https://proandroiddev.com/coil-vs-picasso-vs-glide-get-ready-go-774add8cfd40) **(\*\*)**

**3. Отладка. Перехват и подмена трафика мобильных устройств**
+ [Postman](https://habr.com/ru/company/kolesa/blog/351250/)  **(\*\*)**
+ [Fiddler](https://learn.javascript.ru/fiddler)  **(\*\*)**
+ [Charles](http://wormiks.ru/faq_po_programmam_wormix/11-charles_instrukcija_polzovatelja_i_faq.html)  **(\*\*)**
+ [Перехват и подмена трафика мобильных устройств](https://kb.simbirsoft.com/article/traffic-sniffers/)  **(\*)**

**4. Codelabs**
+ [Android Network Security Configuration](https://codelabs.developers.google.com/codelabs/android-network-security-config/index.html?index=..%2F..index#0) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `networking
` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Создать task в PS с заголовком "XI. Работа с сетью" и взять ее в работу.
2. Необходимо реализовать транспортный слой приложения, который будет осуществалять загрузку данных с [сервера](https://mobile-study.simbirsoft1.com). Запросы, реализуемые сервером, и формат ответов можно посмотреть открыв ссылку в браузере.
**Важно** Сервер доступен только из сети SimbirSoft.
 - Данные от сервера получать в виде Observable
 - Все "тяжелые" операции должны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
3. Заменить загрузку из файла на старте приложения на загрузку с сервера. В случае если ответ от сервера ошибочен - загрузить данные из файла.
 - Все "тяжелые" операции должны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
4. Реализовать загрузку изображений для блока "Ваши друзья" на экране профиля, используя библиотеку Glide, Picasso или Coil
 - Во время загрузки изображения, а также при ошибке загрузки, необходимо реализовать отображение плейсхолдера
 - Отключить кэширование изображений в памяти устройства (Disk Cache)
5. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
6. Завершить task в PS и залогировать затраченное время



---
## XII. Базы данных. Content Provider
---
### Теоретическая часть

**1. Android SQLite**
+ [Работа с SQLite средствами Android SDK](https://developer.android.com/training/data-storage/sqlite.html)  **(\*\*)**
+ [ORM](https://habrahabr.ru/company/yotadevices/blog/242559/)  **(\*\*)**

**2. Основы Realm**
+ [Документация по Realm](https://realm.io/docs/java/latest/)  **(\*\*\*\*)**
+ [DatabaseProvider в androidbase](http://gitlab.simbirsoft/mobile/AndroidBase/blob/master/app/src/main/java/com/simbirsoft/baseplatform/data/db/DatabaseProvider.java)  **(\*\*)**

**3. Основы Room**
+ [Документация по Room](https://developer.android.com/topic/libraries/architecture/room)  **(\*\*\*\*)**
+ [Room и Rx](https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757)  **(\*\*\*\*)**

**4. Content Provider**
+ [Основы](https://developer.android.com/guide/topics/providers/content-provider-basics.html?hl=ru) **(\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – Content provider. Максим Хромцов](https://youtu.be/zeDzbzLmpLs?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz) **(\*\*)**

**5. Codelabs**
+ [Codelab по Room Java](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..index#0) **(\*\*\*)**
+ [Codelab по Room Kotlin](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0)  **(\*\*\*)**
+ [Paging](https://codelabs.developers.google.com/codelabs/android-paging/#0)  **(\*\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_base` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Создать task в PS с заголовком "XII. Базы данных" и взять ее в работу.
2. Необходимо создать базу данных с использованием Room для хранения сущностей Категории и Благотворительных событий.
3. При первом получении данных для сущностей Категории и Благотворительные события, сохранять данные в базе данных, и дальнейшее получение производить из нее. Обновление данных должно происходить только для новой сессии.
- Чтение и запись должны быть реализованы в фоновом потоке
- На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
4. Наполнение экранов Категории и Благотворительных событий должно происходить из созданной базы данных
- Все "тяжелые" операции должны быть реализованы в фоновом потоке
- На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
5. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
6. Завершить task в PS и залогировать затраченное время



---
## XIII. Архитектура приложений. Практика в рамках продуктового проекта.
---
### Теоретическая часть

**1. Архитектура**
+ [Принципы SOLID](https://habr.com/ru/post/348286/)  **(\*\*\*\*)**
+ [MVC, MVP, MVVM](https://habrahabr.ru/company/mobileup/blog/313538/) **(\*\*)**
+ [MVI](https://habr.com/ru/company/badoo/blog/429728/) **(\*\*)**
+ [Redux (eng)](https://android.jlelse.eu/redux-rxkotlin-rxswift-awesome-native-mobile-apps-introduction-part-1-4ea7fa3be319) **(\*\*)**
+ [RIBs 1](https://habr.com/ru/company/sports_ru/blog/424305/) **(\*)**
+ [RIBs 2](https://apptractor.ru/info/articles/mobilnaya-razrabotka-v-bilayn-arhitektura-instrumentyi-i-tseli.html) **(\*)**
+ [Про создание архитектур](https://habr.com/ru/post/276593/) **(\*\*\*)**
+ [Общие принципы архитектур](https://habr.com/ru/post/456256/) **(\*\*)**
+ [Moxy - ссылка на библиотеку](https://github.com/Arello-Mobile/Moxy ) **(\*\*\*\*)**
+ [Moxy — статья о том, как она работает](https://habrahabr.ru/post/276189/) **(\*\*\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – MVP&Co. Дмитрий Попов](https://youtu.be/jOCjKyV9a5U?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz)  рассказывает про архитектуру и наглядно показыватет как архитектура влияет на приложение в разработке. рекомендуется просмотр до момента, когда лектор начинает рассказывать про тестирование **(\*\*)**

+ [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) **(\*\*)**

**2. Clean Architecture**
+ [Статья самого Дядюшки Боба](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) **(\*\*\*\*)**
+ [Хороший цикл статей](https://five.agency/android-architecture-part-1-every-new-beginning-is-hard/) **(\*\*\*\*)**
+ [Видео-доклад "Чистая архитектура. Погружение"](https://www.youtube.com/watch?v=JePQFYb5WJI&feature=youtu.be) **(\*\*\*\*)**
+ [Пример использования архитектуры](https://github.com/android10/Android-CleanArchitecture) **(\*\*\*)**
+ [Заблуждения Clean Architecture](https://habr.com/ru/company/mobileup/blog/335382/) **(\*\*\*)**
+ [Руководство по применению чистой архитектуры в Android проектах](https://github.com/AndroidArchitecture/AndroidArchitectureBook) **(\*\*)**

**3. DI**
+ [Dagger 2 - ссылка на библиотеку](https://github.com/google/dagger)  **(\*\*\*\*)**
+ [Dagger 2 - хороший цикл статей](https://habr.com/ru/post/343248/)  **(\*\*\*\*)**
+ [Dagger 2 - еще один хороший цикл статей](https://habr.com/post/279125/)  **(\*\*\*\*)**
+ [Toothpick](https://github.com/stephanenicolas/toothpick) **(\*\*)**
+ [Видео-лекция Яндекса: Школа мобильной разработки – MyFirstApp (Часть 2). Роман Григорьев](https://youtu.be/gRuTqyCPjv4?list=PLQC2_0cDcSKBNCR8UWeElzCUuFkXASduz)  Описывает флаги запуска активити, говорит о проблеме пересоздания активити в ос Андроид и подходы к созданияю приложения (на активити, на фрагметах, на вьюхах), поднимает проблему пересоздания активити при повороте экрана **(\*\*)**

**4. Многомодульность**
+ [Многомодульность](https://habr.com/ru/company/kaspersky/blog/422555/) **(\*\*)**

**5. Утечки памяти**
+ [Обзоп системы управления памятью в Android](https://developer.android.com/topic/performance/memory-overview) **(\*\*)**
+ [Описание принципов работы с памятью. Типичные утечки памяти](https://proandroiddev.com/everything-you-need-to-know-about-memory-leaks-in-android-d7a59faaf46a) **(\*\*)**
+ [Доклад про GC, Профилирование, Поиск и устранение утечек](https://youtu.be/jJDZdg0dP2Y) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `architecture` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.


1. Перпеписать свое приложение, применяя архитектурный подход Clean Architecture. В реализации presentation слоя применить архитектурный паттерн MVP с использованием библиотеки Moxy. Для предоставления зависимостей в соотвествии с техникой DI использовать библиотеку Dagger
2. Запустить статические анализаторы кода (lint, ktlint, SonarLint) и исправить найденные замечания
3. После завершения работ над заданием перевести задачу в Jira в статус "Code Review", уведомить своего ментора и залогировать затраченное время.
