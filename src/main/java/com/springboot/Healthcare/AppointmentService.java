package com.springboot.Healthcare;

import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    private Map<Long, Appointment> appointments = new HashMap<>();
    private Long nextAppointmentId = 1L;

    public Appointment addAppointment(Appointment appointment) throws AppointmentAlreadyExistsException {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment can't be null.");
        }

        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new AppointmentAlreadyExistsException("Appointment with id: " + appointment.getAppointmentId() + " already exists.");
        }

        Long appointmentId = nextAppointmentId++;
        appointment.setAppointmentId(appointmentId);
        appointments.put(appointmentId, appointment);

        return appointment;
    }

    public Optional<Appointment> getAppointment(Long appointmentId) throws AppointmentNotFoundException {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Apointment id can't be null.");
        }

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
        }

        return appointment;
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        if (patientId == null) {
            throw new IllegalArgumentException("Patient ID cannot be null.");
        }

        return appointmentRepository.findAllByPatientId(patientId);
    }

    public Map<Long, Appointment> getAppointments() {
        return appointments;
    }

    public Appointment deleteAppointment(Long appointmentId) throws AppointmentNotFoundException {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null.");
        }

        if (!appointments.containsKey(appointmentId)) {
            throw new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
        }

        return appointments.remove(appointmentId);
    }

   
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ResponseEntity<String> handleAppointmentAlreadyExistsException(AppointmentAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> AppointmentNotFoundException(AppointmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}


//@Service
//public class AppointmentService {
//
//	@Autowired
//	private AppointmentRepository appointmentRepository;
//
//	private Map<Long, Appointment> appointments = new HashMap<>();
//	private Long nextAppointmentId = 1L;
//
//	public Appointment addAppointment(Appointment appointment) {
//		Long appointmentId = nextAppointmentId++;
//
//		appointment.setAppointmentId(appointmentId);
//		appointments.put(appointmentId, appointment);
//
//		// TODO Auto-generated method stub
//		return appointment;
//	}
//
//	public Optional<Appointment> getAppointment(Long appointmentId) throws AppointmentNotFoundException {
//		if (appointmentId <= 0) {
//			throw new IllegalArgumentException();
//		}
//
//		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
//		if (optionalAppointment.isEmpty()) {
//			throw new AppointmentNotFoundException();
//		}
//		return optionalAppointment;
//	}
//
//	public List<Appointment> getAppointmentsByPatientId(Long patientId) throws IllegalArgumentException {
//		if (patientId <= 0) {
//			throw new IllegalArgumentException();
//		}
//
//		return appointmentRepository.findAllByPatientId(patientId);
//	}
//
//	public Map<Long, Appointment> getAppointments() {
//		
//		return appointments;
//	}
//
//	public Appointment deleteAppointment(Long appointmentId) throws AppointmentNotFoundException {
//		if (appointmentId <= 0) {
//			throw new IllegalArgumentException();
//		}
//
//		if (!appointments.containsKey(appointmentId)) {
//			throw new AppointmentNotFoundException();
//		}
//
//		return appointments.remove(appointmentId);
//	}
//}
//
