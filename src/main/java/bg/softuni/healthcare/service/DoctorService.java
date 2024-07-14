package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.doctor.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.AllDoctorsDTO;
import bg.softuni.healthcare.model.dto.doctor.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {
    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<AllDoctorsDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);
}
