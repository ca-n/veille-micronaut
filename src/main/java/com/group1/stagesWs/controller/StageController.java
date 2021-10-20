package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.repositories.CVRepository;
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
    private StageService service;

    @Autowired
    private CVRepository cvRepository;

    @GetMapping(path = "/stage/offres")
    public ResponseEntity<List<Offre>> getAllOffres() {
        return new ResponseEntity<>(service.getAllOffres(), HttpStatus.OK);
    }

    @PostMapping(path = "/stage/offres/etudiant")
    public ResponseEntity<List<Offre>> getEtudiantOffres(@RequestBody Etudiant etudiant) {
        return new ResponseEntity<>(service.getEtudiantOffres(etudiant), HttpStatus.OK);
    }

    @PostMapping(path = "/stage/offre")
    public ResponseEntity<Offre> saveOffre(@RequestBody Offre offre) {
        return service.saveOffre(offre).map(offre1 -> ResponseEntity.status(HttpStatus.OK).body(offre1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping(path = "/stage/whitelist")
    public ResponseEntity<Whitelist> saveWhitelist(@RequestBody Whitelist whitelist) {
        return service.saveWhitelist(whitelist).map(whitelist1 -> ResponseEntity.status(HttpStatus.OK).body(whitelist1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping(path = "/stage/cv")
    public ResponseEntity<CV> saveCV(@RequestBody CV cv) {
        return service.saveCV(cv).map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/stage/cv/etudiant/{id}")
    public ResponseEntity<List<CV>> getAllCVbyEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.getAllCV(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/stage/cv/delete/{id}")
    public void deleteCV(@PathVariable int id) {
        service.deleteCV(id);
    }

    @GetMapping(path = "/stage/cv/pdf/{id}")
    public void generatePDF(@PathVariable("id") int id, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            Optional<CV> cv = cvRepository.findById(id);
            InputStream inputStream = new ByteArrayInputStream(
                    service.generateCVPDF(cv.get().getData(), cv.get().getNom()));
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/stage/cv/accept")
    public ResponseEntity<CV> acceptCV(@RequestBody CV cv) {
        return service.acceptCV(cv).map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stage/cv/reject")
    public ResponseEntity<CV> rejectCV(@RequestBody CV cv) {
        return service.rejectCV(cv).map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/stage/cv/pending")
    public ResponseEntity<List<CV>> getPendingCVs() {
        return new ResponseEntity<>(service.getPendingCVs(), HttpStatus.OK);
    }
}
