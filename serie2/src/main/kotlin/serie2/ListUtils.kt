package serie2


    fun <E> removeAfterIntersectionPoint(list1: Node<E>, list2: Node<E>, cmp: Comparator<E>) {
        var curr1 = list1.previous
        var curr2 = list2.previous

        while (curr1 != list1 && curr2 != list2) {
            if (cmp.compare(curr1.value, curr2.value) == 0) {
                curr1.previous.next = curr1.next
                curr1.next.previous = curr1.previous
                curr1 = curr1.previous
                curr2 = curr2.previous
            }
            else break
        }
    }
fun <E> xor(list1: Node<E>, list2: Node<E>, cmp: Comparator<E>): Node<E> {
    var resultList = Node<E>()
    var currentNode = resultList

    var node1 = list1.next
    var node2 = list2.next

    while (node1 != list1 && node2 != list2) {
        val compare = cmp.compare(node1.value, node2.value)
        when {
            compare < 0 -> {
                node1 = updateResult(node1, resultList, currentNode)
                currentNode = currentNode.next
            }
            compare > 0 -> {
                node2 = updateResult(node2, resultList, currentNode)
                currentNode = currentNode.next
            }
            else -> {
                node1 = node1.next
                node2 = node2.next
            }
        }
    }

    while (node1 != list1) {
        node1 = updateResult(node1, resultList, currentNode)
        currentNode = currentNode.next
    }
    while (node2 != list2) {
        node2 = updateResult(node2, resultList, currentNode)
        currentNode = currentNode.next
    }

    resultList.previous = currentNode.next
    currentNode.next.previous = resultList

    return resultList
}
fun <E> updateResult(node: Node<E>, resultList: Node<E>, currentNode: Node<E>) : Node<E> {
    val nextNode = node.next
    node.previous.next = node.next
    node.next.previous = node.previous
    currentNode.next = node
    node.previous = currentNode
    node.next = resultList
    resultList.previous = node
    return nextNode
}

class Node<E> {
    val value: E
    var previous: Node<E>
    var next: Node<E>

    constructor() {
        value = Any() as E
        previous = this
        next = this
    }

    constructor(data: E, p: Node<E>, n: Node<E>) {
        value = data
        previous = p
        next = n
    }
}


fun <E> printlist(list1: Node<E>) {
    var curr = list1.next
    var list = mutableListOf<E>()
    while (curr!= list1) {
        list += curr.value
        curr = curr.next
    }
    println(list)
}





