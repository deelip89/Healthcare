package com.springboot.Healthcare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



public class PatientServiceTest {
	@Mock
    private PatientRepository patientRepository;

	@InjectMocks
    private PatientService patientService;
	
	
	@BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	public void addPatientTest() {
	Patient patient = new Patient( "Deelip", "Chhetri");
	when(patientRepository.save(patient)).thenReturn(patient);
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
		patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

		
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
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

		List<Patient> allPatients = patientService.getAllPatient();
		assertEquals(2, allPatients.size());
		assertTrue(allPatients.contains(patient1));
		assertTrue(allPatients.contains(patient2));
	}

	@Test
	void deletePatientTest() {
	     Patient patient = new Patient( "Deelip", "Chhetri");
		
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
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
		when(patientRepository.existsById(1L)).thenReturn(true);
		when(patientRepository.save(patient)).thenReturn(patient);
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
	
	
	
	
	
	
	
	
	@Test
	public void validatePatientExistsTest_PatientExists() {
	    Patient patient = new Patient("Deelip", "Chhetri");
	    patient.setId(1L);
	    patientService.addPatient(patient);

	    Assertions.assertDoesNotThrow(() -> patientService.validatePatientExists(1L));
	}

	@Test
	public void validatePatientExistsTest_PatientDoesNotExist() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        patientService.validatePatientExists(2L);
	    });
	}
	
	
	
	@Test
	public void validateValidPatientIdTest_ValidId() {
	    Long id = 1L;

	    Assertions.assertDoesNotThrow(() -> patientService.validateValidPatientId(id));
	}

	@Test
	public void validateValidPatientIdTest_InvalidId_Null() {
	    Long id = null;

	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	 patientService.validateValidPatientId(id);
	    });
	}

	@Test
	public void validateValidPatientIdTest_InvalidId_Negative() {
	    Long id = -1L;

	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        patientService.validateValidPatientId(id);
	    });
	}

	@Test
	public void validateValidPatientIdTest_InvalidId_Zero() {
	    Long id = 0L;

	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        patientService.validateValidPatientId(id);
	    });
	}

	@Test
	public void validateValidPatientIdTest_InvalidId_Positive() {
	    Long id= 100L;

	    Assertions.assertDoesNotThrow(() -> patientService.validateValidPatientId(id));
	}
}