package rooftophero.io.toyp2p.service.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequestDto {
    @ApiParam(value = "Bank Name", required = true)
    private String bankName;

    @ApiParam(value = "Account Number", required = true)
    private String accountNumber;
}
