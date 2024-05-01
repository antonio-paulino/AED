import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.exp
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class CreateBSTFromRangeTest {

    val cmp = { i1: Int, i2: Int -> i1 - i2 }

    @Test
    fun creatBSTFromRange_empty_trees() {
        var tree = emptyBST()
        val resultTree = createBSTFromRange(0, -1)
        assertEqual(tree,resultTree)
        tree = singleNodeBST(0)
    }

     @Test
     fun createBSTFromRange_singleton_trees(){
         var tree =populatedBST(intArrayOf(1))
         val resultTree = createBSTFromRange(1, 1)
         assertEqual(tree,resultTree)
     }

    @Test
    fun createBSTFromRange_someElements1_trees(){
        var tree =populatedBST(intArrayOf(5,2,7,1,3,4,6,8,9))
        val resultTree = createBSTFromRange(1, 9)
        assertEqual(tree,resultTree)
    }

    //adicional
    @Test
    fun createBSTFromRange_threeElements() {
        var tree = populatedBST(intArrayOf(2,1,3))
        var resultTree = createBSTFromRange(1,3)
        assertEqual(tree,resultTree)
        tree = populatedBST(intArrayOf(4,5,3))
        resultTree = createBSTFromRange(3,5)
        assertEqual(tree,resultTree)
        tree = populatedBST(intArrayOf(7,8,6))
        resultTree = createBSTFromRange(6,8)
        assertEqual(tree,resultTree)
    }

    @Test
    fun createBSTFromRange_evenRange() {
        var tree = populatedBST(intArrayOf(4, 2, 3, 6, 5, 7))
        var resultTree = createBSTFromRange(2, 7)
        assertEqual(tree, resultTree)
        tree = populatedBST(intArrayOf(2,3,4,5,6,7))
        assertNotEquals(resultTree,tree)
        tree = populatedBST(intArrayOf(12, 10, 14, 11, 13, 15))
        resultTree = createBSTFromRange(10, 15)
        assertEqual(tree, resultTree)
        tree = populatedBST(intArrayOf(14,11,12,13,15))
        assertNotEquals(resultTree,tree)
    }

    @Test
    fun createBSTFromRange_oddRange() {
        var tree = populatedBST(intArrayOf(5, 3, 6, 4, 7))
        var resultTree = createBSTFromRange(3, 7)
        assertEqual(tree, resultTree)
        resultTree = populatedBST(intArrayOf(5,6,7,8,9))
        assertNotEquals(resultTree,tree)
        tree = populatedBST(intArrayOf(7, 5, 6, 8, 9))
        resultTree = createBSTFromRange(5, 9)
        assertEqual(tree, resultTree)
        tree = populatedBST(intArrayOf(8,9,7,6,5))
        assertNotEquals(resultTree,tree)
    }

    @Test
    fun createBSTFromRange_negativeRange() {
        var tree = populatedBST(intArrayOf(-3, -5, -1, -6, -4, -2))
        var resultTree = createBSTFromRange(-6, -1)
        assertEqual(tree, resultTree)
        tree = populatedBST(intArrayOf(-7, -9, -5, -10, -8, -6,-4))
        resultTree = createBSTFromRange(-10, -4)
        assertEqual(tree, resultTree)
    }

    @Test
    fun createBSTFromRange_mixedBigRange() {
        val tree = populatedBST(intArrayOf(0,5,2,8,3,1,4,6,7,9,10,-5,-8,-2,-1,-3,-4,-6,-7,-9,-10))
        val resultTree = createBSTFromRange(-10, 10)
        assertEqual(tree, resultTree)
    }




}


