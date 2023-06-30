package tr.com.obss.jip2022.jip_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.enums.RoleType;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleType roleType);

}
