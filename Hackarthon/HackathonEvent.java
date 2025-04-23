import java.time.LocalDate;

public class HackathonEvent {
    private String traccia;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public HackathonEvent(String traccia, LocalDate dataInizio, LocalDate dataFine) {
        this.traccia = traccia;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }


    public void mostraInfoEvento() {
        System.out.println("=== HACKATHON EVENT ===");
        System.out.println("Traccia: " + traccia);
        System.out.println("Data Inizio: " + dataInizio);
        System.out.println("Data Fine: " + dataFine);
        System.out.println();
    }
}
