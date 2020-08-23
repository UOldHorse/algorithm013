package week04.homework;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.findMin(new int[]{3,4,5,1,2});
        //solution.searchMatrix(new int[][]{new int[]{1},new int[]{3}},1);
        //System.out.println(solution.isPerfectSquare_binarySearch(808201));

//        String[] strings = new String[]{"hit","hot","dot","dog","lot","log","cog"};
//        LinkedList linkedList = new LinkedList(Arrays.asList(strings));
//        solution. findLadders("hit","cog",linkedList);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * @param root 根节点
     * @return List<List < Integer>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> r = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    r.add(poll.val);
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
            if (r.size() != 0)
                res.add(r);
        }
        return res;
    }

    /**
     * 最小基因变化
     *
     * @param start 起始基因序列
     * @param end   目标基因序列
     * @param bank  基因库
     * @return 要变化的次数
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (set.contains(end) == false) {
            return -1;
        }
        char[] jiying = new char[]{'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        set.remove(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            for (int count = queue.size(); count > 0; count--) {
                char[] chars = queue.poll().toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];
                    for (int j = 0; j < jiying.length; j++) {
                        if (chars[i] == jiying[j]) {
                            continue;
                        } else {
                            chars[i] = jiying[j];
                            String s = new String(chars); //这边一定要这样写不然会有问题
                            if (set.contains(s)) {
                                //System.out.println(s);
                                queue.offer(s);
                                set.remove(s);
                                if (end.equals(s)) {
                                    return step;
                                }
                            }
                            chars[i] = temp;
                        }
                    }
                }
            }
            //System.out.println("=======");
        }
        return -1;
    }

    /**
     * 括号生成
     *
     * @param n 括号的对数
     * @return List<String>
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        recur(0, 0, n, "", res);
        return res;
    }
    private void recur(int leftCount, int rightCount, int n, String str, List<String> res) {
        //terminator
        if (leftCount + rightCount == 2 * n) {
            res.add(str);
        }
        //process
        if (leftCount < n) {
            recur(leftCount + 1, rightCount, n, str + "(", res);
        }
        if (rightCount < leftCount) {
            recur(leftCount, rightCount + 1, n, str + ")", res);
        }
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int max = Integer.MIN_VALUE;
            for (int count = q.size(); count > 0; count--) {
                TreeNode poll = q.poll();
                if (poll.val > max) {
                    max = poll.val;
                }
                if (poll.left != null)
                    q.offer(poll.left);
                if (poll.right != null)
                    q.offer(poll.right);
            }
            res.add(max);
        }
        return res;
    }

    /**
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int count = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            ++count;
            for (int i = 0; i < size; ++i) {
                String start = queue.poll();
                for (String s : wordList) {
                    // 已经遍历的不再重复遍历
                    if (visited.contains(s)) {
                        continue;
                    }
                    // 不能转换的直接跳过
                    if (!canConvert(start, s)) {
                        continue;
                    }
                    // 用于调试
                    // System.out.println(count + ": " + start + "->" + s);
                    // 可以转换，并且能转换成endWord，则返回count
                    if (s.equals(endWord)) {
                        return count + 1;
                    }
                    // 保存访问过的单词，同时把单词放进队列，用于下一层的访问
                    visited.add(s);
                    queue.offer(s);
                }
            }
        }
        return 0;
    }

    public boolean canConvert(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int count = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        List<List<String>> res = new LinkedList<>();
        if (!set.contains(endWord)) {
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Queue<LinkedList<String>> resQueue = new LinkedList<>();
        queue.add(beginWord);
        LinkedList<String> start = new LinkedList<>();
        visited.add(beginWord);
        start.add(beginWord);
        resQueue.add(start);
        boolean isFind = false;
        while (!queue.isEmpty() && !isFind) {
            Set<String> v = new HashSet<>();
            for (int count = queue.size(); count > 0; count--) {
                String poll = queue.poll();
                List<String> r = resQueue.poll();
                for (String word : wordList) {
                    if (visited.contains(word)) {
                        continue;
                    }
                    if (canConvert(word, poll)) {
                        if (word.equals(endWord)) {
                            isFind = true;
                        }
                        queue.add(word);
                        LinkedList<String> newR = new LinkedList<>(r);
                        newR.add(word);
                        System.out.println(newR);
                        resQueue.add(newR);
                        v.add(word);
                    }
                }
            }
            visited.addAll(v);
        }
        for (LinkedList<String> r : resQueue) {
            System.out.println(r);
            if (r.getLast().equals(endWord)) {
                res.add(r);
            }
        }
        return res;
    }

    /**
     * 岛屿数量
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        boolean[][] flag = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (flag[i][j] == false && grid[i][j] == '1') {
                    findIsland(grid, flag, i, j);
                    count++;
                }
            }
        }
        return count;

    }

    private void findIsland(char[][] grid, boolean[][] flag, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || flag[i][j] == true || grid[i][j] == '0')
            return;

        flag[i][j] = true;
        findIsland(grid, flag, i + 1, j);
        findIsland(grid, flag, i - 1, j);
        findIsland(grid, flag, i, j + 1);
        findIsland(grid, flag, i, j - 1);
    }

    /**
     * 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int money_5 = 0;
        int money_10 = 0;
        int money_20 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                money_5++;
            }
            if (bill == 10) {
                if (money_5 != 0) {
                    money_5--;
                    money_10++;
                } else {
                    return false;
                }
            }
            if (bill == 20) {
                if (money_10 >= 1 && money_5 >= 1) {
                    money_10--;
                    money_5--;
                } else if (money_5 >= 3) {
                    money_5 -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 买卖股票的最佳时机 II
     *
     * @param flag
     * @param nums
     * @param i
     * @return
     */
    public boolean recur(boolean[] flag, int[] nums, int i) {
        if (i == nums.length - 1) {
            return true;
        }
        if (i < 0 || i >= nums.length || flag[i]) {
            return false;
        }

        flag[i] = true;

        boolean recur1 = recur(flag, nums, i + nums[i]);
        boolean recur2 = recur(flag, nums, i - nums[i]);

        return recur1 || recur2;
    }

    public int maxProfit(int[] prices) {
        int count = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                count += prices[i + 1] - prices[i];
            }
        }
        return count;
    }

    /**
     * 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
                count++;
            } else {
                j++;
            }
        }
        return count;
    }

    /**
     * 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length <= 1)
            return true;
        //保存状态
        boolean[] res = new boolean[nums.length];
        //初始条件
        res[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (res[i] == true) {
                //转移公式
                for (int count = nums[i]; count > 0; count--) {
                    if (i + count < nums.length) {
                        res[i + count] = true;
                    }
                }
            }
        }
        return res[res.length - 1];
    }

    //最佳答案 前提当你能跳到某格的时候你可以跳到该格之前的所有格子
    public boolean canJumpOpt(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            //当未i>k时说明该格无法跳过
            if (i > k)
                return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    /**
     * 跳跃游戏II
     * <p>
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * <p>
     * 来源：力扣（LeetCode）
     * <p>
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * <p>
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        //保存状态
        int[] res = new int[nums.length];
        //初始条件
        res[0] = 0;
        for (int i = 0; i < nums.length; i++) {

            if (i == 0 || res[i] != 0) {
                //转移公式
                for (int count = nums[i]; count > 0; count--) {
                    if (i + count < nums.length) {
                        int value = res[i] + 1;
                        if (res[i + count] == 0) {
                            res[i + count] = value;
                        } else {
                            res[i + count] = value < res[i + count] ? value : res[i + count];
                        }
                    }
                }
            }
        }
        return res[res.length - 1];

    }

    /**
     * 平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 1)
            return 1;
        int i = 0;
        int j = x;
        while (j - i > 1) {
            int mid = i + (j - i) / 2;
            //System.out.println(mid);
            if (mid > x / mid) {
                j = mid;
            } else {
                i = mid;
            }

        }
        return i;
    }

    /**
     * 是否是完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        //暴力法 O(n)
        return isPerfectSquare_violence(num);
    }
    //1.暴力法 O(n)
    private boolean isPerfectSquare_violence(int num) {
        if (num == 1)
            return true;
        for (int i = 0; i < num; i++) {
            if (i * i >= num) {
                if (i * i == num) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    //2.二分查找 O(logN)
    private boolean isPerfectSquare_binarySearch(int num) {
        if (num == 1)
            return true;
        int i = 0;
        int j = num;
        while (j - i > 1) {
            int mid = i + (j - i) / 2;
            //System.out.println(mid);
            double res = num * 1.0 / mid; //防止越界
            if (mid == res) {
                return true;
            } else if (mid > res) {
                j = mid;
            } else {
                i = mid;
            }
        }
        return false;

    }

    /**
     * 搜索旋转排序数组 O(logn)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1, mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左段还是右段
            if (nums[mid] >= nums[lo]) {
                // 再判断 target 是在 mid 的左边还是右边，从而调整左右边界 lo 和 hi
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找二维数组
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length < 1) return false;
        int row = getRow(matrix, target);
        return find(matrix[row], target);
    }
    public int getRow(int[][] matrix, int target) {
        int top = 0, bottom = matrix.length - 1;
        int col = matrix[0].length - 1;
        while (top < bottom) {
            int mid = (top + bottom) / 2;
            if (matrix[mid][col] < target)
                top = mid + 1;
            else
                bottom = mid;
        }
        return top;
    }
    public boolean find(int[] data, int target) {
        int l = 0, r = data.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (data[mid] == target)
                return true;
            else if (data[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return false;
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] < nums[right]) {
                // middle可能是最小值
                right = middle;
            } else {
                // middle肯定不是最小值
                left = middle + 1;
            }
        }
        return nums[left];
    }
}