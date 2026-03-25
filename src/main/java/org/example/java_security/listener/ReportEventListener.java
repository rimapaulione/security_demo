package org.example.java_security.listener;


import lombok.RequiredArgsConstructor;
import org.example.java_security.event.UserDeleteEvent;
import org.example.java_security.event.UserRegisteredEvent;
import org.example.java_security.event.UserRoleChangeEvent;
import org.example.java_security.model.Report;
import org.example.java_security.model.UserActionType;
import org.example.java_security.repository.ReportRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReportEventListener {

    private final ReportRepository reportRepository;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onRegistered(UserRegisteredEvent event) {
        save(event.userId(), UserActionType.REGISTERED, "User '" +
                event.username() + "' registered", event.performedBy(), event.date());
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onDelete(UserDeleteEvent event) {
        save(event.userId(), UserActionType.DELETED, "User '" +
                event.username() + "' deleted", event.performedBy(), event.date());
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onRoleChange(UserRoleChangeEvent event) {
        save(event.userId(), UserActionType.CHANGED, "User '" +
                event.username() + "' changed role to " +
                event.newRole(), event.performedBy(), event.date());
    }

    private void save(Long userId, UserActionType status, String description,
                      String performedBy, LocalDateTime date) {
        reportRepository.save(Report.builder()
                .userId(userId)
                .status(status)
                .description(description)
                .performedBy(performedBy)
                .date(date)
                .build());
    }
}
