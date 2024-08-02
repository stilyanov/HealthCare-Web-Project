package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.doctor.DoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentEntity findById(Long id) {
        return this.departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public List<DoctorDTO> findByDepartment(DepartmentEnum name) {
       return departmentRepository.findByName(name)
               .orElseThrow(() -> new IllegalArgumentException("Department not found"))
               .getDoctors()
                .stream()
                .map(this::mapDoctors)
                .toList();
    }

    @Override
    public List<DoctorDTO> findByTown(String town) {
        return this.doctorRepository.findByTown(town)
                .stream()
                .map(this::mapDoctors)
                .toList();
    }

    private DoctorDTO mapDoctors(DoctorEntity doctor) {
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setDepartment(doctor.getDepartment().getName());
        return doctorDTO;
    }
}
