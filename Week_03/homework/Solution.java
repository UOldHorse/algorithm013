package homework;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nthSuperUglyNumber(12,new int[]{2,7,13,19});
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] index = new int[primes.length] ;
        int[] res = new int[n+1];
        res[0] = 1;
        int count = 0;
        for(int i =1;i<=n;i++){
            int min = Integer.MAX_VALUE;
            for(int j =0;j<primes.length;j++){
                int val = primes[j] * res[index[j]];
                if(min>val)
                    min = val;
            }
            for(int j =0;j<primes.length;j++){
                if(min == primes[j] * res[index[j]])
                    index[j] ++;
            }
            res[i] = min;
            System.out.println(min);
        }
        return res[n];
    }
    /**
     * 二叉树的最近公共祖先
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> res1 = new LinkedList<>();
        List<TreeNode> res2 = new LinkedList<>();
        recur(root, p.val, res1);
        //System.out.println("-------------");
        recur(root, q.val, res2);
        int count = Math.min(res1.size(),res2.size());
        for(int i =0;i<count;i++){
            if(res2.get(i).val != res1.get(i).val){
                return res2.get(i-1);
            }
        }
        return res2.get(count-1);
    }
    public boolean recur (TreeNode root,int val,List<TreeNode> res){
        //terminator
        if(root == null){
            return false;
        }
        //process
        res.add(root);
        //System.out.println("+"+root.val);
        if(root.val == val){
            return true;
        }
        //drill down
        if(recur(root.left, val, res)||recur(root.right, val, res)){
            return true;
        }else{
            res.remove(res.size()-1);
            //System.out.println("-"+root.val);
            return false;
        }

    }
    //最优解法
    public TreeNode lowestCommonAncestorOpt(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //前序遍历的第一个节点是根节点
        //中序遍历的根节点左右是左右子树
        return recur(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    private TreeNode recur(
            int[] preorder,int pi,int pj,
            int[] inorder ,int ii,int ij){
        if(pi > pj|| ii > ij){
            return null;
        }
        //找到本节点
        int root = preorder[pi];
        TreeNode node = new TreeNode(root);
        //查找左右子树
        int index = ii;
        while(inorder[index] != root){
            index ++;
        }
        //计算左右子树长度

        node.left = recur(preorder, pi + 1 , pi + index - ii, inorder, ii, index -1);
        node.right = recur(preorder, pi + index - ii + 1, pj, inorder, index + 1, ij);
        return node;
    }

    /**
     * 组合
     */
    private List<List<Integer>> r;
    public List<List<Integer>> combine(int n, int k) {
        r = new LinkedList<>();
        recur(1, n, 0, k, new LinkedList<Integer>());
        return r;
    }
    private void recur(int i,int n,int j,int k,LinkedList<Integer> res){
        //terminator
        if(j == k){
            r.add(new LinkedList<Integer>(res));
        }
        //process
        for(;i<=n;i++){
            res.add(i);
            //drill down
            recur(i+1, n, j+1, k, res);
            res.removeLast();
        }
    }

    /**
     * 全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        boolean[] flag = new boolean[nums.length];
        recur(0,nums,flag,new LinkedList<Integer>());
        return  res;
    }
    private List<List<Integer>> res;
    private void recur(int index,int[] nums, boolean[] flag,LinkedList<Integer> r){
        //treminator
        if(index == nums.length){
            res.add(new LinkedList<>(r));
        }
        //process
        for (int i = 0;i<nums.length;i++){
            if(!flag[i]){
                r.add(nums[i]);
                flag[i] = true;
                //drill down
                recur(index + 1,nums,flag,r);
                r.removeLast();
                flag[i] = false;
            }
        }
    }

    /**
     * 全排列II
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        //排序
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        recur_II(0,nums,flag,new LinkedList<Integer>());
        return  res;
    }
    //private List<List<Integer>> res;
    private void recur_II(int index,int[] nums, boolean[] flag,LinkedList<Integer> r){
        //treminator
        if(index == nums.length){
            res.add(new LinkedList<>(r));
        }
        //process
        for (int i = 0;i<nums.length;i++){
            if(!flag[i]){
                //drill down
                if(i>=1&&nums[i] == nums[i-1]&&flag[i -1] == true){
                    break;
                }else {
                    flag[i] = true;
                    r.add(nums[i]);
                    recur_II(index + 1 ,nums,flag,r);
                    r.removeLast();
                    flag[i] = false;
                }
            }
        }
    }
}
