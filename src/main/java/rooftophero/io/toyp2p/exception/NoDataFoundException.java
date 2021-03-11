package rooftophero.io.toyp2p.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {
    private static final String MESSAGE = "No Data Found";

    public NoDataFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
