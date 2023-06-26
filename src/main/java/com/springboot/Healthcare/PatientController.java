package com.springboot.Healthcare;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@DeleteMapping("/{id}")
	public Patient deletePatient(@PathVariable Long id) {
		return patientService.deletePatient(id);
		
	}
	@PutMapping("/{id}")
	public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
		return patientService.updatePatient(id, patient);
	}

}
