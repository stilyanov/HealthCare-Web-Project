package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addDoctor(AddDoctorDTO addDoctorDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUser = "";

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            currentUser = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication != null && authentication.getPrincipal() instanceof String) {
            currentUser = (String) authentication.getPrincipal();

        }

        UserEntity user = this.userRepository.findByEmail(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        DoctorEntity doctor = this.modelMapper.map(addDoctorDTO, DoctorEntity.class);

        Optional<DepartmentEntity> department = this.departmentRepository.findByName(DepartmentEnum.valueOf(addDoctorDTO.getDepartment().name()));

        doctor.setDepartment(department.get());
        doctor.setUser(user);

        this.doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return this.doctorRepository.findAll()
                .stream()
                .map(doctor -> {
                    DoctorDTO allDoctorsDTO = this.modelMapper.map(doctor, DoctorDTO.class);
                    allDoctorsDTO.setDepartment(doctor.getDepartment().getName());
                    return allDoctorsDTO;
                }).toList();
    }

    @Override
    public InfoDoctorDTO getDoctorInfo(Long doctorId) {
        return this.doctorRepository.findById(doctorId)
                .map(doctor -> {
                    InfoDoctorDTO infoDoctorDTO = this.modelMapper.map(doctor, InfoDoctorDTO.class);
                    infoDoctorDTO.setDepartment(doctor.getDepartment().getName());
                    infoDoctorDTO.setAddedBy(doctor.getUser().getUsername());
                    return infoDoctorDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Override
    public List<String> getAllTowns() {
        return this.doctorRepository.findAllTowns();
    }

    @Override
    public void deleteDoctor(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new IllegalArgumentException("You do not have permission to delete users");
        }
        this.doctorRepository.deleteById(id);
    }
}
