package DesignPattern.DecoratorPattern;

/**
 * Created by Zhanghr on 2016/4/6.
 */
public class BlankRoom implements Room {
    @Override
    public String showRoom() {
        return "毛坯房";
    }
}
