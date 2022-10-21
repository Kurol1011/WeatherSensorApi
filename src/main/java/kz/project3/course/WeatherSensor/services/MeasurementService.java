package kz.project3.course.WeatherSensor.services;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.project3.course.WeatherSensor.models.Measurement;

import kz.project3.course.WeatherSensor.repositories.MeasurementRepository;


@Service
@Transactional(readOnly = true)
public class MeasurementService {

	private final MeasurementRepository measurementRepository;
	private final SensorService sensorService;
	
	@Autowired
	public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
		this.measurementRepository = measurementRepository;
		this.sensorService= sensorService;
	}


	public List<Measurement> findAll(){
		return measurementRepository.findAll();
	}
	
	
	@Transactional
	public void addMeasurements(Measurement measurement) {
		
		enrichMeasurement(measurement);
		measurementRepository.save(measurement);
	}
	
	public long getRainyDaysCountSensorAll() {
		//int countRainyDays = 0;
		
		List<Measurement> listOfMeasurements = measurementRepository.findAll();
		
		return listOfMeasurements.stream().filter(c-> c.getRaining() == true).count();
	}
	
	public long getRainyDaysCountSensor(String sensorTitle) {
		//int countRainyDays = 0;
		
		List<Measurement> listOfMeasurements = measurementRepository.findBySensor(sensorService.findByTitle(sensorTitle));
		
		return listOfMeasurements.stream().filter(c-> c.getRaining() == true).count();
	}
	
	private void enrichMeasurement(Measurement measurement) {
		measurement.setSensor(sensorService.findByTitle(measurement.getSensor().getTitle()));
		measurement.setAddedAt(Date.valueOf(LocalDate.now()));
	}
	
	public List<Measurement> getAllMeasurementBySensorTitle(String sensorTitle){
		return measurementRepository.findBySensor(sensorService.findByTitle(sensorTitle));
	}
	
}
