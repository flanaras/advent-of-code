package y2020.d21

import y2020.d07.base_2020
import java.io.File

class Reader {

    fun readMenu(filename: String = "day-21-test"): List<Dish> {
        return File(base_2020 + filename).readLines()
            .map { line -> toDish(line) }
    }

    private fun toDish(line: String): Dish {
        val ingredients = line.split(" (")[0].split(" ")
        val allergens = line.split(" (contains ")[1]
            .replace(")", "")
            .split(", ")

        return Dish(ingredients, allergens)
    }
}

data class Dish(
    val ingredients: List<String>,
    val allergens: List<String>
)
