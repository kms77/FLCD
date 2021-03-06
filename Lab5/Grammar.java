import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
//    N - nonterminals
//    E - terminals
//    P - productions
//    S - starting symbol
    private Set<String> N = new HashSet<>();
    private Set<String> E = new HashSet<>();
    private HashMap<Set<String>,Set<List<String>>> P = new HashMap<>();
    private String S = "";
    private String grammarFilename;

    public Grammar(String grammarFilename) {
        this.grammarFilename = grammarFilename;
        readGrammarFromFile(grammarFilename);
    }

    public void setGrammarFilename(String grammarFilename) {
        this.grammarFilename = grammarFilename;
    }

    private void readGrammarFromFile(String grammarFilename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(grammarFilename));

            String input = reader.readLine();
            String[] NlineSplit = input.split("=",input.indexOf("="));
            StringBuilder Nline = new StringBuilder();
            for(int i=1;i<NlineSplit.length;++i)
                Nline.append(NlineSplit[i]);
            StringBuilder builder = new StringBuilder(Nline.toString());
            builder.deleteCharAt(1).deleteCharAt(Nline.length()-2);
            Nline = new StringBuilder(builder.toString());
            this.N = new HashSet<>(Arrays.asList(Nline.toString().strip().split(" ")));

            input = reader.readLine();
            String[] ElineSplit = input.split("=",input.indexOf("="));
            StringBuilder Eline = new StringBuilder();
            for(int i=1;i<ElineSplit.length;++i)
                Eline.append(ElineSplit[i]);
            builder = new StringBuilder(Eline.toString());
            builder.deleteCharAt(1).deleteCharAt(Eline.length()-2);
            Eline = new StringBuilder(builder.toString());
            this.E = new HashSet<>(Arrays.asList(Eline.toString().strip().split(" ")));

            this.S = reader.readLine().split("=")[1].strip();

            reader.readLine(); // first and last lines for productions will not contain any relevant information, we only need to check starting from the second until the second-last
            String line = reader.readLine();
            while(line != null){
                if(!line.equals("}")) {
                    String[] tokens = line.split("->");
                    String[] lhsTokens = tokens[0].split(",");
                    String[] rhsTokens = tokens[1].split("\\|");

                    Set<String> lhs = new HashSet<>();
                    for(String l : lhsTokens)
                        lhs.add(l.strip());
                    if(!P.containsKey(lhs))
                        P.put(lhs,new HashSet<>());

                    for(String rhsT : rhsTokens) {
                        ArrayList<String> productionElements = new ArrayList<>();
                        String[] rhsTokenElement = rhsT.strip().split(" ");
                        for(String r : rhsTokenElement)
                            productionElements.add(r.strip());
                        P.get(lhs).add(productionElements);
                    }
                }
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String print_N(){
        StringBuilder sb = new StringBuilder("N = { ");
        for (String n: this.N) {
            sb.append(n).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public String print_E(){
        StringBuilder sb = new StringBuilder("E = { ");
        for (String e: this.E) {
            sb.append(e).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public String print_P(){
        StringBuilder sb = new StringBuilder("P = { \n");
        P.forEach((lhs, rhs) -> {
            sb.append("\t");
            int count = 0;
            for(String lh : lhs){
                sb.append(lh);
                count++;
                if(count < lhs.size())
                    sb.append(", ");
            }
            sb.append("->");
            count = 0;
            for(List<String> rh : rhs){
                for (String r : rh){
                    sb.append(r).append(" ");
                }
                count++;
                if(count < rhs.size())
                    sb.append("| ");
            }
            sb.append("\n");
        });
        sb.append("}");
        return sb.toString();
    }

    public String print_P_forNonTerminal(String nonTerminal){
        StringBuilder sb = new StringBuilder();
        for (Set<String> lhs : P.keySet()){
            if(lhs.contains(nonTerminal)){
                sb.append(nonTerminal).append(" -> ");
                Set<List<String>> rhs = P.get(lhs);
                int count = 0;
                for(List<String> rh : rhs){
                    for(String r : rh){
                        sb.append(r).append(" ");
                    }
                    count++;
                    if(count < rhs.size())
                        sb.append("| ");
                }
            }
        }
        return sb.toString();
    }

    public boolean isCFG(){
        var checkStartingSymbol = false;
        for(Set<String> lhs : P.keySet())
            if (lhs.contains(S)) {
                checkStartingSymbol = true;
                break;
            }
        if(!checkStartingSymbol)
            return false;

        for(Set<String> lhs : P.keySet()){
            if(lhs.size()>1)
                return false;
            else if(!N.contains(lhs.iterator().next()))
                return false;


        }
        return true;
    }



}
