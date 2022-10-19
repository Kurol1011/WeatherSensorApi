package kz.project3.course.WeatherSensor.controllers;

import java.util.List;

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

import kz.project3.course.WeatherSensor.dto.SensorDTO;
import kz.project3.course.WeatherSensor.models.Sensor;
import kz.project3.course.WeatherSensor.services.SensorService;
import kz.project3.course.WeatherSensor.util.ErrorResponse;
import kz.project3.course.WeatherSensor.util.SensorNotCreatedException;
import kz.project3.course.WeatherSensor.util.SensorNotFoundException;

@RestController
public class SensorsController {
	
	private final ModelMapper modelMapper;
	private final SensorService sensorService;
	
	
	@Autowired
	public SensorsController(ModelMapper modelMapper, SensorService sensorService) {
		this.modelMapper = modelMapper;
		this.sensorService = sensorService;
	}


	@PostMapping("/sensors/registration")
	public ResponseEntity<HttpStatus> registrateSensor(@RequestBody @Valid SensorDTO sensorDTO,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			StringBuilder errorMsg = new StringBuilder();
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors) {
				errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
						.append(";");
			}
			
			throw new SensorNotCreatedException(errorMsg.toString());
			
	}
		sensorService.createNewSensor(convertToSensor(sensorDTO));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	
	@GetMapping("/sensors/{title}")
	public SensorDTO showSensorByTitle(@PathVariable("title") String title){
		return convertToSensorDTO(sensorService.findByTitle(title));
	}
	
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e){
		ErrorResponse response = new ErrorResponse(
				"Sensor with this title not found",
				System.currentTimeMillis()
				);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e){
		ErrorResponse response = new ErrorResponse(
				e.getMessage(),
				System.currentTimeMillis()
				);
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	

	private Sensor convertToSensor(SensorDTO sensorDTO) {
		return modelMapper.map(sensorDTO, Sensor.class);
	}
	
	private SensorDTO convertToSensorDTO(Sensor sensor) {
		return modelMapper.map(sensor, SensorDTO.class);
	}
}

