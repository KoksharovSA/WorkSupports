package ru.konsist.JenkinsService.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="request_jenkins_log")
@Data
public class RequestJenkinsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 2000)
    private String userName;

    @Column(nullable = false, length = 2000)
    private String chatId;

    @Column(length = 2000)
    private String request;

    @Column(length = 2000)
    private String response;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
}
