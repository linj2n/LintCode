import java.util.*;
class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        int KIndex = nums.length - k, lo = 0, hi = nums.length-1;
        int pivot = lomutoPartition(nums,lo,hi);
        while (pivot != KIndex)
        {
            if (pivot > KIndex)
                pivot = lomutoPartition(nums,lo,pivot-1);
            else
                pivot = lomutoPartition(nums,pivot+1,hi);
        }
        return nums[KIndex];
    }
    public static void exch(int[] nums, int i, int j){
        int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
    }
    public static int lomutoPartition(int[] nums, int lo, int hi){
        Random r = new Random();
        int p = lo + r.nextInt(hi - lo + 1);
        int pivotValue = nums[p];
        exch(nums,p,lo);
        
        int i = lo, j = lo + 1;
        while (j <= hi){
            if (nums[j] < pivotValue)
                exch(nums,++i,j);
            ++j;
        }
        exch(nums,i,lo);
        return i;
    }
};