package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
