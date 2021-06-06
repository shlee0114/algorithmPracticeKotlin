package DataStructure

import DataStructure.Node.CustomNode
import FunctionModules.DataStructure.DataStructureType

class CustomIterator(private val start : CustomNode?, private val end : CustomNode?, dataStructureType : DataStructureType) : Iterator<Any?>{
    private var initVal : CustomNode? = null
    private val dataStructureType = dataStructureType
    override fun hasNext(): Boolean {
        if(start == null){
            return false
        }
        return initVal != end
    }

    override fun next(): Any? {
        initVal = if(initVal == null){
            start
        }else{
            if(dataStructureType == DataStructureType.LinkedList){
                initVal?.nextNode
            }else{
                initVal?.prevNode
            }
        }
        return initVal?.nodeValue
    }
}