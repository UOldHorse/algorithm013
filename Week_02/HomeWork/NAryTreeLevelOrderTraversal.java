
//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 105 👎 0

package HomeWork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N叉数的层序遍历
 */
public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new LinkedList<>();
            //层序遍历通过一个队列来保存本层的节点
            Queue<Node> queue = new LinkedList<>();
            //压入子节点
            queue.add(root);
            while(!queue.isEmpty()){
                //记住每一轮开始时的队列大小
                List<Integer> list = new ArrayList();
                int count = queue.size();
                for (int i =0;i<count;i++){
                    Node node = queue.poll();
                    if(node!=null){
                        list.add(node.val);
                        queue.addAll(node.children);
                    }
                }
                if(!list.isEmpty())
                    res.add(list);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}