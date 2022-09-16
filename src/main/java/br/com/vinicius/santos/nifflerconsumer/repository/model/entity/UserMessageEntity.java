package br.com.vinicius.santos.nifflerconsumer.repository.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "message_points")
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

    public UserMessageEntity(int messageLength, Double pointsToBeAdded, UserEntity user_sent, boolean isSpam) {
        this.messageLength = messageLength;
        this.pointsToBeAdded = pointsToBeAdded;
        this.user = user_sent;
        this.isSpam = isSpam;
    }

    public UserMessageEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public Double getPointsToBeAdded() {
        return pointsToBeAdded;
    }

    public void setPointsToBeAdded(Double pointsToBeAdded) {
        this.pointsToBeAdded = pointsToBeAdded;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user_sent) {
        this.user = user_sent;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }
}
