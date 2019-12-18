package com.teleone.mytele.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractController {
    ResponseEntity<String> booleanResponse(boolean result) {
        if (!result) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
