package kz.project3.course.WeatherSensor.util;

public class MeasurementNotAddedException extends RuntimeException{
	public MeasurementNotAddedException(String msg) {
		super(msg);
	}
}
