
//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 622 👎 0

package HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            //中序遍历 先遍历左子树 再自己 再右子树
            List<Integer> array = new ArrayList<>();
            subMethod(root,array);
            return  array;
        }

        public void subMethod(TreeNode root, List<Integer> array){
            if (root == null) {
                return;
            }
            subMethod(root.left,array);
            array.add(root.val);
            subMethod(root.right,array);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}