package DesignPattern.CallBack;

/**
 * Created by zhr on 2016/7/29.
 */
public class Server {
    public void serve(int a, int b, Service service){
        int result = a+b;
        service.doJob(a, b, result);
    }
}
