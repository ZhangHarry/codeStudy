package DesignPattern.DecoratorPattern;

/**
 * Created by Zhanghr on 2016/4/6.
 */
public class FlooredDecorator extends RoomDecorator {
    public FlooredDecorator(Room roomToBeDecorated) {
        super(roomToBeDecorated);
    }

    public String showRoom(){
        doFlooring();
        return super.showRoom()+"铺地板";
    }

    private void doFlooring() {

    }
}
