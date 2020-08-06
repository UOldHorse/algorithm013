//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组

package homework;

public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            //本题的主要点是考虑进位，特别是999的时候
            int count = 0;
            for(int i = digits.length - 1;i>=0;i--){
                if(i == digits.length - 1){
                    count = (digits[i] + 1)/10;
                    digits[i] = (digits[i] + 1) % 10;
                }else {
                    int buff = (digits[i] + count)/10;
                    digits[i] = (digits[i] + count) % 10;
                    count = buff;
                }
                if(count == 0)
                    break;
            }
            if(count == 0){
                return digits;
            }
            else {
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                for (int i =0;i<digits.length;i++){
                    res[i+1] = digits[i];
                }
                return res;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}