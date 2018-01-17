class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public static void exch(int[] nums, int i, int j)
    {
        int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
    }
    public static int lomutoPartition(int[] nums, int lo, int hi){
        Random r = new Random();
        int p = lo + r.nextInt(hi - lo + 1);
        int pivotValue = nums[p];
        exch(nums,lo,p);
        int i = lo, j = lo + 1;
        while (j <= hi){
            if (nums[j] < pivotValue)
                exch(nums,++i,j);
            ++j;
        }
        exch(nums,i,lo);
        return i;
    }
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        int seqKIndex = nums.length - k, lo = 0, hi = nums.length-1;
        int pivot = lomutoPartition(nums,lo,hi);
        while (pivot != seqKIndex){
            if (pivot > seqKIndex){
                hi = pivot - 1;
            }else{
                lo = pivot + 1;
            }
            pivot = lomutoPartition(nums,lo,hi);        
        }
        return nums[seqKIndex];
    }
};