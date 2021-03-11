package rooftophero.io.toyp2p.service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponseDto {
    private Long id;

    private String bankName;
    private String accountNumber;

}
