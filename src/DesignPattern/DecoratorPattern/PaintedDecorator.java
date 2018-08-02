package DesignPattern.DecoratorPattern;

/**
 * Created by Zhanghr on 2016/4/6.
 */
public class PaintedDecorator extends RoomDecorator {
    public PaintedDecorator(Room roomToBeDecorated) {
        super(roomToBeDecorated);
    }

    public String showRoom(){
        doPainting();
        return super.showRoom()+"刷油漆";
    }

    private void doPainting(){

    }
}
