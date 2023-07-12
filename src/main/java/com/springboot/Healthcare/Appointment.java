package com.springboot.Healthcare;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Apointments", schema = "public")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="appointment_id")
	private Long appointmentId;
	@Column(name="patient_id")
	private Long patientId;
	@Column(name="date")
	private String date;
	@Column(name="time")
	private String time;

	public Appointment() {
	}
	public Appointment(Long appointmentId, Long patientId, String date, String time) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.date = date;
		this.time = time;
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
