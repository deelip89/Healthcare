package com.springboot.Healthcare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
public class PatientServiceTest {
    @Autowired
	private PatientService patientService;

	@BeforeEach
	public void setUp() {
		patientService = new PatientService();
	}

	@Test
	public void addPatientTest() {
	Patient patient = new Patient( "Deelip", "Chhetri");
	Patient addedPatient = patientService.addPatient(patient);
		Map<Long, Patient> patients = patientService.getPatients();
		assertNotNull(addedPatient);
	    assertNotNull(addedPatient.getId());
	    Patient getPatient = patients.get(addedPatient.getId());
		assertEquals(patient.getFirstName(),getPatient.getFirstName());
		assertEquals(patient.getLastName(), getPatient.getLastName());
	}
	

	@Test
	public void addPatientTest_Negative_NullPatient() {
		PatientService patientService = new PatientService();
		Assertions.assertThrows(Exception.class, () -> {
			patientService.addPatient(null);
		});
	}
	
	@Test
	public void getPatientTest() {
		Patient patient = new Patient( "Deelip", "Chhetri");
		patientService.addPatient(patient);
		Patient result = patientService.getPatient(1L);
		assertEquals(patient, result);
	}
	
	@Test
	public void getPatientTest_Negative_NonExistentPatient() {
		Long nonExistentId = 2L;
		Patient result = patientService.getPatient(nonExistentId);
		Assertions.assertNull(result);
	}

	@Test
	public void getAllPatientTest() {
		Patient patient1 = new Patient( "Deelip", "Chhetri");
		Patient patient2 = new Patient( "Sagar", "Thapa");
		patientService.addPatient(patient1);
		patientService.addPatient(patient2);
		List<Patient> allPatients = patientService.getAllPatient();
		assertEquals(2, allPatients.size());
		assertTrue(allPatients.contains(patient1));
		assertTrue(allPatients.contains(patient2));
	}

	@Test
	void deletePatientTest() {
		Patient patient = new Patient( "Deelip", "Chhetri");
		patientService.addPatient(patient);
		Patient deletedPatient = patientService.deletePatient(1L);
		assertEquals(patient, deletedPatient);
		assertNull(patientService.getPatient(1L));
	}

	@Test
	public void deletePatientTest_Negative_NonExistentPatient() {
		Long nonExistentId = 2L;
		Patient result = patientService.deletePatient(nonExistentId);
		Assertions.assertNull(result);
	}

	@Test
	void updatePatientTest() {
		Patient patient = new Patient( "Deelip", "Chhetri");
		patientService.addPatient(patient);
		Patient updatedPatient = new Patient( "Sagar", "Thapa");
		Patient result = patientService.updatePatient(1L, updatedPatient);
		assertEquals(updatedPatient, result);
		assertEquals("Thapa", patientService.getPatient(1L).getLastName());

	}
	@Test
	public void updatePatientTest_Negative_NonExistentPatient() {
		Long nonExistentId = 2L;
		Patient updatedPatient = new Patient( "Deelip", "Chhetri");
		Patient result = patientService.updatePatient(nonExistentId, updatedPatient);
		Assertions.assertNull(result);
	}
}