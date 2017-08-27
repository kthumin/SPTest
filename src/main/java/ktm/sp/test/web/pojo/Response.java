package ktm.sp.test.web.pojo;

/**
 * Created by kthum on 26/8/2017.
 */
public class Response
{
    private String responseCode;
    private String responseDesc;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {

        return responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }
}
