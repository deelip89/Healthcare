package com.springboot.Healthcare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {
	
	private PatientService patientService;
	@BeforeEach
    public void setUp() {
        patientService = new PatientService();
	}

    @Test
    void addPatientTest() {
        Patient patient = new Patient(1L, "Deelip", "Chhetri");
        Patient addedPatient = patientService.addPatient(patient);
        
        assertEquals(patient, addedPatient);
        assertNotNull(addedPatient.getId());
    }
    
    @Test
    public void addPatientTest_Negative_NullPatient() {
        Patient nullPatient = null;
        try {      
            patientService.addPatient(nullPatient);
            Assertions.fail();
        } catch (NullPointerException e) {
            Assertions.assertNotNull(e);
        }
    }
    
    @Test
    void getPatientTest() {
        Patient patient = new Patient(1L, "Deelip", "Chhetri");
        patientService.addPatient(patient);

        Patient retrievedPatient = patientService.getPatient(1L);
        assertEquals(patient, retrievedPatient);
    }

        @Test
        public void getPatientTest_Negative_NonExistentPatient() { 
            Long nonExistentId = 2L;
            Patient result = patientService.getPatient(nonExistentId);

            Assertions.assertNull(result);
        
      
    }

    @Test
    void getAllPatientTest() {
        Patient patient1 = new Patient(1L, "Deelip", "Chhetri");
        Patient patient2 = new Patient(2L, "Sagar", "Thapa");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);

        List<Patient> allPatients = patientService.getAllPatient();
        assertEquals(2, allPatients.size());
        assertTrue(allPatients.contains(patient1));
        assertTrue(allPatients.contains(patient2));
    }

    @Test
    void deletePatientTest() {
        Patient patient = new Patient(1L, "Deelip", "Chhetri");
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
        Patient patient = new Patient(1L,"Deelip", "Chhetri");
        patientService.addPatient(patient);

        Patient updatedPatient = new Patient(1L, "Sagar", "Thapa");
        Patient result = patientService.updatePatient(1L, updatedPatient);
        assertEquals(updatedPatient, result);
        assertEquals("Thapa", patientService.getPatient(1L).getLastName());


    }
    @Test
    public void updatePatientTest_Negative_NonExistentPatient() {
        Long nonExistentId = 2L;
        Patient updatedPatient = new Patient(nonExistentId, "Deelip", "Chhetri");

        Patient result = patientService.updatePatient(nonExistentId, updatedPatient);

        Assertions.assertNull(result);
    }
}

		