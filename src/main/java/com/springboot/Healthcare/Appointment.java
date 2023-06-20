package com.springboot.Healthcare;

public class Appointment {

	private Long appointmentId;
	private Long patientId;
	private String date;
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
