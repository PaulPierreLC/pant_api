package group.pant.api.service;

import group.pant.api.model.Role;
import group.pant.api.repository.RoleRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Integer id, Role roleDetails) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setNom(roleDetails.getNom());
        return roleRepository.save(role);
    }

    public void deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.delete(role);
    }

    public Role patchRole(Integer id, Map<String, Object> updates) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                role.setNom((String) value);
            }
        });

        return roleRepository.save(role);
    }
}
