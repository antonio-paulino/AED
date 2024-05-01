package serie2.mapCollections

operator fun <K, V> AEDMutableMap<K, V>.set(key: K, value: V){
   put(key, value)
}

operator fun <K,V> AEDMutableMap<K, V>.contains(k:K):Boolean{
    return containsKey(k)
}

fun main() {
    val map = AEDHashMapAED<String, Int>()
    val v0 = map["key2"]
    map["key1"] = 1
    map["key2"] = 2
    val v1 = map["key2"]
    println("key1" in map)
    println("key2" in map)
    println("key3" in map)
    println(v1)
    println(v0)
}