package com.example.momo.echo.web;

import com.example.momo.echo.service.EchoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("echo")
public class EchoController {

    private final EchoService echoService;

    public EchoController(final EchoService echoService) {
        this.echoService = echoService;
    }

    @PutMapping
    @ApiOperation("Echo a message back to the client")
    public ResponseEntity<String> echo(@RequestBody String message) {
        return ResponseEntity.ok(message);
    }

    @GetMapping("/sn")
    @ApiOperation("Service number")
    public ResponseEntity<Integer> getServiceNumber() {
        return ResponseEntity.ok(this.echoService.serviceNumber());
    }

}
