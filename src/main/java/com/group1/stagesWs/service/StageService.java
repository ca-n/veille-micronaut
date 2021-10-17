package com.group1.stagesWs.service;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.repositories.CVRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import com.group1.stagesWs.repositories.WhitelistRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.crypto.spec.PSource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StageService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Autowired
    private CVRepository cvRepository;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(Etudiant etudiant) {
        List<Whitelist> whitelists = whitelistRepository.findAllByWhitelistedEtudiant(etudiant);
        return offreRepository.findAllByVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(whitelists);
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }

    public Optional<Whitelist> saveWhitelist(Whitelist whitelist) {
        return Optional.of(whitelistRepository.save(whitelist));
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public List<CV> getAllCV(int id) {
        return cvRepository.findALlByEtudiantId(id);
    }

    public void deleteCV(int id) {
        cvRepository.deleteById(id);
    }

    public void generateCVPDF(byte[] bArray,String filePath,String fileName) {
        try {
            OutputStream out = new FileOutputStream(filePath + "\\" + fileName);
            out.write(bArray);
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


}
