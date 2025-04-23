public class Utente {
    private String nome;
    private String password;
    private String ruolo; // "utente", "giudice", "amministratore"

    public Utente(String nome, String password, String ruolo) {
        this.nome = nome;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
