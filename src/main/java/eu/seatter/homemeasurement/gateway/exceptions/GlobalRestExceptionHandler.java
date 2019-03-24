package eu.seatter.homemeasurement.gateway.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 14/01/2019
 * Time: 16:03
 * Comment: Taken from https://www.javadevjournal.com/spring/exception-handling-for-rest-with-spring/
 */
@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void defaultExceptionHandler() {
        // Nothing to do

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DeviceNotFoundException.class})
    protected ResponseEntity<Object> handleDeviceNotFoundException(DeviceNotFoundException ex) {
        return getObjectResponseEntity(ex.getLocalizedMessage(), ex.getDetail(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({SensorNotFoundException.class})
    protected ResponseEntity<Object> handleSensorNotFoundException(SensorNotFoundException ex) {
        return getObjectResponseEntity(ex.getLocalizedMessage(), ex.getDetail(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({MeasurementNotSavedException.class})
    protected ResponseEntity<Object> handleMeasurementNotSavedException(MeasurementNotSavedException ex) {
        return getObjectResponseEntity(ex.getLocalizedMessage(), ex.getDetail(), ex);
    }

    private ResponseEntity<Object> getObjectResponseEntity(String localizedMessage, String detail, RuntimeException ex) {
        log.error("Exception:" + localizedMessage);
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withError_code(HttpStatus.NOT_FOUND.name())
                .withMessage(localizedMessage)
                .withDetail(detail)
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }


    ////////////////// SPRING EXCEPTIONS //////////////////


    @Override
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        log.error("Method Argument Not Valid:" + ex.getLocalizedMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code(HttpStatus.BAD_REQUEST.name())
                .withMessage("Input validation failed")
                .withDetail(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }


//    @Override
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.error("HTTP Method Not Readable:" + ex.getLocalizedMessage());
//        String error = "Malformed JSON request";
//        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
//                .withStatus(status)
//                .withError_code("BAD_DATA")
//                .withMessage(ex.getLocalizedMessage())
//                .withDetail(error+ex.getMessage())
//                .build();
//        return new ResponseEntity<>(response, response.getStatus());
//    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("HTTP Request Method Argument Not Supported:" + ex.getLocalizedMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code(HttpStatus.METHOD_NOT_ALLOWED.name())
                .withMessage(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("HTTP Media Type Not Supported:" + ex.getLocalizedMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())
                .withMessage(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Type Mismatch:" + ex.getLocalizedMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code("Type Missmatch")
                .withMessage(ex.getLocalizedMessage()).build();

        return new ResponseEntity<>(response, response.getStatus());
    }
}
