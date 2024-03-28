import kotlin.system.exitProcess

object MenuNavigation {
    private var screenType : ScreenType = ScreenType.ARCHIVES
    private var currentArchive : Archive = Archive("")

    fun printMenu(){
        println(screenType.startMenu)
        when (screenType){
            ScreenType.ARCHIVES -> MenuData.getData().forEachIndexed { index, element -> println("${index + 1}. $element") }
            ScreenType.NOTES -> MenuData.getData(currentArchive)?.forEachIndexed { index, note -> println("${index + 1}. $note")  }
        }
        println("${getSize() + 1}. ${screenType.endMenu}")

    }
    fun selectMenu(input:Int?){
        if (input != null){
            when(input) {
                0 -> create()
                getSize()+1 -> back()
                in 1 .. getSize() -> select(input-1)
                else -> {
                    println("Ошибка: Выбранный элемент отсутствует\n")
                }
            }
        }
    }
    private fun select(index: Int){
        when(screenType) {
            ScreenType.ARCHIVES -> {
                currentArchive = MenuData.getData()[index]
                screenType = ScreenType.NOTES
            }
            ScreenType.NOTES -> MenuData.getData(currentArchive)?.get(index)?.showNote()
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
        val str = inputString()
        if (str != null) {
            MenuData.addData(str)
        }
    }
    private fun createNote(){
        println("Введите название новой заметки:")
        val name = inputString() ?: return
        println("Введите текст заметки")
        val text = inputString() ?: return
        MenuData.addData(currentArchive, name, text)
    }
    private fun getSize():Int {
        return when(screenType) {
            ScreenType.ARCHIVES -> MenuData.getData().size
            ScreenType.NOTES -> MenuData.getData(currentArchive)?.size ?: 0
        }
    }
}