package com.example.deskworkoutservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskworkoutService {
    @Autowired
    private final DeskworkoutMapper deskworkoutMapper;

    public DeskworkoutService(DeskworkoutMapper deskworkoutMapper) {
        this.deskworkoutMapper = deskworkoutMapper;
    }

    public List<Deskworkout> findByBodyPartsStartingWith(String bodyParts) {
        if (bodyParts != null && !bodyParts.isEmpty()) {
            return deskworkoutMapper.findByBodyPartsStartingWith(bodyParts);
        } else {
            return deskworkoutMapper.findAll();
        }
    }

    public Deskworkout findById(int id) {
        return deskworkoutMapper.findById(id)
                .orElseThrow(() -> new DeskworkoutNotFoundException("Workout with id:" + id + " not found"));
    }

    public Deskworkout insert(String name, String howTo, Integer repetition, String bodyParts, String difficulty) {
        Deskworkout deskworkout = new Deskworkout();
        deskworkout.setName(name);
        deskworkout.setHowto(howTo);
        deskworkout.setRepetition(repetition);
        deskworkout.setBodyParts(bodyParts);
        deskworkout.setDifficulty(difficulty);
        deskworkoutMapper.insert(deskworkout);
        return deskworkout;
    }

    public Deskworkout update(Integer id, String name, String howTo, Integer repetition, String bodyParts, String difficulty) {
        Deskworkout deskworkout = deskworkoutMapper.findById(id)
                .orElseThrow(() -> new DeskworkoutNotFoundException("Workout with id:" + id + " not found"));

        deskworkout.setName(name);
        deskworkout.setHowto(howTo);
        deskworkout.setRepetition(repetition);
        deskworkout.setBodyParts(bodyParts);
        deskworkout.setDifficulty(difficulty);

        deskworkoutMapper.update(deskworkout);
        return deskworkout;
    }

    public void delete(int id) {
        deskworkoutMapper.findById(id)
                .orElseThrow(() -> new DeskworkoutNotFoundException("Workout with id:" + id + " not found"));
        deskworkoutMapper.delete(id);
    }
}
