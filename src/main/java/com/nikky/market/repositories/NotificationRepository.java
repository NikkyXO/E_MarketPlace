package com.nikky.market.repositories;

import com.nikky.market.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {

}
