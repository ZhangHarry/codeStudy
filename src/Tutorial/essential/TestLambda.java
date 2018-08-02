package Tutorial.essential;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhanghr on 2018/5/6.
 */

public class TestLambda {
    public static void main(String[] args){
        //Old way:
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        int sum = 0;
        for(Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        System.out.println(sum);

        //New way:
        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7);
        int sum2 = list2.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
        System.out.println(sum2);
    }
}
