package Thoughtworks.UserAuth.Repositories;

import Thoughtworks.UserAuth.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
