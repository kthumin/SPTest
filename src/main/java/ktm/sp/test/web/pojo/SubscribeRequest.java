package ktm.sp.test.web.pojo;

/**
 * Created by kthum on 27/8/2017.
 */
public class SubscribeRequest extends Request{
    private String requestor;
    private String target;

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
