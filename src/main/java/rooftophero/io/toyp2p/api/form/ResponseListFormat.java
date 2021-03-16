package rooftophero.io.toyp2p.api.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseListFormat<T> {
    private int statusCode;
    private String message;
    private List<T> list;

    public static<T> ResponseListFormat of(final int statusCode, final String message, final List<T> list) {
        return ResponseListFormat.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .list(list)
                .build();
    }

}
