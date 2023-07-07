package com.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "notes")
@Setter
@Getter
public class Note {

    @Id
    private int id;
    private String title;
    private String content;
    private Date addedDate;

    public Note() {
        // Default Constructor:
    }

    public Note(int id, String title, String content, Date addedDate) {
        this.id = new Random().nextInt(100000);
        this.title = title;
        this.content = content;
        this.addedDate = addedDate;
    }
}
