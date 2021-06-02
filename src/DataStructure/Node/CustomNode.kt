package DataStructure.Node

import DataStructure.Interface.NodeDefaultImplements

//노드
class CustomNode(
    override var nodeValue: Any?,
    override var nextNode: CustomNode?,
    override var prevNode: CustomNode?
) : NodeDefaultImplements<CustomNode?, Any?>