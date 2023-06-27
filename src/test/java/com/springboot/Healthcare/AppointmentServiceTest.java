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
public class AppointmentServiceTest {
//@Autowired
private AppointmentService appointmentService;
	
	@BeforeEach
	public void setUp() {
        appointmentService = new AppointmentService();
    }
	@Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment(1L, 24L, "2023-06-30", "10:00 AM");
        Appointment addedAppointment = appointmentService.addAppointment(appointment);
        Map<Long, Appointment> appointments = appointmentService.getAppointments();
        assertNotNull(addedAppointment);
        assertNotNull(addedAppointment.getAppointmentId());
        Appointment getAppointment = appointments.get(addedAppointment.getAppointmentId());
        assertEquals(appointment.getPatientId(), getAppointment.getPatientId());
        assertEquals(appointment.getDate(), getAppointment.getDate());
        assertEquals(appointment.getTime(), getAppointment.getTime());
    }
	@Test
	public void addAppointmentTest_Negative_NullPatient() {
		AppointmentService appointmentService = new AppointmentService();
		Assertions.assertThrows(Exception.class, () -> {
			appointmentService.addAppointment(null);
		});
	
    }
	@Test 
	public void getAppointmentTest() {
		
		    Appointment appointment = new Appointment();
		    appointment.setPatientId(1L);
		    Appointment addedAppointment = appointmentService.addAppointment(appointment);
		    Long appointmentId = addedAppointment.getAppointmentId();
		    Appointment retrievedAppointment = appointmentService.getAppointment(appointmentId);
		    assertNotNull(retrievedAppointment);
		    assertEquals(appointmentId, retrievedAppointment.getAppointmentId());
		    assertEquals(1L, retrievedAppointment.getPatientId());

		
	}
	@Test
	public void getPatientTest_Negative_NonExistentPatient() {
			Long nonExistentId = 2L;
			Appointment result = appointmentService.getAppointment(nonExistentId);
			Assertions.assertNull(result);
		
	}
	@Test
	public void getAppointmentsByPatientIdTest() {
        Appointment appointment1 = new Appointment(1L, 1L, "2023-06-27", "10:00 AM");
        Appointment appointment2 = new Appointment(2L, 2L, "2023-06-28", "11:00 AM");
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(1L);
        Assertions.assertEquals(1, appointments.size());
        Assertions.assertEquals(1L, appointments.get(0).getPatientId());
    }
	@Test
	public void getAppointmentsByNonExistentPatientIdTest() {
	    Appointment appointment1 = new Appointment(1L, 1L, "2023-06-27", "10:00 AM");
	    Appointment appointment2 = new Appointment(2L, 2L, "2023-06-28", "11:00 AM");
	    appointmentService.addAppointment(appointment1);
	    appointmentService.addAppointment(appointment2);
	    List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(3L);
	    Assertions.assertTrue(appointments.isEmpty());
	}
	
	
	@Test
    public void deleteAppointmentTest() {
		  Appointment appointment1 = new Appointment();
	        appointment1.setAppointmentId(1L);
	        appointment1.setPatientId(1001L);
	        appointmentService.addAppointment(appointment1);
        Long appointmentIdToDelete = 1L;
        Appointment deletedAppointment = appointmentService.deleteAppointment(appointmentIdToDelete);
        Assertions.assertNotNull(deletedAppointment);
        Assertions.assertEquals(appointmentIdToDelete, deletedAppointment.getAppointmentId());
        Appointment nonExistingAppointment = appointmentService.getAppointment(appointmentIdToDelete);
        Assertions.assertNull(nonExistingAppointment);
    }
	
	@Test
	public void deleteNonExistingAppointmentTest() {
	Long nonExistingAppointmentId = 9999L;
	Appointment deletedAppointment = appointmentService.deleteAppointment(nonExistingAppointmentId);
	Assertions.assertNull(deletedAppointment);
	Appointment nonExistingAppointment = appointmentService.getAppointment(nonExistingAppointmentId);
	Assertions.assertNull(nonExistingAppointment);

	}
}
	

	
