package y2020.d06

import java.io.File

class Reader(filename: String) {

    private var group = ArrayList<String>()
    private var index = (-1)
    private var allGroups: List<String>
    private lateinit var line: String

    init {
        allGroups = ArrayList<String>(File(filename).readLines())
            .also{
                it.add("")
            }
    }

    fun getNextGroup(): List<String> {
        group.clear()
        index++
        line = allGroups[index]
        while (index < allGroups.size && line.isNotBlank()) {
            group.add(line)
            index++
            line = allGroups[index]
        }
        return group
    }

    fun hasNext(): Boolean =
        index < allGroups.size-1
}