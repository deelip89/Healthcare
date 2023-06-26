package com.springboot.Healthcare;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
	private Map<Long, Appointment> appointments = new HashMap<>();
	private Long nextAppointmentId = 1L;
	public Appointment addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointment.setAppointmentId(nextAppointmentId++);
        appointments.put(appointment.getAppointmentId(), appointment);
        return appointment;
        
	
	}
	public Map<Long, Appointment> getAppointments(){
		return appointments;
	}
	
	public Appointment getAppointment(Long appointmentId) {
		// TODO Auto-generated method stub
		
		return null;
	}
	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Appointment deleteAppointment(Long appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
