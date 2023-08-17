package com.nikky.market.services;

import com.nikky.market.entities.CreateNotificationRequest;
import com.nikky.market.entities.Notification;
import com.nikky.market.entities.User;
import com.nikky.market.errorHandling.errorClasses.ResourceNotFoundException;
import com.nikky.market.repositories.NotificationRepository;
import com.nikky.market.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Map;

@Service
@NoArgsConstructor
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public Notification createUserNotification(CreateNotificationRequest request, Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user != null) {
            var notification = Notification
                    .builder().createdAt(new Date())
                    .user(user)
                    .modifiedAt(new Date())
                    .isRead(false)
                    .message(request.getMessage())
                    .build();

            return notificationRepository.save(notification);
        }
        return null;
    }

    public Notification updateNotification(Long id,  CreateNotificationRequest request) {

        Notification getNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification with ID :" + id + " Not Found!"));

        getNotification.setMessage(request.getMessage());

        return notificationRepository.save(getNotification);
    }



}
