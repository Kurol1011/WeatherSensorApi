package kz.project3.course.WeatherSensor.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kz.project3.course.WeatherSensor.dto.MeasurementDTO;
import kz.project3.course.WeatherSensor.models.Measurement;
import kz.project3.course.WeatherSensor.services.MeasurementService;
import kz.project3.course.WeatherSensor.util.ErrorResponse;
import kz.project3.course.WeatherSensor.util.MeasurementNotAddedException;


@RestController
public class MeasurementsController {
	
	private final ModelMapper modelMapper;
	private final MeasurementService measurementService;
	
	
	@Autowired
	public MeasurementsController( MeasurementService measurementService,ModelMapper modelMapper) {
		this.measurementService = measurementService;
		this.modelMapper = modelMapper;
	}



	
	
	@PostMapping("/measurements/add")
	public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			StringBuilder errorMsg = new StringBuilder();
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors) {
				errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
						.append(";");
			}
			
			throw new MeasurementNotAddedException(errorMsg.toString()); //TODO
		}
		measurementService.addMeasurements(convertToMeasurement(measurementDTO));
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/measurements")
	public List<MeasurementDTO> showAllMeasurements(){ //TODO
		return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/measurements/{sensorTitle}/rainyDaysCount")
	public long showRainyDaysCount(@PathVariable("sensorTitle") String sensorTitle){
		return measurementService.getRainyDaysCountSensor(sensorTitle);
	}
		
	@GetMapping("/measurements/rainyDaysCount")
	public long showRainyDaysCount(){
		return measurementService.getRainyDaysCountSensorAll();
	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException e){
		ErrorResponse response = new ErrorResponse(
				e.getMessage(),
				System.currentTimeMillis()
				);
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
//		Measurement measurement = new Measurement();
//		measurement.setValue(measurementDTO.getValue());
//		measurement.setRaining(measurementDTO.getRaining());
//		measurement.setSensor(measurementDTO.getSensor());
//		return measurement;
		return modelMapper.map(measurementDTO, Measurement.class);
	}
	
	private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
//		MeasurementDTO measurementDTO = new MeasurementDTO();
//		measurementDTO.setValue(measurement.getValue());
//		measurementDTO.setRaining(measurement.getRaining());
//		measurementDTO.setSensor(measurement.getSensor());
//		return measurementDTO;
		return modelMapper.map(measurement, MeasurementDTO.class);
	}
}
