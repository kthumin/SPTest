package ktm.sp.test.exception;

/**
 * Created by kthum on 26/8/2017.
 */
public class BusinessException extends Exception {

    private String errorCode;
    private String errorDesc;

    public BusinessException(){}

    public BusinessException(String errorCode, String errorDesc)
    {
        this.setErrorCode(errorCode);
        this.setErrorDesc(errorDesc);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
