package com.springboot.Healthcare;

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
		if (appointmentRepository.existsByDateAndTime(appointment.getDate(), appointment.getTime())) {
            throw new ValidationException("Appointment with the same date and time already exists exists.");
        }	
		
		appointment.setAppointmentId(nextAppointmentId++);
		appointments.put(appointment.getAppointmentId(), appointment);
		return appointmentRepository.save(appointment);
	}
	

	public Appointment getAppointment(Long appointmentId) {
		if (appointmentId <= 0) {
	        throw new ValidationException("Appointment Id should not be 0 or less then 0.");
	    }
		return appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
		
	}
	

	
	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
		
		List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);

		if (appointments.isEmpty()) {
			throw new AppointmentNotFoundException("Appointments not found for patient with ID: " + patientId);
		}
		
		return appointmentRepository.findAllByPatientId(patientId);
	}
	
	

	
	
	public List<Appointment> getAllAppointments() {

		List<Appointment> appointments = appointmentRepository.findAll();

		return Optional.ofNullable(appointments)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointments not found."));
	}
	
	
	

	public void deleteById(Long appointmentId) {
		if (appointmentId == null || appointmentId <= 0) {
	        throw new ValidationException("Invalid appointment ID. Please provide a valid positive ID.");
	    }
		if (!appointmentRepository.existsById(appointmentId)) {

			throw new PatientNotFoundException("Patient with ID " + appointmentId + " not found.");
		}
		appointmentRepository.deleteById(appointmentId);
	}

}


