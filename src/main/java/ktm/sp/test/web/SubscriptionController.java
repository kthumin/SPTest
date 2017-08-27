package ktm.sp.test.web;

import ktm.sp.test.exception.BusinessException;
import ktm.sp.test.service.FriendService;
import ktm.sp.test.web.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by kthum on 26/8/2017.
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FriendService service;

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseEntity<ListFriendResponse> getFriendList(@RequestBody @Valid ListFriendRequest request)
    {
        log.trace("get Friend List");

        //TODO validate request
        Collection<String> friendList = service.getFriendList(request.getEmail());

        ListFriendResponse body = new ListFriendResponse(friendList);
        body.setSuccess(true);
        return new ResponseEntity<ListFriendResponse>(body, HttpStatus.OK );
    }


    @RequestMapping(path = "/add", method = RequestMethod.PUT)
    public ResponseEntity<Response> addFriend(@RequestBody @Valid AddFriendRequest request)
    {
        log.trace("addFriend");

        try {
            service.createFriends(request.getFriends().get(0), request.getFriends().get(1));
        } catch (BusinessException e) {
            e.printStackTrace();
            //TODO handle exception
        }

        Response body = new Response();
        body.setSuccess(true);
        return new ResponseEntity<Response>(body, HttpStatus.OK );
    }

    @RequestMapping(path = "/mutual", method = RequestMethod.POST)
    public ResponseEntity<ListFriendResponse> getMutualFriendList(@RequestBody @Valid ListMutualFriendRequest request)
    {
        //TODO validate request
        Collection<String> mutualFriendList = null;
        try
        {
            mutualFriendList = service.getMutualFriendList(request.getFriends().get(0), request.getFriends().get(1));
        } catch (BusinessException e)
        {
            e.printStackTrace();
            //TODO handle exception
        }

        ListFriendResponse body = new ListFriendResponse(mutualFriendList);
        body.setSuccess(true);
        return new ResponseEntity<ListFriendResponse>(body, HttpStatus.OK );
    }
}
