package ktm.sp.test.web;

import ktm.sp.test.exception.BusinessException;
import ktm.sp.test.service.SubscriptionService;
import ktm.sp.test.web.pojo.ListSubscriptionRequest;
import ktm.sp.test.web.pojo.ListSubscriptionResponse;
import ktm.sp.test.web.pojo.Response;
import ktm.sp.test.web.pojo.SubscribeRequest;
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
 * Created by kthum on 27/8/2017.
 */
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SubscriptionService service;

    @RequestMapping(path = "/add", method = RequestMethod.PUT)
    public ResponseEntity<Response> addFriend(@RequestBody @Valid SubscribeRequest request) throws BusinessException {

        service.addSubscription(request.getRequestor(), request.getTarget());

        Response body = new Response();
        body.setSuccess(true);
        return new ResponseEntity<Response>(body, HttpStatus.OK);
    }

    @RequestMapping(path = "/block", method = RequestMethod.PUT)
    public ResponseEntity<Response> blockUpdate(@RequestBody @Valid SubscribeRequest request) throws BusinessException{
        service.blockUpdate(request.getRequestor(), request.getTarget());

        Response body = new Response();
        body.setSuccess(true);
        return new ResponseEntity<Response>(body, HttpStatus.OK);
    }

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseEntity<ListSubscriptionResponse> listSubscription(@RequestBody @Valid ListSubscriptionRequest request) throws BusinessException {

        Collection<String> subList = service.getAllSubscription(request.getSender(), request.getText());

        ListSubscriptionResponse body = new ListSubscriptionResponse(subList);
        body.setSuccess(true);
        return new ResponseEntity<ListSubscriptionResponse>(body, HttpStatus.OK);
    }


}
