package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.doctor.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.AllDoctorsDTO;

import java.util.List;

public interface DoctorService {
    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<AllDoctorsDTO> getAllDoctors();

    AllDoctorsDTO getDoctorInfo(Long doctorId);
}
