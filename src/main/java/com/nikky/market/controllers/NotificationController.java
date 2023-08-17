package com.nikky.market.controllers;


import com.nikky.market.entities.CreateNotificationRequest;
import com.nikky.market.entities.Notification;
import com.nikky.market.services.NotificationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping("")
    public ResponseEntity<Notification> createNotification(
            @RequestBody CreateNotificationRequest request, @RequestParam Long id) {

        return ResponseEntity.ok().body(notificationService.createUserNotification(request, id));
    }

    @PutMapping(value = "/notification/{id}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable("id") @Min(1) Long id,
            @Valid @RequestBody CreateNotificationRequest request) {
        return ResponseEntity.ok().body(notificationService.updateNotification(id, request));

    }
}
