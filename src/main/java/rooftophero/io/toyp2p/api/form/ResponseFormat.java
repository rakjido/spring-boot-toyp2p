package rooftophero.io.toyp2p.api.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFormat<T> {
    private int statusCode;
    private String message;
    private T data;

    public static<T> ResponseFormat<T> of(final int statusCode, final String message, final T data) {
        return ResponseFormat.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }
}
