import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class IsBalancedTest {

    private val CMP_NATURAL_ORDER = { i1: Int, i2: Int -> i1.compareTo(i2)}
    @Test
    fun isBalanced_empty_trees() {
        val tree = emptyBST()
        assertTrue(isBalanced(tree))
    }

    @Test
    fun isBalanced_singleNodeBST() {
        var tree = singleNodeBST(1)
        assertTrue(isBalanced(tree))
        tree = add(tree, 0, CMP_NATURAL_ORDER)
        assertEquals(2, sizeTree(tree))
        assertTrue(isBalanced(tree))
        tree = add(tree, 2, CMP_NATURAL_ORDER)
        assertEquals(3, sizeTree(tree))
        assertTrue(isBalanced(tree))
    }

    @Test
    fun isBalanced_populatedBST() {
        var tree = populatedBST(intArrayOf(10, 4, 1, 8, 5, 6, 7, 0, 2, 3, 9, 12, 11))
        assertFalse(isBalanced(tree))
        tree = remove(tree, 3, CMP_NATURAL_ORDER)
        tree = remove(tree, 6, CMP_NATURAL_ORDER)
        tree = remove(tree, 7, CMP_NATURAL_ORDER)
        assertEquals(10, sizeTree(tree))
        assertTrue(isBalanced(tree))
    }

    @Test
    fun isBalanced_completeBST() {
        var tree = completeBST()
        assertTrue(isBalanced(tree))
        tree = remove(tree, 1, CMP_NATURAL_ORDER)
        tree = remove(tree, 5, CMP_NATURAL_ORDER)
        tree = remove(tree, 9, CMP_NATURAL_ORDER)
        tree = remove(tree, 13, CMP_NATURAL_ORDER)
        assertEquals(11, sizeTree(tree))
        assertTrue(isBalanced(tree))
        tree = remove(tree, 3, CMP_NATURAL_ORDER)
        tree = remove(tree, 7, CMP_NATURAL_ORDER)
        assertEquals(9, sizeTree(tree))
        assertTrue(isBalanced(tree))
        tree = remove(tree, 6, CMP_NATURAL_ORDER)
        tree = remove(tree, 2, CMP_NATURAL_ORDER)
        assertEquals(7, sizeTree(tree))
        assertFalse(isBalanced(tree))
    }

    //adicionais
    @Test
    fun isBalanced_twoNodeBST() {
        var tree = populatedBST(intArrayOf(7,4))
        assertTrue(isBalanced(tree))
        tree = populatedBST(intArrayOf(1,2))
        assertTrue(isBalanced(tree))
    }

    @Test
    fun isBalanced_threeNodeBST() {
        var tree = populatedBST(intArrayOf(7,4,3))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(7,9,8))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(3,10,11))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(6,3,2))
        assertFalse(isBalanced(tree))

        tree = populatedBST(intArrayOf(10,12,8))
        assertTrue(isBalanced(tree))
        tree = populatedBST(intArrayOf(3,1,4))
        assertTrue(isBalanced(tree))
        tree = populatedBST(intArrayOf(102,1129,44))
        assertTrue(isBalanced(tree))
        tree = populatedBST(intArrayOf(55,1229,31))
        assertTrue(isBalanced(tree))
    }

    @Test
    fun isBalanced_unbalancedTree() {
        var tree = populatedBST(intArrayOf(10, 5, 15, 3, 7, 13, 20, 1, 2))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(7,5,8,9,6,3,2,1))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(9,25,32,40,45,48,50))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(9,8,7,6,5,4,3,2,1))
        assertFalse(isBalanced(tree))
        tree = populatedBST(intArrayOf(74,3,42,64,5,37,48,49,19,40,15,30,89,92,80,82))
        assertFalse(isBalanced(tree))
    }

    @Test
    fun isBalanced_balancedTree() {
        var tree = populatedBST(intArrayOf(10, 5, 15, 3, 7, 13, 20))
        assertTrue(isBalanced(tree))
        tree = populatedBST(intArrayOf(41, 65, 50, 91, 72, 99, 20, 11, 29, 32))
        assertTrue(isBalanced(tree))
    }
}





