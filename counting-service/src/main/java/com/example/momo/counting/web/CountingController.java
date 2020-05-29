package com.example.momo.counting.web;

import com.example.momo.counting.service.CountingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/plus")
    @ApiOperation("Adds one to the current count, and returns the new count")
    public ResponseEntity<Long> increment() {
        return ResponseEntity.ok(countingService.increment());
    }

    @PutMapping("/minus")
    @ApiOperation("Subtracts one from the current count, and returns the new count")
    public ResponseEntity<Long> decrement() {
        return ResponseEntity.ok(countingService.decrement());
    }

    @GetMapping
    @ApiOperation("Gets the current total")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.countingService.getCount());
    }

    @GetMapping("/sn")
    @ApiOperation("Gets the service number")
    public ResponseEntity<Integer> getServiceNumber() {
        return ResponseEntity.ok(this.countingService.serviceNumber());
    }

}
