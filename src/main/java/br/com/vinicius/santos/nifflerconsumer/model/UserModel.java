package br.com.vinicius.santos.nifflerconsumer.model;

public class UserModel {

    private Long id;

    private String username;

    private String displayName;

    private final Double pointsToAdd = 0.0;

    private final Double pointsAdded = 0.0;


    public UserModel(Long id, String username, String displayName) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getPointsToAdd() {
        return pointsToAdd;
    }

    public Double getPointsAdded() {
        return pointsAdded;
    }
}
