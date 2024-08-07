package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.doctor.DoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        DepartmentEntity department = new DepartmentEntity();
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        DepartmentEntity result = departmentService.findById(1L);
        assertEquals(department, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> departmentService.findById(1L));
    }

    @Test
    public void testFindByDepartment() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDepartment(new DepartmentEntity());
        doctor.getDepartment().setName(DepartmentEnum.CARDIOLOGY);
        List<DoctorEntity> doctors = List.of(doctor);
        DepartmentEntity department = new DepartmentEntity();
        department.setDoctors(doctors);

        when(departmentRepository.findByName(DepartmentEnum.CARDIOLOGY)).thenReturn(Optional.of(department));
        when(modelMapper.map(doctor, DoctorDTO.class)).thenReturn(new DoctorDTO());

        List<DoctorDTO> result = departmentService.findByDepartment(DepartmentEnum.CARDIOLOGY);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByDepartmentNotFound() {
        when(departmentRepository.findByName(DepartmentEnum.CARDIOLOGY)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> departmentService.findByDepartment(DepartmentEnum.CARDIOLOGY));
    }

    @Test
    public void testFindByTown() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDepartment(new DepartmentEntity());
        List<DoctorEntity> doctors = List.of(doctor);

        when(doctorRepository.findByTown("Sofia")).thenReturn(doctors);
        when(modelMapper.map(doctor, DoctorDTO.class)).thenReturn(new DoctorDTO());

        List<DoctorDTO> result = departmentService.findByTown("Sofia");
        assertEquals(1, result.size());
    }
}