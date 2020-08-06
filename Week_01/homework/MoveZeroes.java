package homework;

public class MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            //双指针
            int i = 0;
            int j = 1;
            while(j<nums.length){
                if(nums[j] != 0){
                    nums[i] = nums[j];
                    i++;
                }
                j++;
            }
            while (i<nums.length){
                nums[i] = 0;
                i++;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}