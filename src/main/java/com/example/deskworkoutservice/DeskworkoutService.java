package com.example.deskworkoutservice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskworkoutService {
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
}
