package com.bananayong.project.hello;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.springframework.http.ResponseEntity.badRequest;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String hello(@NotNull @Valid NameParam nameParam) {
        var message = "Hello " + capitalize(nameParam.getName().strip()) + ".";
        return message.repeat(nameParam.repeat);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BindException e) {
        //noinspection ConstantConditions
        var field = e.getFieldError().getField();
        var message = "BadRequest. wrong parameter: " + field;
        return badRequest().body(message);
    }

    @Data
    private static class NameParam {
        @Length(min = 2)
        String name;
        @Min(1)
        int repeat = 1;
    }
}
