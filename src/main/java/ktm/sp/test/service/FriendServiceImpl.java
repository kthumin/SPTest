package ktm.sp.test.service;

import ktm.sp.test.domain.PersonEntity;
import ktm.sp.test.repository.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kthum on 26/8/2017.
 */
@Service("friendService")
public class FriendServiceImpl implements FriendService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FriendRepository friendRepo;

    @Override
    public List<String> getFriendList(String email) {
        List<String> friendList = new ArrayList<>();
        friendList.add("test@exampl.com");
        friendList.add("test2@exmaple.com");

        Iterable<PersonEntity> personList = friendRepo.findAll();
        for(PersonEntity person: personList)
        {
            log.info(person.toString());
        }

        return friendList;
    }
}
