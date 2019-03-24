package eu.seatter.homemeasurement.gateway.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 14/01/2019
 * Time: 16:00
 * Comment: Taken from https://www.javadevjournal.com/spring/exception-handling-for-rest-with-spring/
 */
@Getter
class ApiErrorResponse {

    private HttpStatus status;
    private String error_code;
    private String message;
    private String detail;

    // getter and setters
    //Builder
    public static final class ApiErrorResponseBuilder {
        private HttpStatus status;
        private String error_code;
        private String message;
        private String detail;

        ApiErrorResponseBuilder() {
        }

        public static ApiErrorResponseBuilder anApiErrorResponse() {
            return new ApiErrorResponseBuilder();
        }

        ApiErrorResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        ApiErrorResponseBuilder withError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        ApiErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        ApiErrorResponseBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.error_code = this.error_code;
            apiErrorResponse.detail = this.detail;
            apiErrorResponse.message = this.message;
            return apiErrorResponse;
        }
    }
}