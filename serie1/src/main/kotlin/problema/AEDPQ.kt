package problema

data class Pair2(val first : String?, val second : Int)

data class AEDPriorityQueue(
    val heap: Array<Pair2?>,
    var size: Int,
    val cmp:(Pair2?, Pair2?) -> Int
){

    fun isEmpty() = size == 0

    fun peek() = if(isEmpty()) null else heap[0]

    fun poll() : Pair2? {
        if(isEmpty()) return null
        val element = heap[0]
        size--
        heap[0] = heap[size]
        heap[size] = null
        minHeapify(0)
        return element
    }

    private fun minHeapify(i : Int) {
        var smallest = i
        val l = left(i)
        val r = right(i)
        if(l < size && cmp(heap[l], heap[smallest]) < 0) smallest = l
        if(r < size && cmp(heap[r], heap[smallest]) < 0) smallest = r
        if(smallest != i) {
            exchange(i, smallest)
            minHeapify(smallest)
        }
    }

    private fun parent(i : Int) = (i - 1)/2

    private fun left(i : Int) = 2 * i + 1

    private fun right(i : Int) = 2 * i + 2

    private fun exchange(i : Int, j : Int) {
        var temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }

    fun offer(element : Pair2) : Boolean{
        if (size == heap.size) return false
        heap[size] = element
        decreaseKey(size)
        size++
        return true
    }

    private fun decreaseKey(i : Int) {
        var i = i
        while (i > 0 && cmp(heap[i], heap[parent(i)]) < 0) {
            exchange(i, parent(i))
            i = parent(i)
        }
    }
}

