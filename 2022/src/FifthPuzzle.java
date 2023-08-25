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
//        part(true, new File("2022/src/Fifth"));
        part(false, new File("2022/src/Fifth"));

    }

    void part(boolean isFirst, File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        if (isFirst){
            while((line = reader.readLine()) != null){
                move9000(line);
            }
            for (int i = 0; i < crateStacks.size(); i++) {
                System.out.print(crateStacks.get(i + 1).get(crateStacks.get(i + 1).size() - 1));
            }
        }else {
            while((line = reader.readLine()) != null){
                move9001(line);
            }
            for (int i = 0; i < crateStacks.size(); i++) {
                System.out.print(crateStacks.get(i + 1).get(crateStacks.get(i + 1).size() - 1));
            }
        }
    }

    private void fillMap(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int i = 0;
        int length;
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
        for (int j = 1; j < crateStacks.size() + 1; j++) {
            reverse(crateStacks.get(j));
        }
        for (int j = 1; j < crateStacks.size() + 1; j++) {
            System.out.println(crateStacks.get(j));
        }
    }

    private void move9000(String moveLine){
        if (!moveLine.isEmpty() && moveLine.charAt(0) == 'm'){
            String[] parts = moveLine.split(" ");
            int times = Integer.parseInt(parts[1]), from = Integer.parseInt(parts[3]), to = Integer.parseInt(parts[5]);
            for (int i = 0; i < times; i++) {
                crateStacks.get(to).add(crateStacks.get(from).get(crateStacks.get(from).size() - 1));
                crateStacks.get(from).remove(crateStacks.get(from).size() - 1);
            }
        }
    }

    private void move9001(String moveLine) {
        if (!moveLine.isEmpty() && moveLine.charAt(0) == 'm'){
            String[] parts = moveLine.split(" ");
            int howMany = Integer.parseInt(parts[1]), from = Integer.parseInt(parts[3]), to = Integer.parseInt(parts[5]);
            for (int i = howMany; i > 0; i--) {
                crateStacks.get(to).add(crateStacks.get(from).get(crateStacks.get(from).size() - i));
                crateStacks.get(from).remove(crateStacks.get(from).size() - i);
            }
        }
    }







}
