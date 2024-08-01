package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.*;
import bg.softuni.healthcare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final PrescriptionRepository prescriptionRepository;

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

        UserRoleEntity doctorRole = this.userRoleRepository.findByRole(UserRoleEnum.DOCTOR);

        UserEntity doctorUser = new UserEntity();
        doctorUser.setEmail(addDoctorDTO.getEmail());
        doctorUser.setFirstName(addDoctorDTO.getFirstName());
        doctorUser.setLastName(addDoctorDTO.getLastName());
        doctorUser.setPassword(this.passwordEncoder.encode(addDoctorDTO.getPassword()));
        doctorUser.getRoles().add(doctorRole);

        this.userRepository.save(doctorUser);

        DoctorEntity doctor = this.modelMapper.map(addDoctorDTO, DoctorEntity.class);

        Optional<DepartmentEntity> department = this.departmentRepository.findByName(DepartmentEnum.valueOf(addDoctorDTO.getDepartment().name()));

        doctor.setPassword(this.passwordEncoder.encode(addDoctorDTO.getPassword()));
        doctor.setDepartment(department.get());
        doctor.setUser(doctorUser);
        doctor.setAddedTime(LocalDate.now());

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
            throw new IllegalArgumentException("You do not have permission to delete doctors");
        }
        this.doctorRepository.deleteById(id);
    }

    @Override
    public List<DoctorDTO> findByName(String name) {
        return this.doctorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(doctor -> {
                    DoctorDTO doctorDTO = this.modelMapper.map(doctor, DoctorDTO.class);
                    doctorDTO.setDepartment(doctor.getDepartment().getName());
                    return doctorDTO;
                }).toList();
    }

    @Override
    public DoctorDTO getDoctorById(Long doctorId) {
        return this.doctorRepository.findById(doctorId)
                .map(doctor -> {
                    DoctorDTO doctorDTO = this.modelMapper.map(doctor, DoctorDTO.class);
                    doctorDTO.setDepartment(doctor.getDepartment().getName());
                    return doctorDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Override
    public DoctorEntity findByEmail(String email) {
        return this.doctorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Override
    public void changePassword(DoctorEntity doctor, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        doctor.setPassword(encodedPassword);
        doctor.setPasswordChanged(true);
        doctorRepository.save(doctor);

        userRepository.updatePasswordByEmail(doctor.getEmail(), encodedPassword);
    }

    @Override
    public Long getDoctorIdByEmail(String userEmail) {
        return this.doctorRepository.findByEmail(userEmail)
                .map(DoctorEntity::getId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

}
