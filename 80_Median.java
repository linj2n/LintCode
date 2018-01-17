public class Solution {
    /*
     * @param : A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public static void exch(int[] nums, int i, int j)
    {
        int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
    }
    public static int lomutoPartition(int[] nums, int lo, int hi){
        int pivotValue = nums[lo];
        int i = lo, j = lo + 1;
        while (j <= hi){
            if (nums[j] < pivotValue)
                exch(nums,++i,j);
            ++j;
        }
        exch(nums,i,lo);
        return i;
    }    
    public int median(int[] nums) {
        // write your code here
        int m = (nums.length - 1) / 2;
        int lo = 0, hi = nums.length - 1;
        int pivot = lomutoPartition(nums,lo,hi);
        while (pivot != m)
        {
            if (pivot > m)
                pivot = lomutoPartition(nums,lo,pivot-1);
            else
                pivot = lomutoPartition(nums,pivot+1,hi);
        }
        return nums[m];
    }
}