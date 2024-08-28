package com.example.deskworkoutservice.controller;

import com.example.deskworkoutservice.entity.Deskworkout;
import com.example.deskworkoutservice.service.DeskworkoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserDeskworkoutController {

    private final DeskworkoutService deskworkoutService;

    public UserDeskworkoutController(DeskworkoutService deskworkoutService) {
        this.deskworkoutService = deskworkoutService;
    }

    // 利用者向けエンドポイント (取得)
    @GetMapping("/deskworkouts")
    public List<Deskworkout> findByBodyparts(@RequestParam(value = "bodyParts", required = false) String bodyParts) {
        return deskworkoutService.findByBodyPartsStartingWith(bodyParts);
    }

    @GetMapping("deskworkouts/{id}")
    public Deskworkout findByInformation(@PathVariable("id") int id) {
        return deskworkoutService.findById(id);
    }
}
