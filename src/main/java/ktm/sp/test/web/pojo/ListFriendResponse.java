package ktm.sp.test.web.pojo;

import java.util.Collection;

/**
 * Created by kthum on 26/8/2017.
 */
public class ListFriendResponse extends Response {
    private Collection<String> friends;
    private int count;

    public ListFriendResponse(Collection<String> friends)
    {
        this.setFriends(friends);
    }

    public Collection<String> getFriends() {
        return friends;
    }

    public void setFriends(Collection<String> friends) {
        this.friends = friends;
    }

    public int getCount() {
        if (friends!=null){
            return friends.size();
        }
       return 0;
    }

}
