package FunctionModules

import DataStructure.DataStructureDivision
import DataStructure.LinkedList.LinkedList

fun main(){
    val stackTest = LinkedList(DataStructureDivision.Stack)
    val queueTest = LinkedList(DataStructureDivision.Queue)

    stackTest.push(10)
    stackTest.push(12)
    stackTest.push(15)
    stackTest.push(17)
    stackTest.push(22)
    queueTest.push(5)
    queueTest.push(25)
    queueTest.push(28)
    queueTest.push(12)

    println(stackTest.pop())
    println(stackTest.pop())
    println(stackTest.pop())
    println(stackTest.pop())
    println(stackTest.pop())
    println(queueTest.pop())
    println(queueTest.pop())
    println(queueTest.pop())
    println(queueTest.pop())

}