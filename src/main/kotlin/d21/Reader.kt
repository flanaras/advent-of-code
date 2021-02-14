package d21

import d07.base
import java.io.File

class Reader {

    fun readMenu(filename: String = "day-21-test"): List<Dish> {
        return File(base + filename).readLines()
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
