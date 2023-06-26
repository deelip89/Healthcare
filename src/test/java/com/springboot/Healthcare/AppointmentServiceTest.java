package com.springboot.Healthcare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class AppointmentServiceTest {
@Autowired
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
	@Test void testGetAppointment() {
		
	}
}
	
	
	