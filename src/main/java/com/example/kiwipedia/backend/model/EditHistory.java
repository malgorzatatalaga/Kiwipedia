package com.example.kiwipedia.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "edit_history")
public class EditHistory {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public UserEntity getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(UserEntity editedBy) {
        this.editedBy = editedBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "old_text", columnDefinition = "TEXT")
    private String oldText;

    @Column(name = "new_text", columnDefinition = "TEXT")
    private String newText;

    @Column(name = "edit_time")
    private LocalDateTime editTime;

    @ManyToOne
    @JoinColumn(name = "edited_by")
    private UserEntity editedBy;
}
