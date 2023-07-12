package com.springboot.Healthcare;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    
    @InjectMocks
    private AppointmentService appointmentService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addAppointmentTest() {
        // Arrange
        Appointment appointment = new Appointment();
        
        // Act
        Appointment result = appointmentService.addAppointment(appointment);
        
        // Assert
        assertNotNull(result);
        assertNotNull(result.getAppointmentId());
        assertEquals(appointment, result);
        
        Map<Long, Appointment> appointments = appointmentService.getAppointments();
        assertNotNull(appointments);
        assertTrue(appointments.containsKey(result.getAppointmentId()));
        assertEquals(appointment, appointments.get(result.getAppointmentId()));
    }
    
    @Test
    void addAppointmentTest_NullAppointment() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> {
            appointmentService.addAppointment(null);
        });
    }

    @Test
    void getAppointmentTest() throws AppointmentNotFoundException {
        // Arrange
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
        
        // Act
        Optional<Appointment> result = appointmentService.getAppointment(appointmentId);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
    }
    
    @Test
    void getAppointmentTest_InvalidAppointmentId() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment(-1L);
        });
    }
    
    @Test
    void getAppointmentTest_AppointmentNotFound() {
        // Arrange
        Long appointmentId = 1L;
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());
        
        // Act and Assert
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.getAppointment(appointmentId);
        });
    }

    @Test
    void getAppointmentsByPatientIdTest() {
        // Arrange
        Long patientId = 1L;
        List<Appointment> expectedAppointments = Arrays.asList(new Appointment(), new Appointment());
        when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(expectedAppointments);
        
        // Act
        List<Appointment> result = appointmentService.getAppointmentsByPatientId(patientId);
        
        // Assert
        assertNotNull(result);
        assertEquals(expectedAppointments, result);
    }
    
    @Test
    void getAppointmentsByPatientIdTest_InvalidPatientId() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointmentsByPatientId(-1L);
        });
    }
    
    @Test
    void deleteAppointmentTest() throws AppointmentNotFoundException {
        // Arrange
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointmentService.addAppointment(appointment);
        
        // Act
        Appointment result = appointmentService.deleteAppointment(appointmentId);
        
        // Assert
        assertNotNull(result);
        assertEquals(appointment, result);
        assertFalse(appointmentService.getAppointments().containsKey(appointmentId));
    }
    
    @Test
    void deleteAppointmentTest_InvalidAppointmentId() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment(-1L);
        });
    }
    
    @Test
    void deleteAppointmentTest_AppointmentNotFound() {
        // Act and Assert
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.deleteAppointment(1L);
        });
    }
}





