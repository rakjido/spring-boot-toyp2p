package rooftophero.io.toyp2p.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rooftophero.io.toyp2p.exception.NoDataFoundException;
import rooftophero.io.toyp2p.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    // TODO Account Entity에 제한조건 추가시 관련 테스트 추가

    public Account initAccount() {
        return Account.builder()
                .bankName("KB Bank")
                .accountNumber("23041351-3151531")
                .build();
    }

    @Test
    public void createAccount() throws Exception {
        // Given
        Account account = initAccount();
        // When
        Account savedAccount = accountRepository.save(account);
        // Then
        assertNotNull(savedAccount);
        assertThat(savedAccount.getBankName()).isEqualTo(account.getBankName());
        assertThat(savedAccount.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }

    @Test
    public void getAccount() throws Exception {
        // Given
        Account account = initAccount();
        // When
        Account savedAccount = accountRepository.save(account);
        Account getAccount = accountRepository.findById(savedAccount.getId())
                .orElseThrow(() -> new NoDataFoundException());
        // Then
        assertThat(getAccount.getBankName()).isEqualTo(account.getBankName());
        assertThat(getAccount.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }

    @Test
    public void getAccountNoDataFound() throws Exception {
        // Given
        Account account = initAccount();
        // When
        Account savedAccount = accountRepository.save(account);
        Optional<Account> findAccount = accountRepository.findById(12L);
        // Then
        assertThat(findAccount).isEmpty();
    }

    @Test
    public void findAll() throws Exception {
        // Given
        Account account1 = initAccount();
        Account account2 = Account.builder()
                .bankName("Woori Bank")
                .accountNumber("231531-3151")
                .build();

        accountRepository.save(account1);
        accountRepository.save(account2);

        // When
        List<Account> accounts = accountRepository.findAll();

        // Then
        assertAll(
                () -> assertThat(accounts.get(0).getBankName()).isEqualTo(account1.getBankName()),
                () -> assertThat(accounts.get(0).getAccountNumber()).isEqualTo(account1.getAccountNumber()),
                () -> assertThat(accounts.get(1).getBankName()).isEqualTo(account2.getBankName()),
                () -> assertThat(accounts.get(1).getAccountNumber()).isEqualTo(account2.getAccountNumber())
        );
    }

    @Test
    public void updateAccount() throws Exception {
        // Given
        Account account = initAccount();
        Account savedAccount = accountRepository.save(account);
        // When
        Account findAccount = accountRepository.findById(savedAccount.getId())
                .orElseThrow(() -> new NoDataFoundException());
        findAccount.setBankName("KB Update");
        findAccount.setAccountNumber("222-2222");
        Account updatedAccount = accountRepository.save(findAccount);
        // Then
        assertAll(
                () -> assertThat(updatedAccount.getId()).isEqualTo(savedAccount.getId()),
                () -> assertThat(updatedAccount.getBankName()).isEqualTo(updatedAccount.getBankName()),
                () -> assertThat(updatedAccount.getAccountNumber()).isEqualTo(updatedAccount.getAccountNumber())
        );
    }

    @Test
    public void deleteAccount() throws Exception {
        // Given
        Account account = initAccount();
        Account savedAccount = accountRepository.save(account);
        // When
        accountRepository.delete(savedAccount);
        // Then
        Optional<Account> deletedAccount = accountRepository.findById(savedAccount.getId());
        assertThat(deletedAccount).isEmpty();
    }
}