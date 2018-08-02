package DesignPattern.CallBack;

/**
 * Created by zhr on 2016/7/29.
 */
public class Client {

    private String name = null;
    private Service service = null;

    public Client(String name){
        this.name = name;
    }
    public void setName(String nName){
        this.name = nName;
    }

    public String getName() {
        return name;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void callService(int a, int b){
        new Server().serve(a, b, service);
    }
}
