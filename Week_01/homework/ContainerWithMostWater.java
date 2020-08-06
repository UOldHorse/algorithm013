package homework;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
    }
    //注意两个指针都是动态的面积不变的情况下两个指针都会动
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            //头尾两个指针
            int i = 0;
            int j = height.length - 1;
            //初始值
            int max = Math.min(height[i],height[j]) * (j-i);

            while(i!=j){
                //如果i是短板，则移动i的下标
                if(height[i] == Math.min(height[i],height[j])){
                    i++;
                }
                //如果i不是短板，则移动j的下标
                else{
                    j--;
                }
                max = Math.max(Math.min(height[i],height[j])*(j-i),max);
            }
            return max;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}