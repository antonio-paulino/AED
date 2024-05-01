data class Point(var x:Int, var y:Int)

fun first(v : Array<Int>, l: Int, r:Int, element: Int) : Int {
    var left = l
    var right = r
    while (left <= right) {
        val mid = (left + right) / 2
        if ((mid == 0 || element > v[mid-1]) && v[mid] == element) return mid else if (element > v[mid]) left = mid + 1
        else right = mid - 1
    }
    return -1
}

fun last(v : Array<Int>, l: Int, r:Int, element: Int) : Int {
    var left = l
    var right = r
    while (left <= right) {
        val mid = (left + right) / 2
        if ((mid == r || v[mid+1] > element) && v[mid] == element) return mid else if (element < v[mid]) right = mid -1
        else left = mid + 1
    }
    return -1
}
fun count(v: Array<Int>, l: Int, r: Int, element: Int): Int {
    if (r == 0) return 0
    val first = first(v,l,r,element)
    if (first == -1) return 0
    return last(v,first,r,element) - first + 1
}

fun minAbsSum(ar:Array<Int>): Pair<Int, Int>? {
    var l = 0
    var r = ar.size - 1
    if (r == -1 || r == 0) return null
    else {
        var pair = Pair(0,0)
        var sum = absSum(ar[l], ar[r])
        while (l < r) {
            if(absSum(ar[l],ar[r]) <= sum){
                pair = Pair(ar[l],ar[r])
                sum = absSum(ar[l],ar[r])
                if(pair.first != pair.second && sum == 0) return pair
            }
            if(ar[l] + ar[r] > 0) r-- else l++
        }
        return if (pair.first == pair.second) null
        else pair
    }
}

fun absSum(first : Int, second : Int) : Int {
    val sum = first + second
    return if (sum > 0) sum
    else - sum
}



fun countSubKSequences(a: Array<Int>, k: Int): Int {
    if (a.size < k) return 0
    else if (a.size == k) return 1
    else {
        var count = 1
        var seqcount = 0
        var i = 0
        while (i < a.size-1) {
            if (a[i] <= a[i+1]) {
                count++
                i++
            }
            else {
                seqcount += count - (k - 1)
                count = 1
                i++
            }
        }
        if(count == a.size) {seqcount += count - (k - 1)}
        return seqcount
    }
}

fun countEquals( points1: Array<Point>, points2: Array<Point>, cmp: (p1:Point, p2:Point )-> Int): Int {
    var count = 0
    var p1Iteration =0
    var p2Iteration =0

    if (points1.size == 0 || points2.size == 0) return 0
    else {
        val maxArray = if (points1.size > points2.size) points1 else points2 // get the array with more point
        for (i in 0..maxArray.size - 1) {
            val compare = cmp(points1[p1Iteration], points2[p2Iteration])
            if (compare < 0) p1Iteration++
            else if (compare > 0) p2Iteration++
            else {
                count++
                p1Iteration++
                p2Iteration++
            }
        }
        return count
    }
}








