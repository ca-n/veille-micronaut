package com.group1.stagesWs.wrapper;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import lombok.Data;

import java.util.Set;

@Data
public class OffreWhitelistWrapper {
    private Offre offre;
    private Set<Etudiant> whitelist;

    public OffreWhitelistWrapper(Offre offre, Set<Etudiant> whitelist) {
        this.offre = offre;
        this.whitelist = whitelist;
    }
}
