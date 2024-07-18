package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {
    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<DoctorDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);

    List<String> getAllTowns();
}
