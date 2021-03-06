package ktm.sp.test.service;

import ktm.sp.test.exception.BusinessException;

import java.util.Collection;

/**
 * Created by kthum on 26/8/2017.
 */
public interface FriendService {

    static final String ERR_BLOCKED = "ERR_BLOCKED";
    static final String ERR_NOT_FOUND= "ERR_NOT_FOUND";

    public Collection<String> getFriendList(String email);

    public void createFriends(String emailA, String emailB) throws BusinessException;

    public Collection<String> getMutualFriendList(String emailA, String emailB)throws BusinessException;
}
