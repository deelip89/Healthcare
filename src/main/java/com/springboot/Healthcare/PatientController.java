package com.springboot.Healthcare;
import java.util.List;
import java.util.Optional;

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
   private PatientRepository patientRepository;
  

   @PostMapping("/addpatient")
   public Patient addPatient(@RequestBody Patient patient) {
       return patientRepository.save(patient);
   }

   @GetMapping("/{id}")
   public Optional<Patient> getPatient(@PathVariable Long id) {
       return patientRepository.findById(id);
   }

   @GetMapping
   public List<Patient> getAllPatients() {
       return patientRepository.findAll();
   }

   @DeleteMapping("/{id}")
   public void deletePatient(@PathVariable Long id) {
       patientRepository.deleteById(id);
   }

   @PutMapping("/{id}")
   public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
       if (patientRepository.existsById(id)) {
           patient.setId(id);
           return patientRepository.save(patient);
       }
       return null;
   }
}



