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
}

