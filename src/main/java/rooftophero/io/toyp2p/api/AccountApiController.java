package rooftophero.io.toyp2p.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rooftophero.io.toyp2p.api.form.ResponseFormat;
import rooftophero.io.toyp2p.api.form.ResponseMessage;
import rooftophero.io.toyp2p.api.form.StatusCode;
import rooftophero.io.toyp2p.service.AccountService;
import rooftophero.io.toyp2p.service.dto.request.AccountRequestDto;
import rooftophero.io.toyp2p.service.dto.request.AccountUpdateResponseDto;
import rooftophero.io.toyp2p.service.dto.response.AccountResponseDto;

@RequiredArgsConstructor
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> addAccount(@RequestBody AccountRequestDto accountRequestDto) {
        AccountResponseDto addedAccountResponseDto = accountService.addAccount(accountRequestDto);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.CREATED, ResponseMessage.CREATED_ACCOUNT, addedAccountResponseDto),
                Headers(), HttpStatus.CREATED);
    }

    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> getAccount(@PathVariable Long id) {
        AccountResponseDto accountDto = accountService.getAccount(id);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.GET_ACCOUNT, accountDto),
                Headers(), HttpStatus.OK);
    }

    @PutMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> updateAccount(@RequestBody AccountUpdateResponseDto accountDto) {
        AccountResponseDto updatedAccountDto = accountService.updateAccount(accountDto);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.UPDATED_ACCOUNT, updatedAccountDto),
                Headers(), HttpStatus.OK);
    }

//    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.OK, ResponseMessage.DELETED_ACCOUNT,"account deleted"),
                Headers(), HttpStatus.OK);
    }

    private HttpHeaders Headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer [token]");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
        return headers;
    }

}
