package eu.seatter.homeheating.edge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 14/01/2019
 * Time: 11:37
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SensorNotFoundException extends RuntimeException {
    private String detail;

    public SensorNotFoundException(String message, String detail) {
        super(message);
        this.detail = detail;
    }

    String getDetail() {
        return detail;
    }
}
