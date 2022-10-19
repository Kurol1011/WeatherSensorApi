package kz.project3.course.WeatherSensor.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.project3.course.WeatherSensor.models.Measurement;
import kz.project3.course.WeatherSensor.models.Sensor;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
	List<Measurement> findBySensor(Sensor sensorTitle);
}
