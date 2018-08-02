package DesignPattern.DecoratorPattern;

/**
 * Created by Zhanghr on 2016/4/6.
 */
public class TestClient {
    public static void main(String[] args) {
        // 毛胚房
        Room blankRoom = new BlankRoom();

        // 刷了墙的毛胚房
        Room paintedRoom = new PaintedDecorator(new BlankRoom());

        // 先刷墙再铺地板的毛胚房
        // 注意到连续的 new 操作，这就是 wrapper，最内层的一般是毛胚房
        Room paintedAndFlooredRoom = new FlooredDecorator(new
                PaintedDecorator(new BlankRoom()));

        // 先铺地板再刷墙的毛胚房
        Room flooredAndPaintedRoom = new PaintedDecorator(new
                FlooredDecorator(new BlankRoom()));

        System.out.println(blankRoom.showRoom());
        System.out.println(paintedRoom.showRoom());
        System.out.println(paintedAndFlooredRoom.showRoom());
        System.out.println(flooredAndPaintedRoom.showRoom());
    }
}
