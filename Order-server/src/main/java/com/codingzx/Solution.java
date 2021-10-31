package com.codingzx;



import java.util.ArrayList;
import java.util.List;

/**
 * @author codingzx
 * @description  东方财富
 * @date 2021/8/21 9:38
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param numbers int整型一维数组
     * @param k       int整型
     * @return int整型一维数组
     */
    public static Object[] calSlidingWindowsArr(int[] numbers, int k) {
//        if(numbers.length<k){
//            return compareArray(0,numbers.length,numbers);
//        }

        List<Integer> resultList = new ArrayList<>();
        int i=0;
        for (int length = numbers.length; i <= length - k; i++) {
            resultList.add(compareArray(i, i + k, numbers));
        }
        Integer[] result = resultList.toArray(new Integer[resultList.size()]);
        i=0;
//        for (Integer n : resultList) {
//            result[i++] = n;
//        }
        return  result;
    }

    public static void main(String[] args) {
        int a[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        calSlidingWindowsArr(a, k);
        System.out.println("123");
    }


    /**
     * 得到传入区间中的最大值
     *
     * @param i
     * @param j
     * @param numbers
     * @return
     */
    public static int compareArray(int i, int j, int[] numbers) {

        int max = 0;
        for (int k = i; k < j; k++) {
            max = max < numbers[k] ? numbers[k] : max;
        }
        return max;
    }


}