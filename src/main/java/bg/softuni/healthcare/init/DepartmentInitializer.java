package bg.softuni.healthcare.init;

import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {
        if (this.departmentRepository.count() == 0) {
            for (DepartmentEnum department : DepartmentEnum.values()) {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setName(department);
                this.departmentRepository.save(departmentEntity);
            }
        }
    }
}