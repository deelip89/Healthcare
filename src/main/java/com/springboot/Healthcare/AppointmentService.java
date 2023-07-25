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
	@Autowired
    private PatientRepository patientRepository; 

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
		 Appointment appointment =appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
		Long patientId = appointment.getPatientId();
        if (patientId != null) {
            
        	Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));
            appointment.setPatient(patient);
        }

        return appointment;
    }
	
	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
		
		List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);

		if (appointments.isEmpty()) {
			throw new AppointmentNotFoundException("Appointments not found for patient with ID: " + patientId);
		}
		
		Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));

       
        for (Appointment appointment : appointments) {
            appointment.setPatient(patient);
        }

        return appointments;
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


