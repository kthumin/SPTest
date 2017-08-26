package ktm.sp.test.web;

import ktm.sp.test.service.FriendService;
import ktm.sp.test.web.pojo.FriendListRequest;
import ktm.sp.test.web.pojo.FriendListResponse;
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
import java.util.List;

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
    public ResponseEntity<FriendListResponse> getFriendList(@RequestBody @Valid FriendListRequest request)
    {
        log.trace("get Friend List");

        //TODO validate request
        List<String> friendList = service.getFriendList(request.getEmail());

        FriendListResponse body = new FriendListResponse(friendList);
        body.setSuccess(true);
        return new ResponseEntity<FriendListResponse>(body, HttpStatus.OK );
    }
}
