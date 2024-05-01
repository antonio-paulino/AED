import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ContainsTest {

    val cmp={ i1: Int, i2: Int -> i1 - i2 }
    @Test
    fun contains_empty_trees() {
        val tree = emptyBST()
        assertFalse(contains(tree,0,3,cmp))
    }


    @Test
    fun contains_singleNodeBST() {
        val tree = singleNodeBST(1)
        assertTrue(contains(tree,1,1,cmp))
        assertTrue(contains(tree,0,3,cmp))
        assertFalse(contains(tree,2,4,cmp))

    }

    @Test
    fun contains_fullBST() {
        val tree = populatedBST(intArrayOf(10,5,12,4,2,16,11))
        assertFalse(contains(tree,-3,1,cmp))
        assertTrue(contains(tree,2,16,cmp))
        assertTrue(contains(tree,12,16,cmp))
        assertTrue(contains(tree,11,12,cmp))
        assertTrue(contains(tree,4,5,cmp))
        assertTrue(contains(tree,5,5,cmp))
        assertFalse(contains(tree,17,20,cmp))

    }

    @Test
    fun contains_bigBST() {
        val tree = populatedBST(intArrayOf(30,10,5,12,4,3,0,11,40,50))
        assertFalse(contains(tree,-30,-1,cmp))
        assertTrue(contains(tree,2,30,cmp))
        assertTrue(contains(tree,30,50,cmp))
        assertTrue(contains(tree,3,4,cmp))
        assertTrue(contains(tree,4,11,cmp))
        assertTrue(contains(tree,40,50,cmp))
        assertFalse(contains(tree,51,60,cmp))
    }
    //adicionais
    @Test
    fun contains_outOfRange() {
        val tree = populatedBST(intArrayOf(5, 10, 15, 20, 25, 30))
        assertFalse(contains(tree, 1, 4, cmp))
        assertFalse(contains(tree, 35, 40, cmp))
    }

    @Test
    fun contains_singleNodeNotInRange() {
        val tree = singleNodeBST(5)
        assertFalse(contains(tree, 1, 4, cmp))
        assertFalse(contains(tree, 6, 10, cmp))
    }
    @Test
    fun contains_bigBST_loop() {
        var tree = populatedBST(IntArray(10000) {it})
        for (i in 0..9999) {
            assertTrue(contains(tree, i, i, cmp))
        }
        for (i in 0..9999) {
            assertTrue(contains(tree, 0, i, cmp))
        }
        for (i in -1 downTo -1000) {
            assertFalse(contains(tree, 0, i, cmp))
        }
    }

    @Test
    fun contains_twoNodeBST() {
        var tree = populatedBST(intArrayOf(1,2))
        assertTrue(contains(tree,0,123,cmp))
        assertTrue(contains(tree,1,1,cmp))
        assertTrue(contains(tree,2,2,cmp))
        assertFalse(contains(tree,-5,0, cmp))
        assertFalse(contains(tree,3,56, cmp))
        tree = populatedBST(intArrayOf(23,43))
        assertTrue(contains(tree,23,34,cmp))
        assertTrue(contains(tree,37,43,cmp))
        assertFalse(contains(tree,26,26,cmp))
        assertFalse(contains(tree,-5,0, cmp))
        assertFalse(contains(tree,45,56, cmp))
    }

    @Test
    fun contains_threeNodeBST() {
        var tree = populatedBST(intArrayOf(42,2,10))
        assertTrue(contains(tree,0,123,cmp))
        assertTrue(contains(tree,2,10,cmp))
        assertFalse(contains(tree,-5,0, cmp))
        assertFalse(contains(tree,3,9, cmp))
        tree = populatedBST(intArrayOf(23,43,21))
        assertTrue(contains(tree,23,34,cmp))
        assertTrue(contains(tree,37,43,cmp))
        assertFalse(contains(tree,26,26,cmp))
        assertFalse(contains(tree,-5,0, cmp))
        assertFalse(contains(tree,45,56, cmp))
    }
}