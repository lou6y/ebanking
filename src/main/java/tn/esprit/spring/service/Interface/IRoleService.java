package tn.esprit.spring.service.Interface;

import java.util.Optional;

import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;

public interface IRoleService {
	  Optional<Role> findByName(ERole name);

}
