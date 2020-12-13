@file:JvmName("Main")
package d06

fun main(args: Array<String>) {
    print("started")
    val reader = Reader("src/main/resources/day-06-b")
    var sum = 0

    while (reader.hasNext()) {
        sum += groupCounterB(reader.getNextGroup())
    }

    print("Total sum: $sum")
}

fun groupCounterB(group: List<String>): Int {
    val letters = HashMap<Char,Int>()
    val size = group.size

    group.forEach { questionnaire ->
        questionnaire.toCharArray()
            .forEach { answer ->
                letters.compute(answer, { _,i -> i?.plus(1) ?: 1})
            }
    }

    return letters.filterValues { count -> count == size }
        .count()
}

fun groupCounterA(group: List<String>): Int {
    val letters = HashSet<Char>()

    group.forEach { questionnaire ->
        questionnaire.toCharArray()
            .forEach { answer ->
                letters.add(answer)
            }
    }

    return letters.size
}
