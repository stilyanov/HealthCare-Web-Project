package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AllDoctorsDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentEntity findById(Long id) {
        return this.departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public List<AllDoctorsDTO> findByDepartment(DepartmentEnum name) {
       return departmentRepository.findByName(name)
               .orElseThrow(() -> new IllegalArgumentException("Department not found"))
               .getDoctors()
                .stream()
                .map(doctor -> {
                    AllDoctorsDTO allDoctorsDTO = modelMapper.map(doctor, AllDoctorsDTO.class);
                    allDoctorsDTO.setDepartment(name);
                    return allDoctorsDTO;
                })
                .toList();
    }
}
