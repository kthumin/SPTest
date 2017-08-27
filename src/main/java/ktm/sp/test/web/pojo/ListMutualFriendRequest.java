package ktm.sp.test.web.pojo;

import java.util.List;

/**
 * Created by kthum on 26/8/2017.
 */
public class ListMutualFriendRequest extends Request {

    private List<String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
