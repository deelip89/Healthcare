
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
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        
        when(patientRepository.existsById(patient.getId())).thenReturn(false);
        when(patientRepository.save(patient)).thenReturn(patient);

        // Act
        Patient result = patientService.addPatient(patient);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
      
    }

    @Test
    public void addPatientTest_PatientAlreadyExistsException() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);

        when(patientRepository.existsById(patient.getId())).thenReturn(true);

        // Act and Assert
        assertThrows(PatientAlreadyExistsException.class, () -> patientService.addPatient(patient));
        
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
    void getPatientTest_PatientNotFoundException() {
        // Arrange
        Long nonExistentId = 2L;
//        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

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
    public void deletePatientTest() {
        Long patientId = 1L;

        when(patientRepository.existsById(patientId)).thenReturn(true);

        patientService.deletePatient(patientId);

       
    }
    @Test
    public void deletePatientTest_PatientNotFoundException() {
        Long patientId = 2L;

        when(patientRepository.existsById(patientId)).thenReturn(false);

        assertThrows(PatientNotFoundException.class, () -> { patientService.deletePatient(patientId);
        });

       
    }
}
 

