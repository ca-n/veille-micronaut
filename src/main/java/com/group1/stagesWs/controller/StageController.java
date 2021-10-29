package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.service.CVService;
import com.group1.stagesWs.service.EmailService;
import com.group1.stagesWs.service.StageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StageController {

    @Autowired
    private StageService stageService;

    @Autowired
    private CVService cvService;

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/stage/cv")
    public ResponseEntity<CV> saveCV(@RequestBody CV cv) {
        Optional<CV> cvOptional = stageService.saveCV(cv);
        if (cvOptional.isPresent()) {
            emailService.sendGestionnaireEmailCVAjouter();
            return ResponseEntity.ok(cvOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/stage/cv/etudiant/{id}")
    public ResponseEntity<List<CV>> getAllCVbyEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(stageService.getAllCV(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/stage/cv/delete/{id}")
    public void deleteCV(@PathVariable int id) {
        stageService.deleteCV(id);
    }

    @GetMapping(path = "/stage/cv/pdf/{id}")
    public void generatePDF(@PathVariable("id") int id, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            Optional<CV> cv = cvService.getCVById(id);
            InputStream inputStream = new ByteArrayInputStream(
                    stageService.generateCVPDF(cv.get().getData(), cv.get().getNom()));
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/stage/cv/accept")
    public ResponseEntity<CV> acceptCV(@RequestBody CV cv) {
        Optional<CV> cvOptional = stageService.acceptCV(cv);
        if (cvOptional.isPresent()) {
            emailService.sendEtudiantEmailCVAccepted(cv);
            return ResponseEntity.ok(cvOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/stage/cv/reject")
    public ResponseEntity<CV> rejectCV(@RequestBody CV cv) {
        Optional<CV> cvOptional = stageService.rejectCV(cv);
        if (cvOptional.isPresent()) {
            emailService.sendEtudiantEmailCVRejected(cv);
            return ResponseEntity.ok(cvOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/stage/cv")
    public ResponseEntity<List<CV>> getAllCVs() {
        return new ResponseEntity<List<CV>>(stageService.getAllCVs(), HttpStatus.OK);
    }

    @GetMapping("/stage/cv/{id}")
    public ResponseEntity<CV> getCV(@PathVariable int id) {
        return stageService.getCV(id)
                .map(cv -> ResponseEntity.status(HttpStatus.OK).body(cv))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
