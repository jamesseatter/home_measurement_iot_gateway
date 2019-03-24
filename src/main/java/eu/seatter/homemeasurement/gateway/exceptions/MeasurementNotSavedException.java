package eu.seatter.homemeasurement.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 24/01/2019
 * Time: 22:19
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MeasurementNotSavedException  extends RuntimeException {
    private String detail;

    public MeasurementNotSavedException(String message, String detail) {
        super(message);
        this.detail = detail;
    }

    String getDetail() {
        return detail;
    }
}
