package toy.crawle;

/**
 * Created by zhanghr on 2017/4/16.
 */
public class Car {
    String company, brand, productName, typeNo, cp_month, cp_id;
    int  index;
    String string(){
        String s = String.format("%d,%s,%s,%s,%s,%s,%s%n",
                index, company, brand, productName, typeNo, cp_month, cp_id);
        System.out.print(s);
        return s;
    }
}
