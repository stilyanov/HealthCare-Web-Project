package bg.softuni.healthcare.init;

import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesInitializer implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    public RolesInitializer(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            for (UserRoleEnum role : UserRoleEnum.values()) {
                UserRoleEntity roleEntity = new UserRoleEntity().setRole(role);
                userRoleRepository.save(roleEntity);
            }
        }
    }
}
