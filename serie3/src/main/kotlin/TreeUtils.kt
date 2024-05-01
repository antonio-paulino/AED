
import kotlin.math.abs


data class Node<E>(var item: E, var left:Node<E>?, var right:Node<E>?)

fun <E> contains(root: Node<E>?, min:E, max:E, cmp:(e1:E, e2:E)->Int):Boolean =
    if (root == null) false
    else if (cmp(root.item, min) >= 0 && cmp(root.item, max) <= 0) true
    else if (cmp(root.item, min) >= 0 ) contains(root.left, min,max, cmp)
    else contains(root.right, min, max, cmp)

fun <E> isBalanced(root: Node<E>?): Boolean = height(root) != -1

fun <E> height(root : Node<E>?): Int {
    if (root == null) return 0

    val lH = height(root.left)
    if (lH == -1) return -1

    val rH = height(root.right)
    if (rH == -1) return -1

    return if (abs2(lH - rH) > 1) -1
    else if (lH > rH) lH + 1
    else rH + 1
 }
fun abs2(num : Int) =
    if (num < 0) -num
    else num

fun createBSTFromRange(start: Int, end: Int): Node<Int>? {
    if (start > end) {
        return null
    }

    val mid = (start + end) / 2

    val root = Node(mid, null, null)
    root.left = createBSTFromRange(start, mid - 1)
    root.right = createBSTFromRange(mid + 1, end)

    return root
}

