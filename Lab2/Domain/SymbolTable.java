package Domain;

public class SymbolTable {
    private final Integer tableSize;
    private final String[] hashTable;

    public SymbolTable(Integer tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new String[tableSize];
    }

     /*
        addElement:
        - input: key - type String
        - output: String message
        This function add elements to the HashTable; The element will be not added if it exists.
     */

    public String addElement(String key){
        if(searchElement(key)!= -1){
            return "Element already exists!";
        }
        int i = hashFunction(key);
        // linear probing in case of collision resolution
        while (hashTable[i] != null) {
            i++;
            if (i>= tableSize){
                i=0;
            }
        }
        hashTable[i]=key;
        return "Element added on position " + i;
    }

    /*
       searchElement:
       - input: key - type String
       - output: position i - type int, if element exists
                 -1 , if the element doesn't exist in hash table
       This function search fot an element in the HashTable.
     */

    public int searchElement(String key){
        int i = hashFunction(key);
        while (hashTable[i] != null) {
            if (hashTable[i].equals(key)) {
                return i;
            }
            if(i>= tableSize -1){
                return -1;
            }
            i++;
        }
        return -1;
    }

    /*
        hashFunction:
        - input: key - type String
        - output: the hash value -type int
        This method computes the hash value for a given key;
        The returned value is the asci code sum of every key character mod size of the hashtable;

     */
    private int hashFunction(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % tableSize;
    }

    /*
       Method to print all values with position of the hash table
     */
    public void printTable(){
        for (int i=0;i<hashTable.length;i++){
            System.out.println( "Position: " + i + " | Element: "+hashTable[i]);
        }
    }
}
