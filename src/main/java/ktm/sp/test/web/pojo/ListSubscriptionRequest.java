package ktm.sp.test.web.pojo;

/**
 * Created by kthum on 27/8/2017.
 */
public class ListSubscriptionRequest extends Request {

    private String sender;
    private String text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
