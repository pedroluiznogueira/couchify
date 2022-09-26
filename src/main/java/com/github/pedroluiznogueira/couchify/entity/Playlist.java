package com.github.pedroluiznogueira.couchify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class Playlist  {
    private String id;
    private String name;
    private Owner owner;
    private Date created;
    private List<String> tracks;
    private Date updated;
    private String type;
    private String visibility;

    public Playlist() {
        this.type = "playlist";
    }

    public Playlist(String playlistName, String ownerFirstName, String ownerLastname, String ownerUsername) {
        this.name = playlistName;
        this.owner = new Owner(ownerFirstName, ownerLastname, ownerUsername);
        this.type = "playlist";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonIgnore
    public String getClassName() {
        return getClass().getName();
    }

    /**
     * Generate a key using elements of the entity as TYPE::ID
     *
     * @return Generated key
     */
    public String genKey() {
        return getType() + "::" + getId();
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", created=" + created +
                ", tracks=" + tracks +
                ", updated=" + updated +
                ", type='" + type + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
