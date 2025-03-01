package is.webworks.link_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import is.webworks.link_service.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}