package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Role findByName(ERole name);
}
