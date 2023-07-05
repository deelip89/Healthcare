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
//	private AppointmentService appointmentService;
	private AppointmentRepository appointmentRepository;

	@PostMapping("/add")
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@GetMapping("/{appointmentId}")
	public Optional<Appointment> getAppointment(@PathVariable Long appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

	@GetMapping("/patient/{patientId}")
	public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
		return appointmentRepository.findAll();
	}

	@DeleteMapping("/{appointmentId}")
	public void  deleteAppointment(@PathVariable Long appointmentId) {
		 appointmentRepository.deleteById(appointmentId);
	}
}
