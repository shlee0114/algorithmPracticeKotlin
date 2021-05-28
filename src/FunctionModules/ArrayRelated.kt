package FunctionModules

//MutableList 2중으로 되어있어서 setVal.toMutableList()를 했을 시
//내부에 있는 값들은 주소 값으로 가기에 다른 부분에서 수정해도 동일하게 수정되는 현상을 막고자
//반복문 사용해서 내부에 있는 객체를 깊은 복사하는 함수
fun deepCopyMutableMutable(setVal : MutableList<MutableList<Int>>) : MutableList<MutableList<Int>>{
    val getVal = mutableListOf<MutableList<Int>>()
    for(i in setVal){
        getVal.add(i.toMutableList())
    }
    return getVal
}

//Array 2중으로 되어있어서 setVal.clone()을 했을 시
//내부에 있는 값들은 주소 값으로 가기에 다른 부분에서 수정해도 동일하게 수정되는 현상을 막고자
//반복문 사용해서 내부에 있는 객체를 깊은 복사하는 함수
fun deepCopyArrayArray(setVal : Array<Array<Int>?>, arraySize : Int) : Array<Array<Int>?>{
    val getVal = arrayOfNulls<Array<Int>>(arraySize)
    for(j in setVal.indices){
        getVal[j] = setVal[j]!!.clone()
    }
    return getVal
}
