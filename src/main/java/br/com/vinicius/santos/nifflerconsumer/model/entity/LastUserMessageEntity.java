package br.com.vinicius.santos.nifflerconsumer.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table(name = "last_user_message")
public class LastUserMessageEntity implements Serializable {
    private static final long serialVersionUID = -4867219131605730533L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column
    private String lastMessage;

    public LastUserMessageEntity(UserEntity user, String lastMessage) {
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public LastUserMessageEntity() {

    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
