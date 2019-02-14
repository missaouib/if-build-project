package com.bananayong.project.hello;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.capitalize;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String hello(@NotNull @RequestParam String name) {
        return "Hello " + capitalize(name.strip()) + ".";
    }

    @GetMapping(path = "/hello", params = "repeat")
    public String repeatHello(
        @NotNull
        @RequestParam String name,
        @RequestParam int repeat) {
        return hello(name).repeat(repeat);
    }
}
