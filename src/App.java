import java.util.Scanner;
import com.config.MyConfig;

public class App {
    public static void main(String[] args) {
        boolean running = true;
        Scanner inputan = new Scanner(System.in);
        int chose = 0;

        while (running) {
            System.out.println("=================================================");
            System.out.println("WELCOME TO OTAKU SIDE");
            System.out.println("=================================================");
            System.out.println("1.] Read Data\n2.] Insert Data\n3.] Edit Data\n4.] Delete Data");
            System.out.println("=================================================");
            System.out.print("Pilih: ");

            if (inputan.hasNextInt()) {
                chose = inputan.nextInt();
                inputan.nextLine(); // Membaca karakter newline setelah angka

                switch (chose) {
                    case 1:
                        System.out.println("=================================================");
                        MyConfig.readDatabase();
                        break;
                    case 2:
                        System.out.println("=================================================");
                        MyConfig.createDatabase(inputan);
                        break;
                    case 3:
                        System.out.println("=================================================");
                        MyConfig.updateDatabase(inputan);
                        break;
                    case 4:
                        System.out.println("=================================================");
                        MyConfig.deleteDatabase(inputan);
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia");
                }
            } else {
                System.out.println("Inputan tidak Valid");
                inputan.nextLine(); // Membaca karakter newline untuk membersihkan buffer
            }
        }
        inputan.close();
    }
}
