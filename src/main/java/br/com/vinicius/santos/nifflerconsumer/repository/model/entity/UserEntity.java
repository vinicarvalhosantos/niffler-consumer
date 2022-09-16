package br.com.vinicius.santos.nifflerconsumer.repository.model.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2091522841039993308L;

    @Id
    @Column(unique = true)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String displayName;

    @Column
    @ColumnDefault(value = "0")
    private Double pointsToAdd;

    @Column
    @ColumnDefault(value = "0")
    private Double pointsAdded;

    public UserEntity(Long id, String username, String displayName, Double pointsToAdd, Double pointsAdded) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.pointsToAdd = pointsToAdd;
        this.pointsAdded = pointsAdded;
    }

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public Double getPointsToAdd() {
        return pointsToAdd;
    }

    public void setPointsToAdd(Double pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }

    public Double getPointsAdded() {
        return pointsAdded;
    }

    public void setPointsAdded(Double pointsAdded) {
        this.pointsAdded = pointsAdded;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", pointsToAdd=" + pointsToAdd +
                ", pointsAdded=" + pointsAdded +
                '}';
    }
}
