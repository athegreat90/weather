package com.training.weatherservice.controller;

import com.training.weatherservice.dto.ResponseGenericDto;
import com.training.weatherservice.dto.WeatherDataDto;
import com.training.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController
{

    private final WeatherService weatherService;

    @GetMapping("")
    public ResponseEntity<ResponseGenericDto<List<WeatherDataDto>>> getData(@RequestParam(name = "date", required = false) String date)
    {
        List<WeatherDataDto> response;
        if (date == null || date.isEmpty())
        {
            log.info("Execute all");
            response = this.weatherService.getAll();
        }
        else
        {
            log.info("Execute by date: {}", date);
            response = this.weatherService.getByDate(date);
        }
        log.info("Result: {}", response.size());
        if (response.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var responseDto = new ResponseGenericDto<>(0, "OK", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGenericDto<WeatherDataDto>> getById(@PathVariable(name = "id") Long id)
    {
        var response = this.weatherService.getById(id);
        if (response == null)
        {
            return ResponseEntity.notFound().build();
        }
        var responseDto = new ResponseGenericDto<>(0, "OK", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseGenericDto<String>> post(@RequestBody WeatherDataDto body)
    {
        var resultSave = this.weatherService.save(body);
        if (resultSave == null)
        {
            return ResponseEntity.status(400).build();
        }
        var response = new ResponseGenericDto<>(0, "OK", resultSave);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGenericDto<WeatherDataDto>> delete(@PathVariable(name = "id") Long id)
    {
        var resultDelete = this.weatherService.delete(id);
        if (resultDelete == null)
        {
            return ResponseEntity.notFound().build();
        }
        var response = new ResponseGenericDto<>(0, "OK", resultDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseGenericDto<String>> deleteAll()
    {
        this.weatherService.deleteAll();
        var response = new ResponseGenericDto<>(0, "OK", "Delete all");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
