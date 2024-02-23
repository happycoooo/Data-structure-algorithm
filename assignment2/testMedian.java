package assignment2;

import java.util.Arrays;
import java.util.Random;

public class testMedian {
    public static void sort(int[] a){
        int N = a.length;
        for(int i = 1; i < N ; i++ ){
            for(int j = i; j > 0 ; j--){
                if( a[j] < a[j-1] ){
                    exch(a,j,j-1);
                }else break;
            }
        }
    }

    private static void exch(int[] a, int i , int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isEqual(int[] array1, int[] array2){
        for(int i = 0; i < array1.length; i++){
            if(array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Median median = new Median();
        Random rand  = new Random();
        int[] input = new int[1000];
        int[] medianNum = new int[1000];
        for(int i = 0 ; i < input.length; i++){
            input[i] = rand.nextInt();
        }
        for(int i = 0; i < input.length ; i++){
            int[] num = new int[i+1];
            for(int j = 0 ; j < num.length; j++){
                num[j] = input[j];
            }
            sort(num);
            if(i % 2 == 0){
                medianNum[i] = num[i/2];
            }else{
                medianNum[i] = num[(i-1)/2];
            }
        }
        System.out.println(isEqual(median.findMedians(input), medianNum));
    }
}
