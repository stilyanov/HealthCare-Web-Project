package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.entity.DepartmentEntity;

public interface DepartmentService {
    DepartmentEntity findById(Long id);
}
