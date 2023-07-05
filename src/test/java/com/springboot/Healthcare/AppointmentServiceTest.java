package com.springboot.Healthcare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
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
		//Arranging 
		Appointment appointment = new Appointment();

        // Mock part
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.addAppointment(appointment);

        // Asserting
        assertEquals(appointment, result);
		        
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
    }
	@Test 
    public void getAppointmentsByPatientIdTest() {
		
    }

		
}




		