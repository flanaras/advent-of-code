package y2020.d21

class AllergenFinder(
    private val dishes: List<Dish>
) {
    private val ingredientToAllergen = HashMap<String, String>()

    init {
        var remainingAllergenToPotentialIngredients = initialAllergenToIngredients()

        while (remainingAllergenToPotentialIngredients.isNotEmpty()) {
            remainingAllergenToPotentialIngredients = solvingIteration(remainingAllergenToPotentialIngredients)
        }
    }

    private fun initialAllergenToIngredients(): List<Pair<String, List<String>>> =
        dishesAllergenInIngredients().map { (allergen, ingredients) ->
            allergen to intersectionOf(ingredients)
        }

    private fun dishesAllergenInIngredients() = HashMap<String, MutableList<List<String>>>().also { dishesAllergenInIngredients ->
        dishes.forEach { dish ->
            dish.allergens.forEach { allergens ->
                dishesAllergenInIngredients.getOrPut(allergens) { mutableListOf() }
                    .add(dish.ingredients)
            }
        }
    }

    private fun intersectionOf(ingredients: MutableList<List<String>>) =
        ingredients.reduce { acc, list -> acc.intersect(list).toList() }

    private fun solvingIteration(allergenToPotentialIngredients: List<Pair<String, List<String>>>) =
        allergenToPotentialIngredients.map { (allergen, ingredients) ->
            allergen to ingredients.filter { ingredient ->
                !ingredientToAllergen.containsKey(ingredient)
            }.also { remainingIngredients ->
                if (remainingIngredients.size == 1) {
                    ingredientToAllergen[remainingIngredients.first()] = allergen
                }
            }
        }.filter { it.second.size > 1 }

    fun countNonAllergenOccurrences(): Int =
        dishes.map { dish ->
            dish.ingredients.map { !ingredientToAllergen.containsKey(it) }
                .count { it }
        }.reduce { acc, i -> acc + i }

    fun computeCanonicalDangerousIngredientList(): String {
        return ingredientToAllergen.toList()
            .sortedBy { it.second }
            .map { it.first }
            .reduce { acc, s -> "$acc,$s" }
    }
}
