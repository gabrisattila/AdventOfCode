import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.reverse;
import static java.util.Collections.sort;

public class FifthPuzzle {

    Map<Integer, ArrayList<Character>> crateStacks;

    ArrayList<Character> lastElements;

    public FifthPuzzle() throws IOException {
        crateStacks = new HashMap<>();
        fillMap(new File("2022/src/Fifth"));
        part(true, new File("2022/src/Fifth"));

    }

    void part(boolean isFirst, File file) throws IOException {
        if (isFirst){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                move(line);
            }
            for (int i = 0; i < crateStacks.size(); i++) {
                reverse(crateStacks.get(i + 1));
                System.out.print(crateStacks.get(i + 1).get(crateStacks.get(i + 1).size() - 1));
            }
        }
    }

    private void fillMap(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int i = 0;
        int length = 0;
        while((line = reader.readLine()) != null){
            if (i == 0){
                length = (line.length() - 3) / 4 + 1;
                for (int j = 0; j < length; j++) {
                    crateStacks.put(j + 1, new ArrayList<>());
                }
            }
            int z = 1;
            for (int j = 0; j < line.length(); j += 4) {
                if (line.charAt(j) == '['){
                    crateStacks.get(z).add(line.charAt(j + 1));
                }
                z++;
            }
            i++;
        }
    }

    private void move(String moveLine){
        if (!moveLine.isEmpty() && moveLine.charAt(0) == 'm'){
            String[] parts = moveLine.split(" ");
            int times = Integer.parseInt(parts[1]), from = Integer.parseInt(parts[3]), to = Integer.parseInt(parts[5]);
            for (int i = 0; i < times; i++) {
                crateStacks.get(to).add(crateStacks.get(from).get(crateStacks.get(from).size() - 1));
                crateStacks.get(from).remove(crateStacks.get(from).size() - 1);
            }
        }
    }







}
