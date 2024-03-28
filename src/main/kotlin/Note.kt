import java.util.Scanner

data class Note(val archive: Archive, val name: String, val text: String){
    override fun toString(): String {
        return name
    }
    fun showNote(){
        println("----------------------------------------")
        println("Название заметки: $name")
        println("----------------------------------------")
        println(text)
        println("----------------------------------------")
        println("Введите любой символ для возврата в предыдущее меню")
        Scanner(System.`in`).nextLine()
    }
}
