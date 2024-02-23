package assignment1;
import java.util.*;

import static java.lang.Math.pow;

public class Cards {
    /**
     * Using the order the cards are destroyed, calculate the initial order of the cards.
     * @param destroyOrder the order the cards are destroyed.
     * @return the initial order
     */
    public static int[] calculateInitCards( int[] destroyOrder ) {
        int number = destroyOrder.length;
        int[] result = new int[number];
        ArrayList<Integer> n_l = new ArrayList<>();
        ArrayList<Integer> n_d = new ArrayList<>();
        n_d.add(0);
        n_l.add(number);
        int cycle = 0;
        int mark = 0;
        //number is even

        n_d.add((number+1)/2);
        n_l.add(n_l.get(0)-n_d.get(1));
        cycle += 1;
        for(int i = 0 ; i < n_d.get(1) ; i++ ){
            result[(int) pow(2, cycle) * i] = destroyOrder[i];
        }

        while(n_l.get(n_l.size()-1) >=1) {
            ArrayList<Integer> position = new ArrayList<>();
            if (judge(n_l)) {

                if (n_l.get(cycle) == 1) {
                    n_d.add(1);
                    n_l.add(0);
                    cycle += 1;
                    for (int i = 0; i < number; i++) {
                        if (result[i] == 0) {
                            result[i] = destroyOrder[number - 1];
                        }
                    }
                } else {
                    n_d.add((n_l.get(cycle) + 1) / 2);
                    n_l.add(n_l.get(cycle) - n_d.get(cycle + 1));
                    mark += n_d.get(cycle);
                    cycle += 1;
                    for(int i = 0; i < number ; i++){
                        if(result[i] == 0){
                            position.add(i);
                        }
                    }

                    for(int i = 0 ; i < n_d.get(cycle) ; i++){
                        result[position.get(i*2)] = destroyOrder[i+mark];
                    }
                }
            }else {
                if (n_l.get(cycle)%2 == 0){
                    n_d.add(n_l.get(cycle)/2);
                    n_l.add(n_l.get(cycle) - n_d.get(cycle+1));
                    mark += n_d.get(cycle);
                    cycle += 1;

                    for(int i = 0; i<number;i++){
                        if(result[i] == 0){
                            position.add(i);
                        }
                    }

                    for(int i = 0 ; i < n_d.get(cycle) ; i++){
                        result[position.get(1+i*2)] = destroyOrder[i+mark];
                    }
                }else{
                    if (n_l.get(cycle)==1){
                        n_d.add(1);
                        n_l.add(0);
                        cycle += 1;
                        for(int i = 0; i < number ; i++ ){
                            if(result[i] == 0){
                                result[i] = destroyOrder[number-1];
                            }
                        }
                    }else{
                        n_d.add((n_l.get(cycle) - 1) / 2);
                        n_l.add(n_l.get(cycle) - n_d.get(cycle + 1));
                        mark += n_d.get(cycle);
                        cycle += 1;

                        for(int i = 0; i < number ; i++){
                            if(result[i] == 0){
                                position.add(i);
                            }
                        }

                        for(int i = 0 ; i < n_d.get(cycle) ; i++){
                            result[position.get(1+i*2)] = destroyOrder[i+mark];
                        }
                    }
                }

            }
        }
        return result;
    }


    public static boolean judge( ArrayList n_l ) {
        int mark = 0;
        for (int i = 0; i < n_l.size()-1 ; i++) {
            int k = (int) n_l.get(i);
            if((k % 2) == 1)  mark += 1;
            else mark += 2;
        }
        return mark % 2 == 0;
    }

    public static int[] destroyOrder( int[] InitCards ) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> destroyOrder = new ArrayList<>();
        int[] result = new int[InitCards.length];
        for(int i = 0 ; i < InitCards.length; i++ ){
            queue.add(InitCards[i]);
        }
        int counter = 0;
        while(queue.size()>=1){
            if(queue.size() == 1){
                destroyOrder.add(queue.peek());
                queue.poll();
            }
            else if(counter % 2  == 0){
                destroyOrder.add(queue.peek());
                queue.poll();
                counter += 1;
            }else{
                queue.add(queue.peek());
                queue.poll();
                counter += 1;
            }
        }
        for(int i = 0 ; i < InitCards.length; i++){
            result[i] = destroyOrder.get(i);
        }
        return result;

    }

    public static boolean isEqual(int[] input, int[] output) {
        for (int i = 0; i < input.length; i++){
            if(input[i] != output[i]){
                return false;
            }
            else{
                continue;
            }
        }
        return true;
    }


    public static void main( String[] args ) {
        Random rand  = new Random();
        int[] input = new int[10000];
        for(int i = 0 ; i < input.length; i++){
            input[i] = rand.nextInt();
        }
            int[] initOrder = calculateInitCards(input);
            int[] result = destroyOrder(initOrder);
            System.out.println(isEqual(result,input));
    }
}