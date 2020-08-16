package preview;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if(n == 1)
                return 1;
            if(n == 2)
                return 2;
            //动态规划使用数组存储之前的结果
            //初始条件
            int[] res = new int[n];
            res[0] = 1;
            res[1] = 2;
            for (int i =2;i<n;i++){
                res[i] = res[i-1] + res[i-2];
            }
            return res[n-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}