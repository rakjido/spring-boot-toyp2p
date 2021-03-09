package rooftophero.io.toyp2p.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rooftophero.io.toyp2p.api.form.ResponseFormat;
import rooftophero.io.toyp2p.api.form.ResponseMessage;
import rooftophero.io.toyp2p.api.form.StatusCode;
import rooftophero.io.toyp2p.service.AccountService;
import rooftophero.io.toyp2p.service.dto.AccountDto;

@RequiredArgsConstructor
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> addAccount(@RequestBody AccountDto accountDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer [token]");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");

        AccountDto addedAccountDto = accountService.addAccount(accountDto);
        return new ResponseEntity<>(ResponseFormat.of(StatusCode.CREATED, ResponseMessage.CREATED_ACCOUNT, addedAccountDto), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> getAccount(@PathVariable Long id) {
        return null;
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @PutMapping("/api/accounts")
    public ResponseEntity<ResponseFormat> updateAccount(@RequestBody AccountDto accountDto) {
        return null;
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<ResponseFormat> deleteAccount(@PathVariable Long id) {
        return null;
    }
}
