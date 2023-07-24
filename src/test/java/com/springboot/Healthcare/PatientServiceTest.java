
package com.springboot.Healthcare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


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
	public void testAddPatientTest() {
	    // Arranging
	    Patient patient = new Patient();
	    // Mocking
	    when(patientRepository.existsByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())).thenReturn(false);
	    when(patientRepository.save(patient)).thenReturn(patient);
	    // Act
	    Patient addedPatient = patientService.addPatient(patient);
	    // Assert
	    assertNotNull(addedPatient.getId());
	    assertEquals(patient.getFirstName(), addedPatient.getFirstName());
	    assertEquals(patient.getLastName(), addedPatient.getLastName());   
	}
	
	@Test
	public void testAddPatient_ValidationException() {
	    // Arranging
	    Patient patient = new Patient();
	    // Mocking
	    when(patientRepository.existsByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())).thenReturn(true);
	    // Act & Assert
	    assertThrows(ValidationException.class, () -> patientService.addPatient(patient));
	}

	
	
    @Test
    void getPatientTest() {
        // Arrange
        Patient patient = new Patient( );
        patient.setId (1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Act
        Patient result = patientService.getPatient(1L);

        // Assert
        assertNotNull(result);
        assertEquals(patient, result);    
    }
    @Test
    void getPatientTest_ValidationException() {
        // Arrange
        Long nonPositiveId = 0L;  
        Long nullId = null;

        // Act and Assert
        assertThrows(ValidationException.class, () -> patientService.getPatient(nonPositiveId));
        assertThrows(ValidationException.class, () -> patientService.getPatient(nullId));      
    }
     
    @Test
    void getPatientTest_PatientNotFoundException() {
        // Arrange
        Long nonExistentId = 2L;
        when(patientRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatient(nonExistentId));
        
    }
    
    
    
    
    
    @Test
    public void getAllPatientsTest() {
       
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> allPatients = patientService.getAllPatients();

        assertEquals(2, allPatients.size());
        assertEquals(patient1, allPatients.get(0));
        assertEquals(patient2, allPatients.get(1));
    }
    @Test
	public void getAllPatients_PatientNotFoundException() {
		when(patientRepository.findAll()).thenReturn(null);

		assertThrows(PatientNotFoundException.class, () -> { patientService.getAllPatients();});	
	}
    
    
    
    
    
    @Test
    public void updatePatientTest() {
        // Arrange
        Long id = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(id);
        
        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
       
        when(patientRepository.existsById(id)).thenReturn(true);
        when(patientRepository.save(updatedPatient)).thenReturn(updatedPatient);

        // Act
        Patient result = patientService.updatePatient(id, updatedPatient);

        // Assert
        assertEquals(updatedPatient, result);
}
    @Test
    public void updatePatientTest_PatientNotFoundException() {
        // Arrange
        Long id = 1L;
        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
       
        when(patientRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(PatientNotFoundException.class, () -> patientService.updatePatient(id, updatedPatient));
    }
    @Test
    void getUpdateTest_ValidationException() {
    	Long nonPositiveId = -1L;
    	Long invalidId = null;

        assertThrows(ValidationException.class, () -> patientService.updatePatient(invalidId, new Patient()));

        assertThrows(ValidationException.class, () -> patientService.updatePatient(nonPositiveId, new Patient()));
    }
    
    
    
    
    
    
    
    @Test
    public void deletePatientTest() {
        Long patientId = 1L;

        when(patientRepository.existsById(patientId)).thenReturn(true);

        patientService.deletePatient(patientId);

       
    }
    @Test
    public void testDeletePatient_WithInvalidId_ShouldThrowValidationException() {
        Long invalidId = null;
        Long nonPositiveId = -1L;
        assertThrows(ValidationException.class, () -> patientService.deletePatient(nonPositiveId));
        assertThrows(ValidationException.class, () -> patientService.deletePatient(invalidId));
    }
    @Test
    public void deletePatientTest_PatientNotFoundException() {
        Long patientId = 2L;

        when(patientRepository.existsById(patientId)).thenReturn(false);

        assertThrows(PatientNotFoundException.class, () -> { patientService.deletePatient(patientId);});
        

       
    }
    
}
 

