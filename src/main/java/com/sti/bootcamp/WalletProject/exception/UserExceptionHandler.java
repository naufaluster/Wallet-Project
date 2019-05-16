package com.sti.bootcamp.WalletProject.exception;

import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    @SuppressWarnings("rawTypes")
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<CommonResponse> resp(UserException e){
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

    @SuppressWarnings("rawTypes")
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<CommonResponse> catchNotFound(NotFoundException e){
        LOGGER.info("catchEntityNotFound");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

    @SuppressWarnings("rawTypes")
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse> catchHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        LOGGER.info("catchEntityNotFound");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse("405", "Request mehtod not supported"), HttpStatus.OK);
    }

    @SuppressWarnings("rawTypes")
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse> catchException(Exception e){
        LOGGER.info("catchEntityNotFound");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse("500", "Exception found"), HttpStatus.OK);
    }

}