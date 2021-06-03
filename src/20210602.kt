package FunctionModules

import DataStructure.DataStructureDivision
import DataStructure.LinkedList.LinkedList

fun main(){
    val test = LinkedList()

    test.push(10)
    test.push(12)
    test.push(15)
    test.push(17)
    test.push(22)
    test.push(5)
    test.push(25)
    test.push(28)
    test.push(12)

    for(i in test){
        println(i.nodeValue)
    }

    println()
    println(test.pop())
    println(test.pop())
    println(test.pop())
    println(test.pop())
    println(test.pop())
    println(test.pop())
    println()
    test[0] = 100
    test[2] = 411
    test[3] = 411
    test[4] = 411
    println(test[0])
    println(test[1])
    println(test[2])
    println(test[3])
    println(test[4])

}