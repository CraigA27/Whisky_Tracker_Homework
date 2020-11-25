package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(value = "/whiskies")
//    public ResponseEntity<List<Whisky>> getAllWhiskies(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region",required = false) String region
    ){
        if(year != null && name == null && age == null && region == null){
            return new ResponseEntity(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if(year == null && name != null && age != null && region == null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryNameAndAge(name, age), HttpStatus.OK);
        }
        if(year == null && name == null && age == null && region != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping(value = "whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id){
        return new ResponseEntity(whiskyRepository.findById(id), HttpStatus.OK);
    }



}
