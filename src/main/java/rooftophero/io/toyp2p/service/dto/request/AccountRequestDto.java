package rooftophero.io.toyp2p.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequestDto {
    private String bankName;

    private String accountNumber;
}
