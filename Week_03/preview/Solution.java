package preview;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.generateParenthesis(3));
        //System.out.println(solution.subsets(new int[]{1,2,3}));
        //int[] nums = new int[]{3,2,3};
        //solution.letterCombinations("");
        //solution.majorityElement(nums);
    }
    /**
     * 爬楼梯
     * @param n 第几层楼梯
     * @return 可能数
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
     * 子集
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

    /**
     * 获取众数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums){
        //1.使用HashMap 时间O(n) 空间O(n)
        //return majorityElementUseHashMap(nums);
        //2.排序后再计数 排序O(nlogn) 空间O(n)
        //return majorityElementUseSort(nums);
        //3.分治算法 如果a是nums[0-n]的众数那么 a至少是nums[0-n/2],nums[n/2,n]中的一个众数
        //return majorityElementUseDrive(nums);
        return majorityElementUseBoyerMoore(nums);
    }
    // 获取众数 1.使用hashMap计数
    private int majorityElementUseHashMap(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for(int num :nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        for(Integer key:map.keySet()){
            if(map.get(key) > nums.length/2 )
                return key;
        }
        return 0;
    }
    // 获取众数 2.排序后计数
    private int majorityElementUseSort(int[] nums){
        Arrays.sort(nums);
        int count = 0;
        int val = -1;
        for(int num:nums){
            if(num!=val){
                count = 1;
                val = num;
            }else{
                count++;
            }
            if(count>nums.length/2)
                return num;
        }

        return 0;
    }
    // 获取众数 3.使用分治算法
    private int majorityElementUseDrive(int[] nums){
        return majorityElementUseDriveSub(nums,0,nums.length-1);
    }
    private int majorityElementUseDriveSub(int[] nums, int i, int j){
        //terminator
        if(i == j){
            return nums[i];
        }
        //对半开
        int mid = (i+j)/2;
        int left = majorityElementUseDriveSub(nums,i,mid);
        int right = majorityElementUseDriveSub(nums, mid+1, j);
        if(left == right){
            return left;
        }
        int leftCount = numCount(nums, left, i, j);
        int rightCount = numCount(nums, right, i, j);

        return leftCount>(j-i+1)/2?left:right;
    }
    private int numCount(int[] nums,int num,int l,int h){
        int count = 0;
        for(int i= l ;i<=h;i++){
            if(nums[i]==num){
                count++;
            }
        }
        return count;
    }
    // 获取众数 4.投票算法  最简便！
    private int majorityElementUseBoyerMoore(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
    /**
     * 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return new LinkedList<>();
        }
        //1.号码与字符的映射
        String[][] m = new String[][]{
                {"a","b","c"},
                {"d","e","f"},
                {"g","h","i"},
                {"j","k","l"},
                {"m","n","o"},
                {"p","q","r","s"},
                {"t","u","v"},
                {"w","x","y","z"}
        };
        //1.使用循环
        //return letterCombinationsUseLoop(digits,m);
        //2.使用的递归
        List<String> res = new LinkedList<>();
        search("",digits, 0, res, m);
        return res;
    }
    //使用循环
    private List<String> letterCombinationsUseLoop(String digits,String[][] m){
        Queue<String> res = new LinkedList<>();
        res.add("");
        for(int i=0;i<digits.length();i++){
            String[] strings = m[digits.charAt(i)-'2'];
            int count = res.size();
            for(int j=0;j<count;j++){
                String poll = res.poll();
                for(String string:strings){
                    res.offer(poll+string);
                }
            }
        }

        return (List<String>) res;
    }
    //使用递归
    private void search(String s, String digits, int i, List<String> res, String[][] m){
        //terminator
        if(i ==digits.length()){
            res.add(s);
            return;
        }
        //process
        String[] string = m[digits.charAt(i)-'2'];
        //drill down
        for (int j =0;j<string.length;j++){
            search(s+string[j],digits,i+1,res,m);
        }
        //reverse

    }
}
