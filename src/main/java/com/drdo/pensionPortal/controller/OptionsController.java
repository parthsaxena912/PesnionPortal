package com.drdo.pensionPortal.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class OptionsController {

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public void handleOptions() {
        // ✅ Sends a 200 OK response automatically
    }
}
