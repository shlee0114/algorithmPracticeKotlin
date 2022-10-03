package `2022`.`09`

class Algorithm4 {
    fun solution(denum1: Int, num1: Int, denum2: Int, num2: Int): IntArray {
        val lcm = lcm(num1, num2)

        val denum3 = denum1 * (lcm / num1) + denum2 * (lcm / num2)
        val gcd = gcd(denum3, lcm)

        return intArrayOf(denum3 / gcd, lcm / gcd)
    }

    fun lcm(n: Int, m: Int) = n * m / gcd(n, m)

    fun gcd(n: Int, m: Int): Int {
        return if (n < m) {
            if (n == 0) m else gcd(n, m % n)
        } else {
            if (m == 0) n else gcd(m, n % m)
        }
    }
}

fun main() {
    Algorithm4()
        .solution(9, 2, 1, 3)
}
