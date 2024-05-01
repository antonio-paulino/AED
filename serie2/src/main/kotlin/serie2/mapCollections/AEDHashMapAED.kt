package serie2.mapCollections

class AEDHashMapAED<K, V> : AEDMutableMap<K, V> {

    override var size: Int = 0
        private set
    private var dimTable = 0
    private var table: Array<AEDHashNode<K, V>?>

    private class AEDHashNode<K, V> : AEDMutableMap.MutableEntry<K, V> {
        constructor(k: K, v: V) {
            key = k
            value = v
        }

        override var key: K
        override var value: V
        var next: AEDHashNode<K, V>? = null
        override fun setValue(newValue: V): V {
            val oldval = value
            value = newValue
            return oldval
        }
    }

    constructor() {
        table = arrayOfNulls<AEDHashNode<K, V>?>(11)
        dimTable = table.size
    }


    private fun hash(k: K): Int {
        var idx = k.hashCode() % dimTable
        if (idx < 0) idx += dimTable
        return idx
    }

    override fun put(key: K, value: V): V? {
        val idx = hash(key)
        var curr = table[idx]
        while (curr != null) {
            if (key == curr.key) {
                return curr.setValue(value)
            }
            curr = curr.next
        }

        if (size.toDouble() / dimTable >= 0.75) resize()

        val newNode = AEDHashNode(key, value)
        newNode.next = table[idx]
        table[idx] = newNode
        size++

        return null
    }

    private fun resize() {
        dimTable *= 2
        val newTable = arrayOfNulls<AEDHashNode<K, V>?>(dimTable)
        for (i in table.indices) {
            var curr = table[i]
            while (curr != null) {
                table[i] = table[i]?.next
                val idx = hash(curr.key)
                curr.next = newTable[idx]
                newTable[idx] = curr
                curr = table[i]
            }
        }
        table = newTable
    }

    override operator fun get(key: K): V? {
        val idx = hash(key)
        var curr = table[idx]
        while (curr != null) {
            if (key!!.equals(curr.key)) {
                return curr.value
            }
            curr = curr.next
        }
        return null
    }

    override fun iterator(): Iterator<AEDMutableMap.MutableEntry<K, V>> = MyIterator()

    private inner class MyIterator : Iterator<AEDMutableMap.MutableEntry<K, V>> {
        var currIdx = -1
        var currNode: AEDHashNode<K, V>? = null
        var list: AEDHashNode<K, V>? = null

        override fun hasNext(): Boolean {
            if (currNode != null) return true
            while (currIdx < table.size) {
                if (list == null) {
                    currIdx++
                    if (currIdx < table.size) list = table[currIdx]
                } else {
                    currNode = list
                    list?.let { l -> list = l.next }
                    return true
                }
            }
            return false
        }

        override fun next(): AEDMutableMap.MutableEntry<K, V> {
            if (!hasNext()) throw NoSuchElementException()
            val aux = currNode
            currNode = null
            return aux ?: Any() as AEDMutableMap.MutableEntry<K, V>
        }
    }

    override fun containsKey(k: K): Boolean {
        val idx = hash(k)
        var curr = table[idx]
        while (curr != null) {
            if (k != null && k == curr.key) return true
            curr = curr.next
        }
        return false
    }

    override fun remove(key: K): V? {
        val idx = hash(key)
        var curr = table[idx]
        var prev: AEDHashNode<K, V>? = null
        while (curr != null) {
            if (key == curr.key) {
                val value = curr.value
                if (curr == table[idx]) table[idx] = curr.next
                else prev!!.next = curr.next
                size--
                return value
            } else {
                prev = curr
                curr = curr.next
            }
        }
        return null
    }
}


