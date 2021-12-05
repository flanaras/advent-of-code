package y2021.d01

import java.io.File

const val base_2021 = "src/main/resources/2021/"

class Reader {

    fun readDepths(filename: String = "day-01-test-a"): List<Int> {
        return File(base_2021 + filename).readLines()
            .map(String::toInt)
    }
}

