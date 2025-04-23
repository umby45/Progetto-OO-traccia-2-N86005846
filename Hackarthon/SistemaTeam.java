import java.util.*;

public class SistemaTeam {
    public boolean aggiungiUtenteATeam(String nomeTeam, Utente utente) {
        if (utente.getRuolo().equals("giudice")) {
            System.out.println("❌ Un giudice non può far parte di un team.");
            return false;
        }
        for (Team team : teams) {
            if (team.getNome().equalsIgnoreCase(nomeTeam)) {
                if (!team.contieneUtente(utente)) {
                    team.aggiungiMembro(utente);
                    System.out.println("✅ " + utente.getNome() + " è stato aggiunto al team " + nomeTeam);
                    return true;
                } else {
                    System.out.println("⚠️ L'utente è già in questo team.");
                    return false;
                }
            }
        }
        System.out.println("❌ Team non trovato.");
        return false;
    }
    public SistemaTeam(SistemaUtenti sistemaUtenti) {
        // Precarica un team chiamato "TeamStart" con utente1 e utente2
        Optional<Utente> utente1 = sistemaUtenti.getUtentiRegistrati().stream().filter(u -> u.getNome().equals("utente1")).findFirst();
        Optional<Utente> utente2 = sistemaUtenti.getUtentiRegistrati().stream().filter(u -> u.getNome().equals("utente2")).findFirst();
        if (utente1.isPresent()) {
            creaTeam("TeamStart", utente1.get());
            if (utente2.isPresent()) {
                aggiungiUtenteATeam("TeamStart", utente2.get());
            }
        }
    }
    private List<Team> teams = new ArrayList<>();

    public void creaTeam(String nome, Utente creatore) {
        teams.add(new Team(nome, creatore));
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void mostraTuttiITeam() {
        for (Team team : teams) {
            team.mostraInfoTeam();
        }
    }

    public void modificaProgetto(Utente utente, String nuovaDescrizione) {
        for (Team team : teams) {
            if (team.utentePuoModificareProgetto(utente)) {
                team.setProgetto(nuovaDescrizione);
                System.out.println("✅ Progetto aggiornato con successo nel team " + team.getNome());
                return;
            }
        }
        System.out.println("❌ Non sei in nessun team o non hai i permessi per modificare il progetto.");
    }

    public void valutaProgetto(Utente giudice, Team team, int punteggio) {
        if (!giudice.getRuolo().equals("giudice")) {
            System.out.println("Solo i giudici possono valutare i progetti.");
            return;
        }
        team.setPunteggio(punteggio);
        System.out.println("Valutazione assegnata con successo.");
    }
}
