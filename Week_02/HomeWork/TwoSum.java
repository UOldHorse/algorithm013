//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package HomeWork;

import java.util.HashMap;

/**
 * 两数之和
 */
public class TwoSum {
    public static void main(String[] args) {

        Solution solution = new TwoSum().new Solution();
        int[] ints = solution.twoSum(new int[]{3, 2, 4}, 6);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            //使用一个Map来保存
            HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
            for (int i =0;i<nums.length;i++){
                int a = target - nums[i];
                if(map.containsKey(a)) {
                    return new int[]{i,map.get(a)};
                }
                else {
                    map.put(nums[i],i);
                }
            }
            return new int[]{0,0};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}