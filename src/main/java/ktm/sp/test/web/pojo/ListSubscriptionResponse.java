package ktm.sp.test.web.pojo;

import java.util.Collection;

/**
 * Created by kthum on 27/8/2017.
 */
public class ListSubscriptionResponse extends Response {
    private Collection<String> recipients;
    private int count;

    public ListSubscriptionResponse(Collection<String> friends)
    {
        this.setRecipients(friends);
    }

    public Collection<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Collection<String> recipients) {
        this.recipients = recipients;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        if (recipients!=null){
            return recipients.size();
        }
       return 0;
    }

}
