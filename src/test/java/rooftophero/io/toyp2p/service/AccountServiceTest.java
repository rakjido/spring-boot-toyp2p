package rooftophero.io.toyp2p.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import rooftophero.io.toyp2p.domain.Account;
import rooftophero.io.toyp2p.exception.NoDataFoundException;
import rooftophero.io.toyp2p.repository.AccountRepository;
import rooftophero.io.toyp2p.service.dto.request.AccountRequestDto;
import rooftophero.io.toyp2p.service.dto.request.AccountUpdateResponseDto;
import rooftophero.io.toyp2p.service.dto.response.AccountResponseDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        // Mock Configuration
        MockitoAnnotations.initMocks(this);

        // ModelMapper Mock
//        when(modelMapper.map(any(), any())).thenReturn(mockResponseAccount(1L, "Kb Bank", "2151-1351"));
    }

    @Test
    public void getAccountList() throws Exception {
        // Given
        when(accountRepository.findAll())
                .thenReturn(Lists.newArrayList(mockAccount("Kb Bank","2151-1351"),
                        mockAccount("Woori Bank", "314321-1313")));
        // When
        List<AccountResponseDto> accountList = accountService.getAccountList();
        // Then
        assertThat(accountList.size()).isEqualTo(2);
        assertThat(accountList.get(0).getBankName()).isEqualTo("Kb Bank");
        assertThat(accountList.get(0).getAccountNumber()).isEqualTo("2151-1351");
        assertThat(accountList.get(1).getBankName()).isEqualTo("Woori Bank");
        assertThat(accountList.get(1).getAccountNumber()).isEqualTo("314321-1313");
    }

    @Test
    public void getAccount() throws Exception {
        // Given
        Long id = 1L;
        String bankName = "Kb Bank";
        String accountNumber = "2151-1351";
        Account account = mockAccount(id, bankName, accountNumber);
        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));
        // When

        AccountResponseDto accountDto = accountService.getAccount(1L);
        // Then
        assertAll(
                () -> assertThat(accountDto.getBankName()).isEqualTo(bankName),
                () -> assertThat(accountDto.getAccountNumber()).isEqualTo(accountNumber)
        );
    }

    @Test
    public void getAccountNotFound() throws Exception {
        // Given
        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());
        // When
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            AccountResponseDto accountResponseDto = accountService.getAccount(1L);
        });
        // Then
        assertThat(exception.getMessage()).isEqualTo("No Data Found");
    }


    // ModelMapper는 @Bean을 통해 사용해야 하므로 @Mock에서 사용하지 못하는 문제로 인해 ModelMap으로 바꿈.
    // TODO verify 추가
    @Test
    public void addAccount() throws Exception {
        // Given
        Long id = 1L;
        String bankName = "Kb Bank";
        String accountNumber = "2151-1351";
        Account savedAccount = mockAccount(id, bankName, accountNumber);
        when(accountRepository.save(any()))
                .thenReturn(savedAccount);
        // When
        AccountResponseDto accountResponseDto = accountService.addAccount(mockRequestDto(bankName, accountNumber));
        // Then
        assertThat(accountResponseDto.getBankName()).isEqualTo(bankName);
    }

    @Test
    public void updateAccountIsNull() throws Exception {
        // Given
        Long id = 1L;
        String bankName = "Kb Bank";
        String accountNumber = "2151-1351";
        AccountUpdateResponseDto accountUpdateResponseDto = mockUpdateRequestDto(id, bankName, accountNumber);

        when(accountRepository.save(any())).thenThrow(new IllegalArgumentException("IllegalArgumentException"));
        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AccountResponseDto accountResponseDto = accountService.updateAccount(accountUpdateResponseDto);
        });
        // Then
        assertThat(exception.getMessage()).isEqualTo("IllegalArgumentException");
    }

    @Test
    public void updateAccount() throws Exception {
        // Given
        Long id = 1L;
        String bankName = "Kb Bank";
        String accountNumber = "2151-1351";
        Account account = mockAccount(id, bankName, accountNumber);
        AccountUpdateResponseDto updateResponseDto = mockUpdateRequestDto(id, bankName, accountNumber);
        when(accountRepository.save(any())).thenReturn(account);

        // When
        accountService.updateAccount(updateResponseDto);
        // Then
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void deleteAccountNotFound() throws Exception {
        // Given
        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());
        // When
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            accountService.deleteAccount(1L);
        });
        // Then
        assertThat(exception.getMessage()).isEqualTo("No Data Found");
    }


    @Test
    public void deleteAccount() throws Exception {
        // Given
        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(mockAccount(1L, "Kb Bank", "2151-1351")));
        // When
        accountService.deleteAccount(1L);
        // Then
        verify(accountRepository, times(1)).delete(any(Account.class));
    }



    private Account mockAccount(Long id, String bankName, String accountNumber){
        return Account.builder()
                .id(id)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }
    private Account mockAccount(String bankName, String accountNumber){
        return Account.builder()
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }

    private AccountRequestDto mockRequestDto(String bankName, String accountNumber) {
        return AccountRequestDto.builder()
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }

    private AccountUpdateResponseDto mockUpdateRequestDto(Long id, String bankName, String accountNumber) {
        return AccountUpdateResponseDto.builder()
                .id(id)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }

    private AccountResponseDto mockResponseAccount(Long id, String bankName, String accountNumber) {
        return AccountResponseDto.builder()
                .id(id)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }

}