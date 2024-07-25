package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DoctorEntity;

import java.util.List;

public interface DoctorService {
    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<DoctorDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);

    List<String> getAllTowns();

    void deleteDoctor(Long id);

    List<DoctorDTO> findByName(String name);

    DoctorDTO getDoctorById(Long doctorId);

    DoctorEntity findByEmail(String email);

    void changePassword(DoctorEntity doctor, String password);
}
