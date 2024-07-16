package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity findById(Long id) {
        return this.departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }
}
