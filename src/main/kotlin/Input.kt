import java.util.Scanner

fun inputNumber() : Int? {
    val input : Int? = Scanner(System.`in`).nextLine().trim().toIntOrNull()
    if (input == null) {
        println("Ошибка: Введенное значение не является числом\n")
    }
    return input
}

fun inputString() : String? {
    val str = Scanner(System.`in`).nextLine()
    if(str.isBlank()) {
        println("Ошибка: Введенна пустая строка\n")
        return null
    }
    return str
}