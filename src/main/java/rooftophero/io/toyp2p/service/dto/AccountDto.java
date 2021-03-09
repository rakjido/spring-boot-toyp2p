package rooftophero.io.toyp2p.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private Long id;

    private String bankName;
    private String accountNumber;

}
