package ak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ak.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{

}
