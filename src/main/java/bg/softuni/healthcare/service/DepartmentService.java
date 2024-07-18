package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AllDoctorsDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;

import java.util.List;

public interface DepartmentService {
    DepartmentEntity findById(Long id);

    List<AllDoctorsDTO> findByDepartment(DepartmentEnum name);

}
