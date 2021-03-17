package rooftophero.io.toyp2p.api.form;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import rooftophero.io.toyp2p.exception.NoDataFoundException;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseFormat> ResponseExceptionHandler(NoDataFoundException e) {
        return new ResponseEntity(ResponseFormat.of(StatusCode.NOT_FOUND, ResponseMessage.NO_DATA_FOUND, "No data"),
                Headers(), HttpStatus.NOT_FOUND);
    }

    // TODO 400 Bad Request (AccountRequestDto format)

    private HttpHeaders Headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer [token]");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
        return headers;
    }

}
