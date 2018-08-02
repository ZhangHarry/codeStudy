package toy.json.exception;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class WrongFormatException extends Exception {
    private String message;
    public WrongFormatException(String msg){
        this.message = msg;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
