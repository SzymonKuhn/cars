import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    public static void main(String[] args) throws SQLException {
        scanner = new Scanner(System.in);

        AutoDAO autoDAO;
        try {
            autoDAO = new AutoDAO();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return;
        }

        String input;
        do {
            System.out.println("wpisz komendę:");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("wypisz")) {
                System.out.println("wszystkie auta:");
                List<Auto> auta = autoDAO.selectAll();
                System.out.println(auta);
            } else if (input.equalsIgnoreCase("dodaj")) {
                Auto auto = createAuto();
                boolean added = autoDAO.insertCar(auto);
                System.out.println("dodano auto: " + added);
            } else if (input.equalsIgnoreCase("usunId")) {
                System.out.println("Podaj id");
                int id = Integer.parseInt(scanner.nextLine());
                boolean isDeleted = autoDAO.deleteAutoById(id);
                System.out.println("usunięto? " + isDeleted);
            } else if (input.equalsIgnoreCase("usunRej")) {
                System.out.println("wpisz nr rejestracyjny");
                String nrRej = scanner.nextLine();
                boolean isDeleted = autoDAO.deleteAutoByNrRej(nrRej);
                System.out.println("usunięto? " + isDeleted);
            } else if (input.equalsIgnoreCase("wypiszrej")) {
                System.out.println("Podaj nr rejestracyjny");
                String nrRej = scanner.nextLine();
                List<Auto> auta = autoDAO.listAutosByRej(nrRej);
                System.out.println(auta);
            } else if (input.equalsIgnoreCase("wypisznazwisko")) {
                System.out.println("Podaj nazwisko wlasciciela");
                String name = scanner.nextLine();
                List<Auto> auta = autoDAO.listAutosByName(name);
                System.out.println(auta);
            }
        } while (!input.equalsIgnoreCase("koniec"));


    }//main

    private static Auto createAuto () {
        System.out.println("Wpisz nr rejestracyjny");
        String nrRejestracyjny = scanner.nextLine();
        System.out.println("Wpisz przebieg");
        int przebieg = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz markę i model");
        String marka_model = scanner.nextLine();
        System.out.println("Wpisz rok produkcji");
        int rocznik = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz typ auta: KOMBI, SEDAN, HATCHBACK, CABRIO, SUV");
        String typ = scanner.nextLine();
        System.out.println("Wpisz nazwisko właściciela");
        String nazwisko = scanner.nextLine();
        Auto auto = new Auto (nrRejestracyjny, przebieg, marka_model, rocznik, TypAuta.valueOf(typ), nazwisko);
        return auto;
    }

}//class
