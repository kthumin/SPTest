package ktm.sp.test.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by kthum on 26/8/2017.
 */
@DynamoDBTable(tableName = "Person")
public class PersonEntity implements Serializable
{
    private String email;
    private String name;
    private Set<String> friends;
    //people I subscribe to.
    private Set<String> subscribers;
    //people who blocked me
    private Set<String> blockers;

    public PersonEntity()
    {}

    public PersonEntity(String email){
        this.setEmail(email);
    }

    public boolean addFriend(PersonEntity friend)
    {
        Set<String> friendList = this.getFriends();
        if(friendList==null) friendList = new HashSet<String>();
        friendList.add(friend.getEmail());

        Set<String> hisFriendList = friend.getFriends();
        if(hisFriendList==null) hisFriendList = new HashSet<String>();
        hisFriendList.add(this.getEmail());

        this.setFriends(friendList);
        friend.setFriends(hisFriendList);
        return true;
    }

    public boolean isBlocked(String email)
    {
        if(!CollectionUtils.isEmpty(this.getBlockers()))
        {
            Set<String> blockers = this.getBlockers();
            return blockers.contains(email.toLowerCase());
        }
        return false;
    }

    public boolean addSubscriber(String requester)
    {
        Set<String> subscribers = this.getSubscribers();
        if(subscribers==null)
        {
            subscribers = new HashSet<String>();
        }
        subscribers.add(requester.toLowerCase());

        this.setSubscribers(subscribers);
        return true;
    }

    public void removeSubscriber(String targetEmail)
    {
        Set<String> subscribers = this.getSubscribers();
        if(!CollectionUtils.isEmpty(subscribers))
        {
            subscribers.remove(targetEmail.toLowerCase());
            this.setSubscribers(subscribers);
        }
    }

    public boolean addBlocker(String targetEmail)
    {
        Set<String> blockers = this.getBlockers();
        if(blockers==null)
        {
            blockers = new HashSet<String>();
        }
        blockers.add(targetEmail.toLowerCase());

        this.setBlockers(blockers);

        return true;
    }

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if(this.email!=null)
        {
            //save all email as lower case, to avoid case sensitivity error
            this.email = this.email.toLowerCase();
        }
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    @DynamoDBAttribute
    public Set<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<String> subscribers) {
        this.subscribers = subscribers;
    }

    @DynamoDBAttribute
    public Set<String> getBlockers() {
        return blockers;
    }

    public void setBlockers(Set<String> blockers) {
        this.blockers = blockers;
    }

    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

}
