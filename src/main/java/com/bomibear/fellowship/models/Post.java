package com.bomibear.fellowship.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;
    Date createdAt;

    @ManyToOne
    ApplicationUser applicationUser;

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public Post(){}

    public Post(String body, Date createdAt, ApplicationUser applicationUser){
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUser = applicationUser;
    }
}
