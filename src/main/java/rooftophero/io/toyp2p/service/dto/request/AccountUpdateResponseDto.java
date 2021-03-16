package rooftophero.io.toyp2p.service.dto.request;


import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountUpdateResponseDto {
    private Long id;

    @ApiParam(value = "Bank Name", required = true)
    @NotBlank
    private String bankName;

    @ApiParam(value = "Account Number", required = true)
    @NotBlank
    private String accountNumber;
}
