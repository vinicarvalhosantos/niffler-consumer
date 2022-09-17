package br.com.vinicius.santos.nifflerconsumer.model.entity;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.EntityListeners;

import java.util.Date;

@Entity
@Table(name = "message_points")
@EntityListeners(AuditingEntityListener.class)
public class UserMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @NotNull
    private int messageLength;

    @Column(nullable = false)
    private Double pointsToBeAdded;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private boolean isSpam;

    @CreatedDate
    private Date createdAt;

    public UserMessageEntity(int messageLength, Double pointsToBeAdded, UserEntity user_sent, boolean isSpam) {
        this.messageLength = messageLength;
        this.pointsToBeAdded = pointsToBeAdded;
        this.user = user_sent;
        this.isSpam = isSpam;
    }

    public UserMessageEntity() {

    }
}
