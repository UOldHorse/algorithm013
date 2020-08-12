//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划

package HomeWork;

/**
 * 丑数
 */
public class UglyNumberIi {
    public static void main(String[] args) {
        Solution solution = new UglyNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthUglyNumber(int n) {
            //动态规划
            int[] res = new int[n];
            //*2,*3,*5的数的下标
            int index_2= 0;
            int index_3= 0;
            int index_5= 0;
            res[0] = 1;
            for (int i = 1;i<n;i++){
                res[i] = Math.min(Math.min(res[index_2]*2,res[index_3]*3),res[index_5]*5);
                if(res[i] == res[index_2]*2)
                    index_2 ++;
                if(res[i] == res[index_3]*3)
                    index_3 ++;
                if(res[i] == res[index_5]*5)
                    index_5 ++;
            }
            return res[n-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}