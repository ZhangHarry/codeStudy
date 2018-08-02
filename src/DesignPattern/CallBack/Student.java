package DesignPattern.CallBack;

/**
 * Created by zhr on 2016/7/29.
 */
public class Student extends Client {
    public Student(String name){
        super(name);
        setService(new HomeWork());
    }

//    @Override
//    public void callService(int a, int b) {
//        new Server().serve(a, b, new HomeWork());
//    }


    class HomeWork implements Service {
        @Override
        public void doJob(int a, int b, int result) {
            System.out.format("%s ask for help : %d + %d = %d%n",
                    getName(), a, b, result);
        }
    }
}


