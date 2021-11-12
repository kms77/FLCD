import java.io.IOException;

/*public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "C:/Users/komsa/FLCD/Laboratory3-final/Laboratory3-final/src/Files/p1err.txt";
        var lexicalScanner = new LexicalScanner(filename);
        lexicalScanner.parseFile();
    }
}
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Choose a value: ");
        System.out.println("1. DFA menu");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch(option){
            case 1 -> MenuDFA();
        }
    }

    private static void printMenu(){
        System.out.println("1. Print states");
        System.out.println("2. Print alphabet");
        System.out.println("3. Print final states");
        System.out.println("4. Print transitions");
        System.out.println("5. Check if DFA");
        System.out.println("6. Check if sequence is accepted by DFA");
        System.out.println("0. Exit");
    }

    private static void MenuDFA() {
        FA fa = new FA("C:/Users/komsa/OneDrive/Documents/FLCD/Lab4/Lab4/Laboratory4/src/Files/FA.txt");
        System.out.println("FA.txt read from file.");
        printMenu();
        System.out.println("Your option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while(option != 0){
            switch(option){
                case 1 -> System.out.println(fa.writeStates());
                case 2 -> System.out.println(fa.writeAlphabet());
                case 3 -> System.out.println(fa.writeFinalStates());
                case 4 -> System.out.println(fa.writeTransitions());
                case 5 -> System.out.println(fa.checkIfDFA());
                case 6 -> {
                    if(fa.checkIfDFA()){
                        System.out.println("Write your sequence: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String sequence = scanner2.nextLine();
                        System.out.println(sequence.length());

                        if (fa.checkSequence(sequence))
                            System.out.println("Valid sequence!");
                        else
                            System.out.println("Invalid sequence!");
                    }
                    else{
                        System.out.println("FA.txt is not deterministic");
                    }
                }
            }
            System.out.println();
            printMenu();
            System.out.println("Choose your option: ");
            option = scanner.nextInt();
        }
    }



}
