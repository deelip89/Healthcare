package com.springboot.Healthcare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {
	@Autowired
	private PatientService patientService;
	
	@Test
	public void addPatientTest() {
		Patient patient = new Patient(1L,"Kusum","Khadka");
		patientService.addPatient(patient);
		Patient result = patientService.addPatient(patient);
		assertNotNull(result.getId());
		assertEquals("Kusum", result.getFirstName());
		assertEquals("Khadka",result.getLastName());
			
	}
	@Test
	public void getPatientTest() {
		Patient patient = new Patient(1L, "Kusum", "Khadka");
		patientService.addPatient(patient);
		Patient result = patientService.getPatient(1L);
		assertNotNull(result);
		assertEquals("Kusum", result.getFirstName());
		assertEquals("Khadka", result.getLastName());
		
		
		
	}
	@Test
	public void getAllPatienttest() {
		Patient patient1 = new Patient(1L, "Kusum", "Khadka");
		Patient patient2 = new Patient(2L, "Ram", "Sharma");
		patientService.addPatient(patient1);
		patientService.addPatient(patient2);
		List<Patient> patients = patientService.getAllPatient();
		assertEquals(2, patients.size());
		
	}
	
	
	
	
	
	

}
