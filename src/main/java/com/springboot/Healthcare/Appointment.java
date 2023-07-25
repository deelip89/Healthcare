package com.springboot.Healthcare;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Apointments", schema = "public")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long appointmentId;
	private Long patientId;
	private String date;
	private String time;
	@ManyToOne
	@JoinColumn(name = "patient_Details")
	private Patient patient;
  
	public Appointment() {
	}
	public Appointment(Long appointmentId, Long patientId, String date, String time, Patient patient) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.date = date;
		this.time = time;
		this.patient = patient;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
