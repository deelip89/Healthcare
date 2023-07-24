package com.springboot.Healthcare;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Assertions;
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
    public void testAddAppointment_Success() {
        Appointment appointment = new Appointment();

        when(appointmentRepository.existsByDateAndTime(appointment.getDate(), appointment.getTime()))
            .thenReturn(false);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment result = appointmentService.addAppointment(appointment);

        assertNotNull(result.getAppointmentId());
        assertEquals(appointment, result);
    }

    @Test
    public void testAddAppointment_AppointmentExists() {
        Appointment appointment = new Appointment();

        when(appointmentRepository.existsByDateAndTime(appointment.getDate(), appointment.getTime()))
            .thenReturn(true);

        assertThrows(ValidationException.class, () -> appointmentService.addAppointment(appointment));
    }



	@Test
	public void testGetAppointment() {
		Long appointmentId = 1L;
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(appointmentId);

		when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

		Appointment result = appointmentService.getAppointment(appointmentId);
		assertEquals(appointmentId, result.getAppointmentId());
	}

	@Test
	public void testGetAppointment_Appointmentnptfound() {
		Long appointmentId = 2L;
		when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

		assertThrows(AppointmentNotFoundException.class, () -> appointmentService.getAppointment(appointmentId));
	}
	@Test
    public void testGetAppointment_InvalidAppointmentId() {
        long invalidAppointmentId = 0L;

        assertThrows(ValidationException.class, () -> appointmentService.getAppointment(invalidAppointmentId));
	}
	
	
		
	@Test
	public void getAppointmentsByPatientIdTest() {
		Long patientId = 1L;
		List<Appointment> expectedAppointments = Arrays.asList(new Appointment(), new Appointment());
		when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(expectedAppointments);

		List<Appointment> result = appointmentService.getAppointmentsByPatientId(patientId);

		assertNotNull(result);
		assertEquals(expectedAppointments, result);
	}
	
	@Test
	public void getAppointmentsByPatientIdTest_Appointmentnotfound() {
		Long patientId = 1L;
		when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(Collections.emptyList());
		assertThrows(AppointmentNotFoundException.class, () -> {appointmentService.getAppointmentsByPatientId(patientId);});
	}

	
	
	
	@Test
	void testGetAllAppointments() {
		List<Appointment> mockAppointments = new ArrayList<>();
		when(appointmentRepository.findAll()).thenReturn(mockAppointments);

		List<Appointment> result = appointmentService.getAllAppointments();
		assertEquals(mockAppointments, result);
	}
	
	@Test
	void testGetAllAppointments_AppointmentNotFoundException() {
		when(appointmentRepository.findAll()).thenReturn(null);

		assertThrows(AppointmentNotFoundException.class, () -> {appointmentService.getAllAppointments();});
	}
	
	
	
	@Test
	public void testDeleteById() {
		Long appointmentId = 1L;
		when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
		appointmentService.deleteById(appointmentId);
	}
	@Test
    public void testDeleteById_ValidationException() {
        Long invalidAppointmentId = -1L;
		when(appointmentRepository.existsById(invalidAppointmentId)).thenReturn(false);


        assertThrows(ValidationException.class,
                () -> appointmentService.deleteById(invalidAppointmentId));}

	@Test
	public void testDeleteById_Patientnotfoundexception() {
		Long nonExistentAppointmentId = 999L;
		when(appointmentRepository.existsById(nonExistentAppointmentId)).thenReturn(false);
		assertThrows(PatientNotFoundException.class, () -> {appointmentService.deleteById(nonExistentAppointmentId);});
	}
}
