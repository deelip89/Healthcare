package com.springboot.Healthcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
	private Map<Long, Appointment> appointments = new HashMap<>();
	private Long nextAppointmentId = 1L;

	public Appointment addAppointment(Appointment appointment) {
		appointment.setAppointmentId(nextAppointmentId++);
		appointments.put(appointment.getAppointmentId(), appointment);
		return appointment;
	}

	public Appointment getAppointment(Long appointmentId) {
		return appointments.get(appointmentId);
	}

	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
		List<Appointment> patientAppointments = new ArrayList<>();
		for (Appointment appointment : appointments.values()) {
			if (appointment.getPatientId().equals(patientId)) {
				patientAppointments.add(appointment);
			}
		}
		return patientAppointments;
	}

	public Appointment deleteAppointment(Long appointmentId) {
		return appointments.remove(appointmentId);
	}

}
