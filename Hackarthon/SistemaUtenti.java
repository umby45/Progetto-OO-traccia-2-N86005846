import java.util.*;

public class SistemaUtenti {
    private List<Utente> utentiRegistrati = new ArrayList<>();

    public SistemaUtenti() {
        utentiRegistrati.add(new Utente("admin", "adminpass", "amministratore"));
        for (int i = 1; i <= 10; i++) {
            utentiRegistrati.add(new Utente("utente" + i, "pass" + i, "utente"));
        }
    }

    public void registra(String nome, String password) {
        utentiRegistrati.add(new Utente(nome, password, "utente"));
    }

    public Utente login(String nome, String password) {
        for (Utente u : utentiRegistrati) {
            if (u.getNome().equals(nome) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public List<Utente> getUtentiRegistrati() {
        return utentiRegistrati;
    }
}
