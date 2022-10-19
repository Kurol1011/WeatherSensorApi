package kz.project3.course.WeatherSensor.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="measurements")
public class Measurement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Min(value=-100,message = "value should be greater than -100 and smaller than 100")
	@Max(value = 100, message = "value should be greater than -100 and smaller than 100")
	@Column(name = "value")
	private double value;
	
	@NotNull
	@Column(name = "raining")
	private boolean raining;
	
	@ManyToOne
	@JoinColumn(name = "sensor", referencedColumnName = "title")
	private Sensor sensor;
	
	
	public Sensor getSensor() {
		return sensor;
	}



	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}



	@Column(name="added_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedAt;

	
	public Measurement() {}
	
	
	
	public Measurement(double value, boolean raining) {
		this.value = value;
		this.raining = raining;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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


	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	
	
	
	
}
