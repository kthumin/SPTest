package ktm.sp.test.web;

import ktm.sp.test.exception.BusinessException;
import ktm.sp.test.web.pojo.Response;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by kthum on 27/8/2017.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public ResponseEntity<Response> handleException(BusinessException be)
    {
        log.info("having exception in controller");
        log.info("Exception is : {}", ReflectionToStringBuilder.toString(be));
        Response response = new Response();
        response.setSuccess(false);
        response.setResponseCode(be.getErrorCode());
        response.setResponseDesc(be.getErrorDesc());
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(response);
    }

}
