package com.springboot.Healthcare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AppointmentServiceTest {
	@Mock
	private AppointmentRepository appointmentRepository;
	@InjectMocks
	private AppointmentService appointmentService;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	    @Test
	    public void addAppointmentTest() {
	        // Arrange
	        Appointment appointment = new Appointment();
	       
	        when(appointmentRepository.save(appointment)).thenReturn(appointment);

	        // Act
	        Appointment result = appointmentService.addAppointment(appointment);

	        // Assert
	        assertEquals(appointment, result); 
	        assertNotNull(result); 
	        assertNotNull(result.getAppointmentId()); 

	       

	        Map<Long, Appointment> appointments = appointmentService.getAppointments();
	        assertNotNull(appointments);
	        assertTrue(appointments.containsKey(result.getAppointmentId()));
	        assertEquals(appointment, appointments.get(result.getAppointmentId()));
	    }
	   
	@Test
	public void addAppointmentTest_Negative() {
	    // Act and Assert
	    assertThrows(NullPointerException.class, () -> {
	        appointmentService.addAppointment(null);
	    });
	}
	
	

	@Test
	public void getAppointmentTest() {
		// Arranging
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();

        // Mock part
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        // Act
        Optional<Appointment> result = appointmentService.getAppointment(appointmentId);

        // Asserting
        assertEquals(Optional.of(appointment), result);
        assertNotNull(result);
		
        
    }
	@Test
    void getAppointment_NonexistentAppointmentId_ReturnsEmpty() {
        // Arrange
        Long appointmentId = 2L;

        // Mock
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        // Act
        Optional<Appointment> result = appointmentService.getAppointment(appointmentId);

        // Assert
        assertTrue(result.isEmpty());
    }
	@Test 
    public void getAppointmentsByPatientIdTest() {
		// Arranging
        Long patientId = 1L;
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> expectedAppointments = Arrays.asList(appointment1, appointment2);

        // Mock 
        when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getAppointmentsByPatientId(patientId);

        // Assert
        assertEquals(expectedAppointments, result);
    }
	

	    
	    @Test
	    public void testGetAppointmentsByPatientId_NoAppointmentsFound() {
	        // Arrange
	        Long patientId = 1L;
	        List<Appointment> expectedAppointments = Collections.emptyList();

	        // Mock
	        when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(expectedAppointments);

	        // Act
	        List<Appointment> result = appointmentService.getAppointmentsByPatientId(patientId);

	        // Assert
	        assertEquals(expectedAppointments, result);
	    }
	    @Test
	    public void deleteAppointmentTest() {
	        // Arrange
	        Long appointmentId = 1L;
	        Appointment appointment = new Appointment();
	        
	        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

	        // Act
	        appointmentService.deleteAppointment(appointmentId);

	        // Assert
	        
	        Map<Long, Appointment> appointments = appointmentService.getAppointments();
	        assertFalse(appointments.containsKey(appointmentId));
	        assertNull(appointments.get(appointmentId));
	    }
	    @Test
	    public void deleteNonexistentAppointmentTest() {
	        // Arrange
	        Long appointmentId = 1L;
	        
	        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

	        // Act
	        appointmentService.deleteAppointment(appointmentId);

	        // Assert
	        Map<Long, Appointment> appointments = appointmentService.getAppointments();
	        assertFalse(appointments.containsKey(appointmentId));
	        assertNull(appointments.get(appointmentId));
	    }

	    	
	    }
	    
	


	    
	


		





		