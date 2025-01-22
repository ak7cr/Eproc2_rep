package ak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ak.entity.ListEntity;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long>{

}