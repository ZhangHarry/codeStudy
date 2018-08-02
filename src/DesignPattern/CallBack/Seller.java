package DesignPattern.CallBack;

/**
 * Created by zhr on 2016/7/29.
 */
public class Seller extends Client {
    public Seller(String name) {
        super(name);
        setService(new DebetWork());
    }

//    @Override
//    public void callService(int a, int b) {
//        new Server().serve(a, b, new DebetWork());
//    }

    class DebetWork implements Service {
        @Override
        public void doJob(int a, int b, int result) {
            System.out.format("%s ask for help : %d + %d = %d%n ",
                    getName(), a, b, result);
        }
    }
}
