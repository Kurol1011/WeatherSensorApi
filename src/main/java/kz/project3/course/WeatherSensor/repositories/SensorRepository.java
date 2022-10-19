package kz.project3.course.WeatherSensor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.project3.course.WeatherSensor.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
	Optional <Sensor> findByTitle(String title);
}
