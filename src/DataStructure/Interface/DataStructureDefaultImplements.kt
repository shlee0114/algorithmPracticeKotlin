package DataStructure.Interface

interface DataStructureDefaultImplements<T> {
    fun pop() : T
    fun push(item : T)
    fun peek() : T

    fun count() : Int

    //비었는 지 안 비었는 지 체크
    fun isEmpty() : Boolean
    fun isNotEmpty() : Boolean


}