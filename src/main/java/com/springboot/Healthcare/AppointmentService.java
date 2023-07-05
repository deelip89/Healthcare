package com.springboot.Healthcare;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
	private AppointmentRepository appointmentRepository;

	private Map<Long, Appointment> appointments = new HashMap<>();
	private Long nextAppointmentId= 1L;

	public Appointment addAppointment(Appointment appointment) {
         Long appointmentId = nextAppointmentId++;
        
        
        appointment.setAppointmentId(appointmentId);

       
        appointments.put(appointmentId, appointment);

     
     // TODO Auto-generated method stub
        return appointment;
						
		
}

	public Optional<Appointment> getAppointment(Long appointmentId) {
		// TODO Auto-generated method stub
		return  appointmentRepository.findById(appointmentId);
	}

	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

	