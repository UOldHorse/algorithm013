//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树

package HomeWork;

import java.util.LinkedList;
import java.util.List;

public class NAryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    // Definition for a Node.
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

    ;


    class Solution {
        public List<Integer> preorder(Node root) {
            //使用迭代
            //return useRecursion(root);
            //使用递归
             List<Integer> results = new LinkedList();
             //递归算法 时间O(n) 每个节点访问一次
             subMethod(root,results);
             return results;
        }

        /**
         * 使用迭代
         * @param root
         * @return
         */
        public List<Integer> useRecursion(Node root) {
            //使用栈来保存
            LinkedList<Node> stack = new LinkedList<Node>();
            //压入首个节点
            stack.add(root);
            List<Integer> res = new LinkedList<Integer>();
            while (!stack.isEmpty()){
                Node node = stack.pollLast();
                if(node!=null){
                    res.add(node.val);
                    for (int i = node.children.size()-1; i >= 0 ; i--){
                        stack.add(node.children.get(i));
                    }
                }
            }
            return res;
        }

        /**
         * 使用递归
         * @param root
         * @param array
         */
        public void subMethod(Node root,List<Integer> array){
            if(root != null){
                array.add(root.val);
                //遍历添加子节点
                for(Node children:root.children){
                    subMethod(children,array);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}