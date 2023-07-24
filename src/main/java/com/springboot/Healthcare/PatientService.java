package com.springboot.Healthcare;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	private Map<Long, Patient> patients = new HashMap<>();
	 private Long nextId = 1L;

	 public Patient addPatient(Patient patient) {
		 if (patientRepository.existsByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())) {
	            throw new ValidationException("Patient with the same first name and last name already exists.");
	        }	   
		    patient.setId(nextId++);
		    patients.put(patient.getId(), patient);
		    return patientRepository.save(patient);
		}

	public Patient getPatient(Long id) {
		if (id == null || id <= 0) {
	        throw new ValidationException("Id must be a positive non-zero value.");
	    }
		    return patientRepository.findById(id).orElseThrow(() ->
		            new PatientNotFoundException("Patient with ID " + id + " not found."));
		}

	
	public List<Patient> getAllPatients(){
		List<Patient> patients = patientRepository.findAll();

		return Optional.ofNullable(patients)
				.orElseThrow(() -> new PatientNotFoundException("Patients not found."));
	}
	
	
	public Patient updatePatient(Long id, Patient patient) {
	    if (id == null || id <= 0) {
	        throw new ValidationException("Id must be a positive non-zero value.");
	    }
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found.");
        }
        
        return patientRepository.save(patient);
    }   

	
	public void deletePatient(Long id) {
		if (id == null || id <= 0) {
	        throw new ValidationException("Id must be a positive non-zero value.");
	    }
		
		if (patientRepository.existsById(id)) {
		    patientRepository.deleteById(id);
		} else {
		    throw new PatientNotFoundException("Patient with ID " + id + " not found.");
		}
	}
	
}

