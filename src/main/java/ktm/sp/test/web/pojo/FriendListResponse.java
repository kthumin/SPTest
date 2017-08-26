package ktm.sp.test.web.pojo;

import java.util.List;

/**
 * Created by kthum on 26/8/2017.
 */
public class FriendListResponse  extends Response {
    private List<String> friends;
    private int count;

    public FriendListResponse(List<String> friends)
    {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public int getCount() {
        if (friends!=null){
            return friends.size();
        }
       return 0;
    }

}
