/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        sortNutsAndBolts(nuts,bolts,0,bolts.length - 1, compare);
        // write your code here
    }
    // pre：nuts[lo..hi]，bolts[lo..hi]内的元素未匹配。
    // post：nuts[lo..hi]，bolts[lo..hi]内的元素已匹配。
    public void sortNutsAndBolts(String[] nuts, String[] bolts, int lo, int hi, NBComparator compare)
    {
        if (lo >= hi) return;
        int pivot = partition(nuts,bolts[lo],lo,hi,compare);
        partition(bolts,nuts[pivot],lo,hi,compare);    // 交替划分量数组，返回pivot下标位置
        sortNutsAndBolts(nuts,bolts,lo,pivot-1,compare);
        sortNutsAndBolts(nuts,bolts,pivot+1,hi,compare);
    }
    
    // pre: NA
    // post: a[]中下标i、j两元素交换位置。
    public void exch(String[] a, int i, int j)
    {
        String temp = a[i]; a[i] = a[j]; a[j] = temp;
    }
    
    // pre: 未划分的字符串数组
    // post: 对于返回值j，a中的元素满足：a[lo..j-1] < a[j] < a[j+1..hi]
    // 这个partition算法实现是基于hoare版本的简要改写，虽不够简洁，但是易于理解和纸笔模拟，也可以使用其他的版本实现。
    public int partition(String[] a, String pValue, int lo, int hi, NBComparator compare)
    {
        // 找出与pValue匹配的元素，调整到lo位置上
        for (int i = lo; i <= hi; i++)
        {
            if (compare.cmp(a[i],pValue) == 0 || compare.cmp(pValue,a[i]) == 0)
            {
                exch(a,lo,i); break;
            }
        }
        // 只含有两个元素时，单独处理
        if (hi - lo == 1)
        {
            if (compare.cmp(a[hi],pValue) == -1 || compare.cmp(pValue,a[hi]) == 1)
            {
                exch(a,lo,hi); return hi;
            }else
                return lo;
        }
        
        // 以a[lo] 作为pivot元，划分数组
        int i = lo + 1, j = hi;
        while (true)
        {   
            // pValue与a[i]匹配，移动i下标，a[i] > a[lo]时停止。
            while (true)
            {
                if (i >= hi || (compare.cmp(a[i],pValue) == 1 || compare.cmp(pValue,a[i]) == -1))
                    break;
                else
                    ++i;
            }
            
            // pValue与a[j]匹配，移动i下标，a[j] < a[lo]时停止。
            while (true)
            {
                if (j <= lo || (compare.cmp(a[j],pValue) == -1 || compare.cmp(pValue,a[j]) == 1))
                    break;
                else
                    --j;
            }
            // i与j不交叉或相遇时，交换a[i]与a[j]
            if (i >= j)
                break;
            else
                exch(a,i,j);
        }
        exch(a,lo,j);   // 将a[lo]移动到中轴pivot位置，划分完成
        return j;
    }

};