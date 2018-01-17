public class Solution {
    /*
     * @param : A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public static void exch(int[] nums, int i, int j)
    {
        int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
    }
    public static int partition(int[] nums, int lo, int hi)
    {
        int pValue = nums[lo];
        int i = lo,j = lo + 1;
        while (j <= hi)
        {
            if (nums[j] < pValue)
            {
                exch(nums,++i,j);
            }
            ++j;
        }
        exch(nums,lo,i);
        return i;
    }
    public int median(int[] nums) {
        // write your code here
        int n = nums.length;
        int m = (n % 2 == 0 ? n/2 - 1 : n / 2);
        int lo = 0, hi = nums.length - 1;
        int pivot = partition(nums,lo,hi);
        while (pivot != m)
        {
            if (pivot > m)
                pivot = partition(nums,lo,pivot-1);
            else
                pivot = partition(nums,pivot+1,hi);
        }
        return nums[m];
    }
}