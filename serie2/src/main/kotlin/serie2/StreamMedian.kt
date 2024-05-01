package serie2

import java.util.*
import kotlin.Comparator


class StreamMedian {
    private val maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())
    private val minHeap = PriorityQueue<Int>()
    fun getMedian(): Int {
        val size = maxHeap.size + minHeap.size
        return if (size % 2 == 0) {
            (maxHeap.peek() + minHeap.peek()) / 2
        } else {
            if (maxHeap.size > minHeap.size) maxHeap.peek()
            else minHeap.peek()
        }
    }

    fun updateSet(i: Int) {
        if (maxHeap.isEmpty() || i <= maxHeap.peek()) {
            maxHeap.offer(i)
        } else {
            minHeap.offer(i)
        }
        balanceHeaps()
    }

    private fun balanceHeaps() {
        val totalSize = maxHeap.size + minHeap.size
        if (totalSize % 2 == 0) {
            if (maxHeap.size > minHeap.size + 1) {
                minHeap.offer(maxHeap.poll())
            } else if (minHeap.size > maxHeap.size + 1) {
                maxHeap.offer(minHeap.poll())
            }
        } else {
            if (maxHeap.size > minHeap.size) {
                minHeap.offer(maxHeap.poll())
            } else if (minHeap.size > maxHeap.size) {
                maxHeap.offer(minHeap.poll())
            }
        }
    }
}