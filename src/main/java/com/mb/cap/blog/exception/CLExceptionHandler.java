package com.mb.cap.blog.exception;

import com.mb.cap.blog.utils.CommonResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CLExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest req) {
        CommonResponse<String> res = CommonResponse.fail(ErrorCode.NOT_FOUND.value, ex.getMessage(), null);
        return handleExceptionInternal(ex, res, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest req) {
        CommonResponse<String> res = CommonResponse.fail(ErrorCode.BAD_REQUEST.value, ex.getMessage(), null);
        return handleExceptionInternal(ex, res, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorizedException(RuntimeException ex, WebRequest req) {
        CommonResponse<String> res = CommonResponse.fail(ErrorCode.UNAUTHORIZED.value, ex.getMessage(), null);
        return handleExceptionInternal(ex, res, new HttpHeaders(), HttpStatus.UNAUTHORIZED, req);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest req) {
        ValidationException vex = (ValidationException) ex;
        CommonResponse<String> res = CommonResponse.fail(vex.getErrorCode().value, ex.getMessage(), null);
        return handleExceptionInternal(vex, res, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }
}
