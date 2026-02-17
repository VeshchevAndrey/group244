/* Функции в Kotlin */

// fun - оператор объявления (создания) функции

// Пример объявления функции
fun welcomeUser() {
    print("Введите имя: ")
    val name = readln()
    println("Добро пожаловать в систему, $name!")
}

// Пример объявления функции с параметрами
fun newStudent(name: String, surname: String, age: Byte) {
    println("Прибыл новый студент: фамилия: $name, имя: $surname, возраст: $age")
}

// Функция с обязательными параметрами
fun user(name: String, login: String){
    println("Информация о пользователе: Имя пользователя - $name, логин - $login")
}

// Функция с необязательными параметрами
fun newUser(login: String, password: String, name: String, image: String = "No Photo"){
    println("Account created! Your name is $name.")
    println("Login: $login, Password: $password")
    if (image != "No Photo"){
        println("Avatar is loaded!")
    }
}

// return - оператор возврата значений из функции
// Пример вызова функции с возвратом значений
fun square(number: Int): Int{
    return number * number
}

fun power(num: Int, pow: Int): Int{
    var result = 1
    for (i in 1..pow){
        result *= num
    }
    return result
}

// vararg - оператор параметра функции с переменным количеством принимаемых элементов
// Пример функции с переменным количеством значений параметра
fun guests(vararg names: String){
    println("Список гостей: ")
    for (i in names) println(i)
}

// Функция расчёта среднего арифметического значения
fun mean(vararg numbers: Int): Int {
    var sum = 0
    for (i in numbers) sum += i
    return sum / numbers.size
}

// Функция с разными типами параметров
fun group(name: String, course: Int, vararg students: String){
    println("Группа $name, курс $course.")
    for (i in students) println(i)
}

fun country(name: String, vararg bigCities: String, capital: String){
    println("Название страны: $name. Столица: $capital.\nКрупные города:")
    for (i in bigCities) println(i)
}

fun sum(vararg numbers: Int): Int{
    var result = 0
    for (i in numbers) result += i
    return result
}

// Однострочные фукнции
// Пример однострочной функции
fun cubed(number: Int) = number * number * number


// Анонимные функции
val greetings = fun () = println("Hello!")

val sum = fun(x: Int, y: Int) = x + y

// Лямбда-выражение

val hello = { println("Hello") }

// main() - точка запуска программы
fun main(){
    welcomeUser() // Вызов функции
    newStudent("Андрей", "Вещев", 27) // Вызов функции с параметрами
    newStudent(age = 27, name = "Андрей", surname = "Вещев") // Вызов функции с параметрами через указания их имён
    user("Andrey", "Admin") // Вызов функции с обязательными параметрами
    newUser("admin", "password", "John Smith") // Вызов функции с необязательным параметром без его указания
    newUser("user", "1234", "Jane Doe", "//flower.jpg") // Вызов функции с необязательным параметром c его указанием
    val sqr = square(625)
    println(sqr)
    val p = power(10, 8)
    println(p)

    guests("Андрей Вещев", "Иван Иванов", "Райан Гослинг", "Барак Обама", "Дональд Трамп")

    val arithmeticMean = mean(67, 23, 53, 545, 688, 34)
    println(arithmeticMean)

    group("24/4", 2, "Иван Иванов", "Алексей Алексеев", "Сергей Сергеев")

    country("Россия", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань", capital = "Москва")

    // * перед параметром функции - оператор распаковки массива
    val arrayNumbers = intArrayOf(1, 2, 3, 3, 2, 1, 5, 6, 7, 7, 6, 5)
    println(sum(*arrayNumbers))

    val cbd = cubed(4)
    println(cbd)
    greetings()
    println( sum(9, 8))

    hello()
}



