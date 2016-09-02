import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static User user = new User();


    public static void main(String[] args) throws Exception {

//        user.generateUser();

        boolean b = true;

        while (b) {
//            boolean a = true;
            user.chooseUser();

            boolean d = true;
            while (d) {
                d = user.chooseAction();
            }
        }

    }
}
