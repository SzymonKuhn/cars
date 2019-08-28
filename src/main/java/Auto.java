import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Auto {
    private int id;
    private String nr_rejestracyjny;
    private int przebieg;
    private String marka_model;
    private int rocznik;
    private TypAuta typAuta;
    private String nazwisko_wlasciciela;

    public Auto(String nr_rejestracyjny, int przebieg, String marka_model, int rocznik, TypAuta typAuta, String nazwisko_wlasciciela) {
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.przebieg = przebieg;
        this.marka_model = marka_model;
        this.rocznik = rocznik;
        this.typAuta = typAuta;
        this.nazwisko_wlasciciela = nazwisko_wlasciciela;
    }
}
