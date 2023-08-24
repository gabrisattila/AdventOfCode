import java.io.*;
import java.util.ArrayList;

public class FourthPuzzle {

    public FourthPuzzle() throws IOException {
        part(true);
        part(false);
    }

    private void part(boolean b) throws IOException {
        System.out.println(sumOfFullyContains(new File("2022/src/Fourth.txt"), b));
    }

    private int sumOfFullyContains(File file, boolean b) throws IOException {
        String line;
        String[] parts;
        int sum = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int i = 0;
        while ((line = reader.readLine()) != null){
            parts = line.split(",");
            sum += b ? (containsFullyTheOther(separateStringByHyphen(parts[0]), separateStringByHyphen(parts[1])) ?
                    1 : 0) : (containsOverlap(separateStringByHyphen(parts[0]), separateStringByHyphen(parts[1])) ?
                    1 : 0);
        }
        return sum;
    }


    private boolean containsFullyTheOther(Integer[] i1, Integer[] i2){
        return (i1[0] <= i2[0] && i1[1] >= i2[1]) || (i2[0] <= i1[0] && i2[1] >= i1[1]);
    }

    private boolean containsOverlap(Integer[] i1, Integer[] i2) {
        ArrayList<Integer> first = new ArrayList<>(), second = new ArrayList<>();
        for (int i = i1[0]; i < i1[1] + 1; i++) {
            first.add(i);
        }
        for (int i = i2[0]; i < i2[1] + 1; i++) {
            second.add(i);
        }
        first.retainAll(second);
        return !first.isEmpty();
    }
    private Integer[] separateStringByHyphen(String string){
        String[] s = string.split("-");
        return new Integer[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};
    }

}
