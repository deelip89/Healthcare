package com.springboot.Healthcare;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	private Map<Long, Patient> patients = new HashMap<>();
	 private Long nextId = 1L;

	 public Patient addPatient(Patient patient) {
		 
		    if (patientRepository.existsById(patient.getId())) {
		        throw new PatientAlreadyExistsException("Patient with ID " + patient.getId() + " already exists.");
		        
		    } 
		    patient.setId(nextId++);
		    patients.put(patient.getId(), patient);
		    return patientRepository.save(patient);
		}

	public Patient getPatient(Long id) {
		    return patientRepository.findById(id).orElseThrow(() ->
		            new PatientNotFoundException("Patient with ID " + id + " not found."));
		}

	
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	public Patient updatePatient(Long id, Patient patient) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found.");
        }

        patient.setId(id);
        return patientRepository.save(patient);
    }

	   

	public void deletePatient(Long id) {
		if (patientRepository.existsById(id)) {
		    patientRepository.deleteById(id);
		} else {
		    throw new PatientNotFoundException("Patient with ID " + id + " not found.");
		}
	}
}

