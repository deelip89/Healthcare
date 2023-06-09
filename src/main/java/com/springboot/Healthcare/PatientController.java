package com.springboot.Healthcare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/addpatient")
	public Patient addpatient(@RequestBody Patient patient) {
		return patientService.addPatient(patient);
	}
	@GetMapping("/{id}")
	public Patient getPatient(@PathVariable Long id) {
		return patientService.getPatient(id);
		
	}
	@GetMapping
	public List<Patient>getAllPatients(){
		return patientService.getAllPatient();
	}

}
