package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 连续的子数组和
 * [数学]
 * 
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *      子数组大小 至少为 2 ，且子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
 * 示例 1：
 *      输入：nums = [23,2,4,6,7], k = 6
 *      输出：true
 *      解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 *      输入：nums = [23,2,6,4,7], k = 6
 *      输出：true
 *      解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 *      42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 *      输入：nums = [23,2,6,4,7], k = 13
 *      输出：false
 * 提示：
 *      1 <= nums.length <= 10^5
 *      0 <= nums[i] <= 10^9
 *      0 <= sum(nums[i]) <= 2^31 - 1
 *      1 <= k <= 2^31 - 1
 *
 * @author ywh
 * @since 20/04/2020
 */
public class LeetCode523 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumBruteForce(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int sum = nums[i], j = i + 1; j < n; j++) {
                sum += nums[j];
                if ((k == 0 && sum == 0) || k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 同余：当两个整数除以同一个数字，得到的余数相同：a % k == b % k；
     * 如果 a 与 b 同余，则 a - b == m * k
     * s[i] - s[j] 表示 nums[j+1] ~ nums[i] 之和
     *
     * Time: O(n), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumMod(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        // key：前缀和对 k 取模的结果
        // value：前缀和的右边界下标（由于数组长度至少为 2，因此需要判断两个右边界之差是否大于 1）
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = k == 0 ? sum : sum % k;
            Integer j = map.get(mod);
            if (j != null) {
                if (i - j > 1) {
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }
        return false;
    }
}
