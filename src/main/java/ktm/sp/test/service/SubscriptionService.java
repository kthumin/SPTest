package ktm.sp.test.service;

import ktm.sp.test.exception.BusinessException;

import java.util.Collection;

/**
 * Created by kthum on 27/8/2017.
 */
public interface SubscriptionService {

    static final String ERR_BLOCKED = "ERR_BLOCKED";
    static final String ERR_NOT_FOUND= "ERR_NOT_FOUND";

    public void addSubscription(String requester, String target) throws BusinessException;

    public void blockUpdate(String requester, String target) throws BusinessException;

    public Collection<String> getAllSubscription(String sender, String text) throws BusinessException;

}
