package com.example.deskworkoutservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class DeskworkoutController {

    private final DeskworkoutService deskworkoutService;

    public DeskworkoutController(DeskworkoutService deskworkoutService) {
        this.deskworkoutService = deskworkoutService;
    }

    @GetMapping("/deskworkouts")
    public List<Deskworkout> findByBodyparts(@RequestParam(value = "bodyparts", required = false) String bodyparts) {
        return deskworkoutService.findByBodypartsStartingWith(bodyparts);
    }

    @GetMapping("/deskworkouts/{id}")
    public Deskworkout findByInformation(@PathVariable("id") int id) {
        return deskworkoutService.findById(id);
    }

    @ExceptionHandler(value = DeskworkoutNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDeskworkoutNotFoundException(
            DeskworkoutNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/deskworkouts")
    public ResponseEntity<DeskworkoutResponse> insert(@RequestBody DeskworkoutRequest deskworkoutRequest, UriComponentsBuilder uriBuilder) {
        Deskworkout deskworkout = deskworkoutService.insert(
                deskworkoutRequest.getName(),
                deskworkoutRequest.getHowto(),
                deskworkoutRequest.getRepetition(),
                deskworkoutRequest.getBodyparts(),
                deskworkoutRequest.getDifficulty()
        );
        URI location = uriBuilder.path("/deskworkouts/{id}").buildAndExpand(deskworkout.getId()).toUri();
        DeskworkoutResponse body = new DeskworkoutResponse("deskworkout created");
        return ResponseEntity.created(location).body(body);
    }

    @PatchMapping("/deskworkouts/{id}")
    public ResponseEntity<DeskworkoutResponse> update(@PathVariable("id") int id, @RequestBody DeskworkoutRequest deskworkoutRequest) {
        deskworkoutService.update(
                id,
                deskworkoutRequest.getName(),
                deskworkoutRequest.getHowto(),
                deskworkoutRequest.getRepetition(),
                deskworkoutRequest.getBodyparts(),
                deskworkoutRequest.getDifficulty());
        DeskworkoutResponse body = new DeskworkoutResponse("deskworkout updated");
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/deskworkouts/{id}")
    public ResponseEntity<DeskworkoutResponse> delete(@PathVariable int id) {
        deskworkoutService.delete(id);
        DeskworkoutResponse body = new DeskworkoutResponse("deskworkout deleted");
        return ResponseEntity.ok(body);
    }
}
