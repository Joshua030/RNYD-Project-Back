package com.rnyd.rnyd.controller.workOut;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.service.workOutService.WorkOutService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@RestController
@RequestMapping("/workout")
public class WorkOutController {
    private final WorkOutService workOutService;

    public WorkOutController(WorkOutService dietServiceService) {
        this.workOutService = dietServiceService;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<String> createWorkOut(
            @RequestPart("workout") WorkOutDTO workoutDTO,
            @RequestPart("workoutPdf") MultipartFile pdfFile) {

        try {
            workoutDTO.setWorkoutPdf(pdfFile.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(ERROR_PDF, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String workoutResponse = workOutService.createWorkout(workoutDTO);

        if (workoutResponse != null) {
            return new ResponseEntity<>(workoutResponse, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(WORKOUT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }


    @PatchMapping
    public ResponseEntity<String> updateWorkout(@RequestBody WorkOutDTO workoutDTO){
        String workoutResponse = workOutService.updateWorkout(workoutDTO);

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(WORKOUT_NOT_UPDATED, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createWorkOut(@RequestBody WorkOutDTO workoutDTO){
        String workoutResponse = workOutService.createWorkout(workoutDTO);

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(WORKOUT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadWorkoutPdf(@PathVariable Long id) {
        WorkOutDTO workout = workOutService.getWorkOutById(id);

        if (workout == null || workout.getWorkoutPdf() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String fileName = (workout.getWorkoutName() != null ? workout.getWorkoutName().replaceAll("\\s+", "_") : "workout_" + id) + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename(fileName)
                .build());

        return new ResponseEntity<>(workout.getWorkoutPdf(), headers, HttpStatus.OK);
    }

    @PatchMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> updateWorkout(
            @RequestPart("workout") WorkOutDTO workoutDTO,
            @RequestPart("workoutPdf") MultipartFile pdfFile) {

        try {
            workoutDTO.setWorkoutPdf(pdfFile.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(ERROR_PDF, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String workoutResponse = workOutService.updateWorkout(workoutDTO);

        if (workoutResponse != null) {
            return new ResponseEntity<>(workoutResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(WORKOUT_NOT_UPDATED, HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long id){
        String workoutResponse = workOutService.deleteWorkout(id);

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(WORKOUT_NOT_DELETED, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/assign/{email}")
    public ResponseEntity<String> assignWorkout(@PathVariable String email, @RequestBody WorkOutDTO workoutDTO){
        String workoutResponse = workOutService.assignWorkout(email, workoutDTO);

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(WORKOUT_NOT_ASSIGNED, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOutDTO> getWorkoutById(@PathVariable Long id){
        WorkOutDTO workoutResponse = workOutService.getWorkOutById(id);

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<WorkOutDTO>> getAllWorkouts(){
        List<WorkOutDTO> workoutResponse = workOutService.getAllWorkouts();

        if(workoutResponse != null){
            return new ResponseEntity<>(workoutResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
