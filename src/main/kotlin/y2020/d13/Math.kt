package y2020.d13

import java.math.BigInteger

class Math {
    companion object {
        fun lcm(vararg numbers: BigInteger): BigInteger = numbers.reduce { acc, number -> lcm(acc, number) }
        fun lcm(first: BigInteger, second: BigInteger): BigInteger = first * (second / first.gcd(second))
    }
}
