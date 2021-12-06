package xyz.carn.model;

import lombok.Data;
import xyz.carn.SessionManager;
import xyz.carn.model.enums.NotifStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private NotifStatus
            status; // A CHANGER -Veux rendre le type un enum contenant tous les types de categories de
    // notif.
    private boolean isChecked;

    private String session;

    public Notification() {
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
        this.isChecked = false;
    }

    public Notification(String contenu, NotifStatus status) {
        this.content = contenu;
        this.status = status;
        this.isChecked = false;
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }
}
