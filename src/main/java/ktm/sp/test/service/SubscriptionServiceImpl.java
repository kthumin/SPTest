package ktm.sp.test.service;

import ktm.sp.test.domain.PersonEntity;
import ktm.sp.test.exception.BusinessException;
import ktm.sp.test.repository.PersonRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kthum on 27/8/2017.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonRepository repo;

    @Override
    public void addSubscription(String requesterEmail, String targetEmail) throws BusinessException {
        PersonEntity requester= repo.findOne(requesterEmail);
        PersonEntity target= repo.findOne(targetEmail);

        if(requester==null) {
            throw new BusinessException(SubscriptionService.ERR_NOT_FOUND, requesterEmail +" Not Found");
        }
        if(target ==null){
            throw new BusinessException(SubscriptionService.ERR_NOT_FOUND, targetEmail +" Not Found");
        }
        target.addSubscriber(requesterEmail);
        repo.save(target);
    }

    @Override
    public void blockUpdate(String requesterEmail, String targetEmail) throws BusinessException {
        PersonEntity requester= repo.findOne(requesterEmail);
        PersonEntity target= repo.findOne(targetEmail);

        if(requester==null) {
            throw new BusinessException(SubscriptionService.ERR_NOT_FOUND, requesterEmail +" Not Found");
        }
        if(target ==null){
            throw new BusinessException(SubscriptionService.ERR_NOT_FOUND, targetEmail +" Not Found");
        }

        target.addBlocker(requesterEmail);
        repo.save(target);
    }

    private Set<String> getEmailAddresses(String text)
    {
        log.info("Text: {}", text);
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        Set<String> emails = new HashSet<String>();

        String mail = null;
        while(matcher.find()) {
            mail = matcher.group();
            log.info("Found email: {}", mail);
            emails.add(mail);
        }

        return emails;
    }

    public Collection<String> getAllSubscription(String senderEmail, String text) throws BusinessException
    {
        PersonEntity sender = repo.findOne(senderEmail);
        if(sender ==null)
        {
            throw new BusinessException(SubscriptionService.ERR_NOT_FOUND, senderEmail + " Not Found");
        }
        Set<String> friends = sender.getFriends();
        Set<String> subscribers = sender.getSubscribers();
        Set<String> mentions = this.getEmailAddresses(text.toLowerCase());

        Set<String> combined = new HashSet<String>();
        if(!CollectionUtils.isEmpty(friends) )
        {
            combined.addAll(friends);
        }
        if(!CollectionUtils.isEmpty(subscribers))
        {
            combined.addAll(subscribers);
        }
        if(!CollectionUtils.isEmpty(mentions))
        {
            combined.addAll(mentions);
        }
        //remove from list if the person has blocked the sender
        if(!CollectionUtils.isEmpty(sender.getBlockers()))
        {
            combined = SetUtils.difference(combined, sender.getBlockers());
        }

        return combined;
    }

}
