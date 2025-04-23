import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HackathonEvent evento = new HackathonEvent(
                "Sviluppare soluzioni sostenibili per le città smart",
                LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 5, 17)
        );

        evento.mostraInfoEvento();

        SistemaUtenti sistemaUtenti = new SistemaUtenti();

        SistemaTeam sistemaTeam = new SistemaTeam(sistemaUtenti);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Registrazione");
            System.out.println("3. Esci");
            String scelta = scanner.nextLine();

            if (scelta.equals("1")) {
                System.out.print("Nome utente: ");
                String nome = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                Utente utente = sistemaUtenti.login(nome, password);
                if (utente == null) {
                    System.out.println("Login fallito.");
                    continue;
                }

                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("\nBenvenuto, " + utente.getNome() + " (" + utente.getRuolo() + ")");

                    if (utente.getRuolo().equals("utente")) {
                        System.out.println("1. Crea team");
                        System.out.println("2. Unisciti a team esistente");
                        System.out.println("3. Modifica progetto");
                        System.out.println("4. Visualizza team");
                        System.out.println("5. Logout");
                        String sceltaUtente = scanner.nextLine();

                        switch (sceltaUtente) {
                            case "1":
                                System.out.print("Nome del team: ");
                                String nomeTeam = scanner.nextLine();
                                sistemaTeam.creaTeam(nomeTeam, utente);
                                break;
                            case "2":
                                System.out.print("Inserisci nome team a cui unirti: ");
                                String teamJoin = scanner.nextLine();
                                sistemaTeam.aggiungiUtenteATeam(teamJoin, utente);
                                break;
                            case "3":
                                System.out.print("Nuova descrizione del progetto: ");
                                String descrizione = scanner.nextLine();
                                sistemaTeam.modificaProgetto(utente, descrizione);
                                break;
                            case "4":
                                sistemaTeam.mostraTuttiITeam();
                                break;
                            case "5":
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Scelta non valida.");
                        }
                    } else if (utente.getRuolo().equals("giudice")) {
                        System.out.println("1. Visualizza team");
                        System.out.println("2. Valuta progetto");
                        System.out.println("3. Logout");
                        String sceltaGiudice = scanner.nextLine();

                        switch (sceltaGiudice) {
                            case "1":
                                sistemaTeam.mostraTuttiITeam();
                                break;
                            case "2":
                                System.out.print("Nome del team da valutare: ");
                                String teamValutato = scanner.nextLine();
                                for (Team team : sistemaTeam.getTeams()) {
                                    if (team.getNome().equalsIgnoreCase(teamValutato)) {
                                        System.out.print("Punteggio (0-100): ");
                                        int punteggio = Integer.parseInt(scanner.nextLine());
                                        sistemaTeam.valutaProgetto(utente, team, punteggio);
                                        break;
                                    }
                                }
                                break;
                            case "3":
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Scelta non valida.");
                        }
                    } else if (utente.getRuolo().equals("amministratore")) {
                        System.out.println("=== Lista Utenti Registrati ===");
                        for (Utente u : sistemaUtenti.getUtentiRegistrati()) {
                            System.out.println("- " + u.getNome() + " (" + u.getRuolo() + ")");
                        }

                        System.out.println("\nVuoi promuovere utenti a giudice? (s/n)");
                        if (scanner.nextLine().equalsIgnoreCase("s")) {
                            int selezionati = 0;
                            for (Utente u : sistemaUtenti.getUtentiRegistrati()) {
                                if (u.getRuolo().equals("utente")) {
                                    boolean inTeam = false;
                                    for (Team t : sistemaTeam.getTeams()) {
                                        if (t.contieneUtente(u)) {
                                            inTeam = true;
                                            break;
                                        }
                                    }
                                    if (!inTeam) {
                                        u.setRuolo("giudice");
                                        System.out.println("Promosso a giudice: " + u.getNome());
                                        selezionati++;
                                    }
                                }
                                if (selezionati >= 2) break;
                            }
                        }
                        loggedIn = false;
                    }
                }
            } else if (scelta.equals("2")) {
                System.out.print("Nuovo nome utente: ");
                String nuovoNome = scanner.nextLine();
                System.out.print("Nuova password: ");
                String nuovaPass = scanner.nextLine();
                sistemaUtenti.registra(nuovoNome, nuovaPass);
                System.out.println("Registrazione completata.");
            } else if (scelta.equals("3")) {
                System.out.println("Uscita dal programma.");
                break;
            } else {
                System.out.println("Scelta non valida.");
            }
        }
    }
}
