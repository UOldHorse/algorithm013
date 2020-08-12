
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 227 👎 0

package HomeWork;

import java.util.HashMap;

public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
        boolean b = solution.useHashMap("a", "b");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram(String s, String t) {
            //return  useHashMap(s,t);
            return  useArray(s,t);
        }
        //0.暴力法 对于每个数组都遍历 时间O(N^2) 空间O(1)
        //1.使用HashMap 时间O(n) 空间O(n)
        private boolean useHashMap(String s,String t){
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i =0;i<s.length();i++){
                if(map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i),map.get(s.charAt(i)) + 1);
                }
                else{
                    map.put(s.charAt(i), 1);
                }
            }
            for (int i =0;i<t.length();i++){
                if(map.containsKey(t.charAt(i))){
                    map.put(t.charAt(i),map.get(t.charAt(i)) - 1);
                }
                else{
                    map.put(t.charAt(i), -1);
                }
            }
            for (Integer i :map.values()){
                if(i!=0)
                    return false;
            }
            return true;
        }
        //2.使用数组 代替hash表 可用字符表比较大的时候占用空间太大
        private boolean useArray(String s,String t){
            int[] array = new int[256];
            for (int i =0;i<s.length();i++){
                array[s.charAt(i)] ++;
            }
            for (int i =0;i<t.length();i++){
                array[t.charAt(i)] --;
            }
            for (int i =0;i<256;i++){
                if (array[i]!=0){
                    return  false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}