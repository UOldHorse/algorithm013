//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

package HomeWork;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
    }

    /**
     * 字母异位词分组
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String,List<String>> map = new HashMap<>();
            for (String str:strs){
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String buff = String.valueOf(chars);
                if (!map.containsKey(buff)){
                    map.put(buff,new ArrayList<>());
                }
                map.get(buff).add(str);
            }
            List<List<String>> res = new ArrayList<>();
            for(List<String> value :map.values()){
                res.add(value);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}