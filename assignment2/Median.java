package assignment2;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Median {
    public static int[] findMedians(int[] array) {
        int median = array[0];
        int[] result = new int[array.length];
        result[0] = median;
        MaxPQ<Integer> max_heap = new MaxPQ<>(array.length / 2);
        MinPQ<Integer> min_heap = new MinPQ<>(array.length / 2);
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            if (current >= median) {
                min_heap.insert(current);
            } else {
                max_heap.insert(current);
            }
            if (min_heap.size() - max_heap.size() >= 2) {
                max_heap.insert(median);
                median = min_heap.delMin();

            } else {
                if (max_heap.size() - min_heap.size() > 0) {
                    min_heap.insert(median);
                    median = max_heap.delMax();

                }
            }
            result[i] = median;
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改line 14, line 27的文件路径
        File input = new File("src/testing data/sample input.txt");
        if(!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int N  = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }
        int[] result = findMedians(array);
        File output = new File("src/testing data/sample output.txt");
        in = new Scanner(output);
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if(in.nextInt()!=result[i]) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);

    }
}
