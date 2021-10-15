import Domain.SymbolTable;

public class Main {

    public static void main(String[] args) {
        var symbolTable = new SymbolTable(5);
        System.out.println(symbolTable.addElement("a"));
        System.out.println(symbolTable.addElement("bb"));
        System.out.println(symbolTable.addElement("1a"));
        System.out.println(symbolTable.addElement("a"));
        System.out.println(symbolTable.addElement("2b"));
        System.out.println("Search: a");
        System.out.println("Position: " + symbolTable.searchElement("a"));
        System.out.println("Search: 2b");
        System.out.println("Position: " + symbolTable.searchElement("2b"));
        System.out.println("The SymbolTable");
        symbolTable.printTable();
    }
}