package kz.project3.course.WeatherSensor.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="sensors")
public class Sensor implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	@NotEmpty(message = "{title should not be empty}")
	@Size(min = 3, max = 30, message = "{Title should have 3 between 30 characters}")
	private String title;
	
	/*
	 * @OneToMany(mappedBy = "sensor",cascade = CascadeType.ALL, orphanRemoval =
	 * true) //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE) private
	 * List<Measurement> measurements;
	 */
	
	public Sensor() {}
	

	public Sensor(
		 String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
