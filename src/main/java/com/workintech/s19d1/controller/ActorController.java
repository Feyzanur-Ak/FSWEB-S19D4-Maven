package com.workintech.s19d1.controller;


import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {

    private final  ActorService actorService;

    @GetMapping()
    public List<ActorResponse> findAll(){
       List<Actor> actors= actorService.findAll();
       return Converter.actorResponseConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse getById(@PathVariable long id){
        Actor actor=actorService.findById(id);

      return  Converter.actorResponseConvert(actor);
    }

    //ManytoMany ilişki var

    @PostMapping()
    public  ActorResponse save(@Validated @RequestBody ActorRequest actorDao){
        Actor actor=actorDao.getActor();
        List<Movie> movies=actorDao.getMovies();
        for(Movie movie : movies){
            actor.addMovie(movie);
        }

        Actor savedActor=actorService.save(actor);
        return  Converter.actorCreateResponseConvert(actor);

    }

    @PutMapping("/{id}")
    public  ActorResponse update(@PathVariable Long id, @RequestBody Actor actor){
        Actor foundActor=actorService.findById(id);
        actor.setMovies((foundActor.getMovies()));
        actor.setId(id);
        actorService.save(actor);

        return  Converter.actorResponseConvert((actor));

    }


    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id){

        Actor removedActor=actorService.findById(id);
        actorService.delete(removedActor);
        return Converter.actorResponseConvert(removedActor);
    }
}
