package com.workintech.s19d1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "movie",schema = "movie")
@NoArgsConstructor
public class Movie {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="director_name")
    private  String directorName;


    @NotNull
    @Column(name="rating")
    private double rating;


    @NotNull
    @Column(name="release_date")
    private LocalDate releaseDate;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="movie_actor",schema = "movie",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors;


    public void addActor(Actor actor){
        if(actors==null){
            actors=new ArrayList<>();
        }

        actors.add(actor);
    }


}
