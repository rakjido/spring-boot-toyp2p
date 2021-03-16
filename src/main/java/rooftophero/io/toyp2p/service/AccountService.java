package rooftophero.io.toyp2p.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rooftophero.io.toyp2p.domain.Account;
import rooftophero.io.toyp2p.exception.NoDataFoundException;
import rooftophero.io.toyp2p.repository.AccountRepository;
import rooftophero.io.toyp2p.service.dto.request.AccountRequestDto;
import rooftophero.io.toyp2p.service.dto.request.AccountUpdateResponseDto;
import rooftophero.io.toyp2p.service.dto.response.AccountResponseDto;
import rooftophero.io.toyp2p.utils.MapList;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AccountResponseDto addAccount(AccountRequestDto accountRequestDto) {
        Account account = modelMapper.map(accountRequestDto, Account.class);
        Account addedAccount = accountRepository.save(account);
        return modelMapper.map(addedAccount, AccountResponseDto.class);
    }

    public List<AccountResponseDto> getAccountList() {
        List<Account> accountList = accountRepository.findAll();
        return MapList.of(accountList, AccountResponseDto.class);
    }

    public AccountResponseDto getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoDataFoundException::new);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    @Transactional
    public AccountResponseDto updateAccount(AccountUpdateResponseDto accountUpdateResponseDto) {
        Account account = modelMapper.map(accountUpdateResponseDto, Account.class);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponseDto.class);
    }

    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoDataFoundException::new);
        accountRepository.delete(account);
    }
}
