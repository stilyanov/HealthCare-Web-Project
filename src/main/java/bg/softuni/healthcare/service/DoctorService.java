package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.AllDoctorsDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {
    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<AllDoctorsDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);
}
