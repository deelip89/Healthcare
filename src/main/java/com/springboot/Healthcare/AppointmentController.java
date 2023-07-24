package com.springboot.Healthcare;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	

	@PostMapping("/add")
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return appointmentService.addAppointment(appointment);
	}
	 @GetMapping("/{appointmentId}")
	public Appointment getAppointment(@PathVariable Long appointmentId) {
        return appointmentService.getAppointment(appointmentId);
    }

		@GetMapping("/patient/{patientId}")
		public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
			return appointmentService.getAppointmentsByPatientId(patientId);
		}
		@GetMapping
		public List<Appointment>getAllAppointments(){
			return appointmentService.getAllAppointments();
			
		}
    	@DeleteMapping("/{appointmentId}")
    	public void  deleteAppointment(@PathVariable Long appointmentId) {
    		 appointmentService.deleteById(appointmentId);
	}
}
