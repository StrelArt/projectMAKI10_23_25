package telran.javamaki.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.javamaki.accounting.model.UserAccount;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
}
