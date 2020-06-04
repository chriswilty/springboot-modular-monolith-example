package com.example.momo.counting.web;

import com.example.momo.counting.service.CountingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("count")
public class CountingController {

    private final CountingService countingService;

    public CountingController(final CountingService countingService) {
        this.countingService = countingService;
    }

    @PostMapping("/plus")
    @ApiOperation("Adds one to the current count, and returns the new count")
    public ResponseEntity<Long> increment() {
        return ResponseEntity.status(HttpStatus.CREATED).body(countingService.increment());
    }

    @PostMapping("/minus")
    @ApiOperation("Subtracts one from the current count, and returns the new count")
    public ResponseEntity<Long> decrement() {
        return ResponseEntity.status(HttpStatus.CREATED).body(countingService.decrement());
    }

    @GetMapping
    @ApiOperation("Gets the current total")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(countingService.getCurrentValue());
    }

    @GetMapping("/sn")
    @ApiOperation("Gets the service number")
    public ResponseEntity<Integer> getServiceNumber() {
        return ResponseEntity.ok(countingService.serviceNumber());
    }

}
