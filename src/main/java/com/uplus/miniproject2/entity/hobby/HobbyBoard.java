package com.uplus.miniproject2.entity.hobby;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.uplus.miniproject2.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hobby_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String videoLink;

    @Enumerated(EnumType.STRING)
    private HobbyCategory hobbyCategory;
    private int thumbsUp;

    @Builder
    public HobbyBoard(User user, String title, String description, String videoLink, HobbyCategory hobbyCategory, int thumbsUp) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.videoLink = videoLink;
        this.hobbyCategory = hobbyCategory;
        this.thumbsUp = thumbsUp;
    }

    public void updateThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public void updateBoard(String title, String description, String hobbyCategory, String videoLink) {
        this.title = title;
        this.description = description;
        this.hobbyCategory = HobbyCategory.valueOf(hobbyCategory);
        this.videoLink = videoLink;
    }
}
