package com.springboot.Healthcare;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	private Map<Long, Appointment> appointments = new HashMap<>();
	private Long nextAppointmentId = 1L;

	public Appointment addAppointment(Appointment appointment) {
		Long appointmentId = nextAppointmentId++;

		appointment.setAppointmentId(appointmentId);
		appointments.put(appointmentId, appointment);

		// TODO Auto-generated method stub
		return appointment;
	}

	public Optional<Appointment> getAppointment(Long appointmentId) throws AppointmentNotFoundException {
		if (appointmentId <= 0) {
			throw new IllegalArgumentException();
		}

		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isEmpty()) {
			throw new AppointmentNotFoundException();
		}
		return optionalAppointment;
	}

	public List<Appointment> getAppointmentsByPatientId(Long patientId) throws IllegalArgumentException {
		if (patientId <= 0) {
			throw new IllegalArgumentException();
		}

		return appointmentRepository.findAllByPatientId(patientId);
	}

	public Map<Long, Appointment> getAppointments() {
		
		return appointments;
	}

	public Appointment deleteAppointment(Long appointmentId) throws AppointmentNotFoundException {
		if (appointmentId <= 0) {
			throw new IllegalArgumentException();
		}

		if (!appointments.containsKey(appointmentId)) {
			throw new AppointmentNotFoundException();
		}

		return appointments.remove(appointmentId);
	}
}

