package com.springboot.Healthcare;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PatientService {

   private Map<Long, Patient> patients = new HashMap<>();
   private Long nextId = 1L;

 
   
   public Patient addPatient(Patient patient) throws PatientAlreadyExistsException {
	    if (patients.containsKey(patient.getId())) {
	        throw new PatientAlreadyExistsException("Patient with ID: " + patient.getId() + " already exists.");
	    }
	    patient.setId(nextId++);
	    patients.put(patient.getId(), patient);
	    return patient;
	}
   
   public Map<Long, Patient> getPatients() {
       return patients;
   }

   public Patient getPatient(Long id) throws PatientNotFoundException {
       Patient patient = patients.get(id);
       if (patient == null) {
           throw new PatientNotFoundException("Patient not found with ID: " + id);
       }
       return patient;
   }
  
   public List<Patient> getAllPatients() {
       return new ArrayList<>(patients.values());
   }

   
   public Patient deletePatient(Long id) throws PatientNotFoundException {
	    if (!patients.containsKey(id)) {
	        throw new PatientNotFoundException("Patient not found with ID: " + id);
	    }
	    return patients.remove(id);
	}


  
   public Patient updatePatient(Long id, Patient patient) throws PatientNotFoundException {
	    if (!patients.containsKey(id)) {
	        throw new PatientNotFoundException("Patient not found with ID: " + id);
	    }
	    patient.setId(id);
	    patients.put(id, patient);
	    return patient;
	}

}

 
//public class PatientService {
//	private Map<Long, Patient> patients = new HashMap<>();
//	private Long nextId = 1L;
//	public Patient addPatient(Patient patient) throws PatientAlreadyExistsException, PatientEmptyException {
//	    if (patient.getFirstName().isEmpty() || patient.getLastName().isEmpty()) {
//	        throw new PatientEmptyException("First name and last name cannot be empty.");
//	    }
//
//	    // Check if patient with the same details already exists
//	    for (Patient existingPatient : patients.values()) {
//	        if (existingPatient.getFirstName().equals(patient.getFirstName()) &&
//	            existingPatient.getLastName().equals(patient.getLastName())) {
//	            throw new PatientAlreadyExistsException("Patient with the same details already exists.");
//	        }
//	    }
//
//	    patient.setId(nextId++);
//	    patients.put(patient.getId(), patient);
//	    return patient;
//	}
//
//
////	 public Map<Long, Patient> getPatients() {
////	        return patients;
////	    }
////	
////	public Patient getPatient(Long id) {
////		return patients.get(id);
////		
////	}
////	public List<Patient> getAllPatient(){
////		return new ArrayList<>(patients.values());
////		
////	}
////	public Patient deletePatient(Long id) {
////		 return patients.remove(id);
////	}
////	public Patient updatePatient(Long id, Patient patient) {
////		if (patients.containsKey(id)) {
////			patient.setId(id);
////			patients.put(id, patient);
////			return patient;
////		}
////		return null;
////		
////	}
//	
//	
//	
////	public void validatePatientExists(Long id) {
////        if (!patients.containsKey(id)) {
////            throw new IllegalArgumentException();
////        }
////    }
////	 public void validateValidPatientId(Long id) {
////	        if (id == null || id <= 0) {
////	            throw new IllegalArgumentException();
////	        }
////	    }
//	
//	
//
//}
