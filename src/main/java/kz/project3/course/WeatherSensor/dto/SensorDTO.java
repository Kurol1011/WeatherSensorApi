package kz.project3.course.WeatherSensor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
	
	@NotEmpty(message = "title should not be empty")
	@Size(min = 3, max = 30, message = "Title should have 3 between 30 characters")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
