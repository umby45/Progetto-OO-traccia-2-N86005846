import java.util.List;
import java.util.ArrayList;

public class Team {
    private String nome;
    private Utente creatore;
    private String progetto;
    private List<Utente> membri;
    private int punteggio;

    public Team(String nome, Utente creatore) {
        this.nome = nome;
        this.creatore = creatore;
        this.progetto = "";
        this.membri = new ArrayList<>();
        this.membri.add(creatore);
        this.punteggio = 0;
    }

    public String getNome() {
        return nome;
    }

    public Utente getCreatore() {
        return creatore;
    }

    public String getProgetto() {
        return progetto;
    }

    public void setProgetto(String progetto) {
        this.progetto = progetto;
    }

    public List<Utente> getMembri() {
        return membri;
    }

    public void aggiungiMembro(Utente utente) {
        if (!membri.contains(utente)) {
            if (membri.size() < 3) {
                membri.add(utente);
            } else {
                System.out.println("❌ Il team ha già il numero massimo di membri (3).");
            }
        }
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public void mostraInfoTeam() {
        System.out.println("Team: " + nome);
        System.out.println("Creatore: " + creatore.getNome());
        System.out.println("Progetto: " + progetto);
        System.out.println("Punteggio: " + punteggio);
        System.out.print("Membri: ");
        for (Utente membro : membri) {
            System.out.print(membro.getNome() + " ");
        }
        System.out.println();
    }

    public boolean contieneUtente(Utente utente) {
        return membri.contains(utente);
    }

    public boolean utenteAutorizzato(Utente utente) {
        return contieneUtente(utente);
    }

    public boolean utentePuoModificareProgetto(Utente utente) {
        return contieneUtente(utente);
    }
}
