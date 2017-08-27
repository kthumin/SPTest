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
import java.util.Set;

/**
 * Created by kthum on 26/8/2017.
 */
@Service("friendService")
public class FriendServiceImpl implements FriendService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonRepository friendRepo;


    @Override
    public Set<String> getFriendList(String email) {

        PersonEntity person= friendRepo.findOne(email);
        log.debug("Person found: {}", person.toString());
        if (person!=null)
        {
            return person.getFriends();
        }
        return null;
    }

    @Override
    public void createFriends(String emailA, String emailB) throws BusinessException {

        PersonEntity personA= friendRepo.findOne(emailA);
        PersonEntity personB= friendRepo.findOne(emailB);

        if(personA==null) {
            personA = new PersonEntity(emailA);
        }
        if(personB==null){
            personB = new PersonEntity(emailB);
        }

        if(personA.isBlocked(emailB))
        {
            String errorMsg = emailA + " has blocked " + emailB;
            throw new BusinessException(FriendService.ERR_BLOCKED, errorMsg);
        }
        if(personB.isBlocked(emailA))
        {
            String errorMsg = emailB + " has blocked " + emailA;
            throw new BusinessException(FriendService.ERR_BLOCKED, errorMsg);
        }

        personA.addFriend(personB);
        friendRepo.save(personA);
        friendRepo.save(personB);
    }

    @Override
    public Collection<String> getMutualFriendList(String emailA, String emailB) throws BusinessException {

        Set<String> mutualFriends = null;

        PersonEntity personA= friendRepo.findOne(emailA);
        PersonEntity personB= friendRepo.findOne(emailB);

        if(personA == null)
        {
            throw new BusinessException(FriendService.ERR_NOT_FOUND, emailA + " not found.");
        }
        if(personB == null)
        {
            throw new BusinessException(FriendService.ERR_NOT_FOUND, emailB + " not found.");
        }

        Set<String> aFriends= personA.getFriends();
        Set<String> bFriends= personB.getFriends();

        if(!CollectionUtils.isEmpty(aFriends) && !CollectionUtils.isEmpty(bFriends)) {
            mutualFriends = SetUtils.intersection(aFriends, bFriends).toSet();
        }
        return mutualFriends;
    }


}
