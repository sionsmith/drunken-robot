package drunken.me.web.exception;

/**
 * Created by sionsmith on 9/12/14.
 */
public class CarNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public CarNotFoundException(Exception e) {
        super(e);
    }

    public CarNotFoundException(String reason, Exception e) {
        super(reason,e);
    }

    public CarNotFoundException(String reason) {
        super(reason);
    }
}