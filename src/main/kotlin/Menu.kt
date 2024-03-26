
import java.util.Scanner
import kotlin.system.exitProcess

object Menu {
    private var archives : MutableList<Archive> = mutableListOf()
    private var notes : MutableMap<Archive,MutableList<Note>> = mutableMapOf()
    private var screenType : ScreenType = ScreenType.ARCHIVES
    private var currentArchive : Archive = Archive("")

    fun printMenu(){
        println(screenType.startMenu)
        when (screenType){
            ScreenType.ARCHIVES -> archives.forEachIndexed {index, element -> println("${index + 1}. $element") }
            ScreenType.NOTES -> notes[currentArchive]?.forEachIndexed { index, note -> println("${index + 1}. $note")  }
        }
        println("${getSize() + 1}. ${screenType.endMenu}")

    }
    fun selectMenu(){
        val inputString = Scanner(System.`in`).nextLine()
        val input : Int
        try {
            input = inputString.toInt()
        } catch (e:NumberFormatException) {
            println("Ошибка: Введенное значение не является числом\n")
            return
        }

        when(input) {
            0 -> create()
            getSize()+1 -> back()
            in 1 .. getSize() -> select(input-1)
            else -> {
                println("Ошибка: Выбранный элемент отсутствует\n")
                return
            }
        }
    }
    private fun select(index: Int){
        when(screenType) {
            ScreenType.ARCHIVES -> {
                currentArchive = archives[index]
                screenType = ScreenType.NOTES
            }
            ScreenType.NOTES -> notes[currentArchive]?.get(index)?.showNote()
        }
    }
    private fun back(){
        when(screenType) {
            ScreenType.ARCHIVES -> exitProcess(0)
            ScreenType.NOTES -> screenType = ScreenType.ARCHIVES
        }
    }
    private fun create(){
        when(screenType) {
            ScreenType.ARCHIVES -> createArchive()
            ScreenType.NOTES -> createNote()
        }
    }
    private fun createArchive(){
        println("Введите название нового архива:")
        val str = Scanner(System.`in`).nextLine()
        if(str == "") {
            println("Ощибка: Название не должно быть пустым\n")
        } else {
            val archive = Archive(str)
            archives.add(archive)
            notes[archive] = mutableListOf()
        }
    }
    private fun createNote(){
        println("Введите название новой заметки:")
        val name = Scanner(System.`in`).nextLine()
        if(name == "") {
            println("Ощибка: Название не должно быть пустым\n")
            return
        }
        println("Введите текст заметки")
        val text = Scanner(System.`in`).nextLine()
        if(text == "") {
            println("Ощибка: Текст не должно быть пустым\n")
            return
        }
        notes[currentArchive]?.add(Note(currentArchive, name, text))
    }
    private fun getSize():Int {
        when(screenType) {
            ScreenType.ARCHIVES -> return archives.size
            ScreenType.NOTES -> {
                val result = notes[currentArchive]?.size
                if(result == null){
                    return 0
                } else {
                    return result
                }
            }
        }
    }

}