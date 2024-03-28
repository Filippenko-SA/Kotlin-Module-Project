


object MenuData {

    private val notes : MutableMap<Archive,ArrayList<Note>> = mutableMapOf()

    fun getData(): ArrayList<Archive>{
        val result: ArrayList<Archive> = ArrayList()
        notes.forEach { (archive, _) -> result.add(archive) }
        return result
    }

    fun getData(archive: Archive): ArrayList<Note>?{
        return notes[archive]
    }
    fun addData(name:String){
        val archive = Archive(name)
        if(!notes.containsKey(archive)){
            notes[archive] = ArrayList()
        }
    }

    fun addData(archive: Archive, name:String, text:String){
        notes[archive]?.add(Note(archive, name, text))
    }



}