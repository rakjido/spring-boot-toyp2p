package rooftophero.io.toyp2p.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rooftophero.io.toyp2p.api.form.ResponseFormat;
import rooftophero.io.toyp2p.api.form.ResponseListFormat;
import rooftophero.io.toyp2p.api.form.ResponseMessage;
import rooftophero.io.toyp2p.api.form.StatusCode;
import rooftophero.io.toyp2p.service.AccountService;
import rooftophero.io.toyp2p.service.dto.request.AccountRequestDto;
import rooftophero.io.toyp2p.service.dto.request.AccountUpdateResponseDto;
import rooftophero.io.toyp2p.service.dto.response.AccountResponseDto;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"2. Account"})
@RequiredArgsConstructor
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @ApiOperation(value = "Account 추가", notes = "Account를 추가한다.")
    @PostMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> addAccount(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        AccountResponseDto addedAccountResponseDto = accountService.addAccount(accountRequestDto);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.CREATED, ResponseMessage.CREATED_ACCOUNT, addedAccountResponseDto), Headers(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Account 전체조회", notes = "전체 Account를 조회한다.")
    @GetMapping("/api/accounts")
    public ResponseEntity<ResponseListFormat> getAccountList() {
        List<AccountResponseDto> accountList = accountService.getAccountList();
        return new ResponseEntity<>(ResponseListFormat.of(StatusCode.OK, ResponseMessage.GET_ALL_ACCOUNTS, accountList), Headers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Account 조회", notes = "특정 ID의 Account를 조회한다.")
    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> getAccount(@PathVariable Long id) {
        AccountResponseDto accountDto = accountService.getAccount(id);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.GET_ACCOUNT, accountDto),
                Headers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Account 업데이트", notes = "특정한 Account를 업데이트한다.")
    @PutMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> updateAccount(@RequestBody @Valid AccountUpdateResponseDto accountDto) {
        AccountResponseDto updatedAccountDto = accountService.updateAccount(accountDto);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.UPDATED_ACCOUNT, updatedAccountDto),
                Headers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Account 삭제", notes = "특정 ID의 Account를 삭제한다.")
//    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.DELETED_ACCOUNT,"account deleted"),
                Headers(), HttpStatus.OK);
    }

    // TODO refactoring headers()
    private HttpHeaders Headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer [token]");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
        return headers;
    }
}
