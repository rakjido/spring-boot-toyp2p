package rooftophero.io.toyp2p.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rooftophero.io.toyp2p.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
