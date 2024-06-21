package com.example.deskworkoutservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskworkoutService {
    @Autowired
    private final DeskworkoutMapper deskworkoutMapper;

    public DeskworkoutService(DeskworkoutMapper deskworkoutMapper) {
        this.deskworkoutMapper = deskworkoutMapper;
    }

    public List<Deskworkout> findByBodypartsStartingWith(String bodyparts) {
        if (bodyparts != null && !bodyparts.isEmpty()) {
            return deskworkoutMapper.findByBodypartsStartingWith(bodyparts);
        } else {
            return deskworkoutMapper.findAll();
        }
    }

    public Deskworkout findById(int id) {
        Optional<Deskworkout> deskworkout = this.deskworkoutMapper.findById(id);
        if (deskworkout.isPresent()) {
            return deskworkout.get();
        } else {
            throw new DeskworkoutNotFoundException("Workout with id:" + id + " not found");
        }
    }


    public Deskworkout insert(String name, String howto, Integer repetition, String bodyparts, String difficulty) {
        Deskworkout deskworkout = new Deskworkout();
        deskworkout.setName(name);
        deskworkout.setHowto(howto != null && !howto.isEmpty() ? howto : "未設定");
        deskworkout.setRepetition(repetition != null ? repetition : 0);
        deskworkout.setBodyparts(bodyparts != null && !bodyparts.isEmpty() ? bodyparts : "未設定");
        deskworkout.setDifficulty(difficulty != null && !difficulty.isEmpty() ? difficulty : "未設定");
        deskworkoutMapper.insert(deskworkout);
        return deskworkout;
    }
}
