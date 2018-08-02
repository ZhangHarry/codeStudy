package Tutorial.essential;

/**
 * Created by zhanghr on 2018/4/24.
 */

public class TestByteCode {
    public static void main(String[] args){
        Point p1 = new Point(1,1);
        Point p2 = new Point(2,3);
        int area = p1.area(p2);
    }

    public void  test(){
        int a = 12;
        int b = 13;
        int c =a+b;
    }

    public void test1(String[] args){
        int a = 12;
        int b = 13;
        int c =test2(a,b);
    }

    public int test2(int a, int b) {
        int result = (int)(Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0)));
        return result;
    }
}

class Point{
    int x,y;
    Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int area(Point p){
        int length = x-p.x;
        int width = y-p.y;
        return length*width;
    }
}