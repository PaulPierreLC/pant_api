package group.pant.api.service;

import group.pant.api.model.Role;
import group.pant.api.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Integer id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));

        existingRole.setNom(role.getNom());

        return roleRepository.save(existingRole);
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    public Role patchRole(Integer id, Map<String, Object> patch) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingRole.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return roleRepository.save(existingRole);
    }
}
