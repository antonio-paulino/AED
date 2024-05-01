package serie2


fun median(v: IntArray, l: Int, r: Int): Int {

    var idxMed = v.size/2
    var idxPivot = partition(v,l,r)
    var left = l
    var right = r
    if(v.size %2 != 0){
       while (idxMed != idxPivot) {

            if(idxPivot < idxMed)
                left = idxPivot + 1
            else
                right = idxPivot - 1
           idxPivot = partition(v, left, right)
        }
        return v[idxPivot]
    }
    else  {
        var med1check = false
        var med2check = false
        val idxMed2 = idxMed - 1
        while (!(med1check && med2check)) {
            if(idxPivot == idxMed) med1check = true
            if(idxPivot == idxMed2) med2check = true

            if(idxPivot < idxMed)
                left = idxPivot + 1
            else
                right = idxPivot - 1
            idxPivot = partition(v, left, right)
        }
            return  (v[idxMed] + v[idxMed2])/2
    }

}
fun partition(a: IntArray, left: Int, right: Int): Int {
    var i = left - 1
    var j = right
    val pivot = a[right]
    while (true) {
        while (i < right && a[++i] < pivot);
        while (j > left && a[--j] > pivot);
        if (i >= j) break
        exchange(a, i, j)
    }
    exchange(a, i, right)
    return i
}

fun  exchange(a: IntArray, i: Int, j: Int) {
    val x = a[i]
    a[i] = a[j]
    a[j] = x
}


