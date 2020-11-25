package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskiesByYear(){
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByYear(2018);
		assertEquals(6, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesByRegion(){
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleriesByRegion("Islay");
		assertEquals(2, foundDistilleries.size());
	}

	@Test
	public void canGetWhiskiesByDistilleryNameAndAge(){
		Distillery foundDistillery = distilleryRepository.getOne(5L);
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByDistilleryNameAndAge("Highland Park", 12);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canGetWhiskiesByDistilleryRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByDistilleryRegion("Highland");
		assertEquals(7, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesByWhiskiesAge(){
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleriesByWhiskiesAge(12);
		assertEquals(6,foundDistilleries.size());
	}

}
