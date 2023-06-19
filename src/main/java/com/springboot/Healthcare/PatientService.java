package com.springboot.Healthcare;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
	private Map<Long, Patient> patients = new HashMap<>();
	private Long nextId = 1L;
	
	public Patient addPatient(Patient patient) {
		patient.setId(nextId++);
     	patients.put(patient.getId(), patient);
		return patient;
		
	}
	public Patient getPatient(Long id) {
		return patients.get(id);
		
	}
	public List<Patient> getAllPatient(){
		return new ArrayList<>(patients.values());
		
	}
	public Patient deletePatient(Long id) {
		 return patients.remove(id);
	}
	public Patient updatePatient(Long id, Patient patient) {
		if (patients.containsKey(id)) {
			patient.setId(id);
			patients.put(id, patient);
			return patient;
		}
		return null;
		
	}
	
	

}
