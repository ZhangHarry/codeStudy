package DesignPattern.DecoratorPattern;

/**
 * Created by Zhanghr on 2016/4/6.
 */
abstract public class RoomDecorator implements Room{
    protected Room roomToBeDecorated;

    public RoomDecorator(Room roomToBeDecorated){
        this.roomToBeDecorated = roomToBeDecorated;
    }

    @Override
    public String showRoom(){
        return roomToBeDecorated.showRoom();
    }
}
