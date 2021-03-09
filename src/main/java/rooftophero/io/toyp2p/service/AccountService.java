package rooftophero.io.toyp2p.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rooftophero.io.toyp2p.domain.Account;
import rooftophero.io.toyp2p.repository.AccountRepository;
import rooftophero.io.toyp2p.service.dto.AccountDto;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AccountDto addAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account addedAccount = accountRepository.save(account);
        return modelMapper.map(addedAccount, AccountDto.class);
    }
}
