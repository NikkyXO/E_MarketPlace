package com.nikky.market.repositories;

import com.nikky.market.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {


    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
   Page<Notification> findByUser(Long userId, final Pageable pageable);
}
