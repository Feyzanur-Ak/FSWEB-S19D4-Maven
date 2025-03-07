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
@Table(name = "actor",schema="movie")
@NoArgsConstructor
public class Actor {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @Column(name="first_name")
   private  String  firstName;

    @NotNull
    @Column(name="last_name")
    private  String  lastName;


    @Enumerated
    @Column(name="gender")
   private  Gender gender;

    @Column(name="birth_date")
   private LocalDate birthDate;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="movie_actor",schema = "movie",
    joinColumns = @JoinColumn(name="actor_id"),
    inverseJoinColumns = @JoinColumn(name="movie_id"))
    private List<Movie> movies;


    public void addMovie(Movie movie){
        if(movies==null){
            movies=new ArrayList<>();
        }
        movies.add(movie);
    }

}
