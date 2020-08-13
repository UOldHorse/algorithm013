package homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.subsets(new int[]{1,2,3}));
    }
    /**
     * 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //爬楼梯 动态规划 只能从下面一格或者下面两格爬到当前位置
        int[] res = new int[n+1];
        //初始条件
        res[0] = 1;
        res[1] = 2;
        int i =2;
        while (i<n){
            //转移方程
            res[i] = res[i-1]+res[i-2];
            i++;
        }
        return res[n-1];
    }

    /**
     * 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        //左括号随时可以加 左个数》右个数
        List<String > res = new LinkedList<>();
        generate(0,0,n,"",res);
        return res;
    }

    private void generate(int left, int right,int max, String s,List<String> res) {
        //terminator
        if((left+right)==2*max){
            System.out.println(s);
            res.add(s);
            return;
        }
        //process
        if(left>right){
            String s1 = s + ")";
            generate(left,right+1,max,s1,res);
        }
        if(left<max){
            String s2 = s + "(";
            generate(left+1,right,max,s2,res);
        }
        //reverse states
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        //二分乘法
        if (n == 0) { return 1; }
        if (n == 1) { return x; }
        if (n == -1) { return 1 / x; }
        double half = myPow(x, n / 2);
        double rest = myPow(x, n % 2);
        return rest * half * half;
    }

    /**
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add( new ArrayList<Integer>());
        subMethod(nums,0,res);
        return res;
    }

    private void subMethod(int[] nums,int i, List<List<Integer>> res) {
        if(i == nums.length)
            return;
        int count = res.size();
        for (int m =0;m<count;m++){
            List<Integer> buff = new LinkedList<Integer>(res.get(m));
            buff.add(nums[i]);
            res.add(buff);
        }
        subMethod(nums,i+1,res);
    }

}