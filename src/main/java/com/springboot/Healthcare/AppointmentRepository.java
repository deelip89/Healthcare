package com.springboot.Healthcare;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findAllByPatientId(Long patientId);

	

	boolean existsByDateAndTime(String date, String time);

	

	

}
