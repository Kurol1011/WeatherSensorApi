package kz.project3.course.WeatherSensor.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
	@Min(value=-100,message = "value should be greater than -100 and smaller than 100")
	@Max(value = 100, message = "value should be greater than -100 and smaller than 100")
	private double value;
	
	@NotNull
	private boolean raining;
	
	private SensorDTO sensor;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public boolean getRaining() {
		return raining;
	}
	public void setRaining(boolean raining) {
		this.raining = raining;
	}
	public SensorDTO getSensor() {
		return sensor;
	}
	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}
	
	
}
