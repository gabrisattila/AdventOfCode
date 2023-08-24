import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.sort;

public class ThirdPuzzle {

    public ThirdPuzzle() throws IOException {
        part(true);
        part(false);
    }

    private void part(boolean isFirst) throws IOException {
        System.out.println(sumOfCommonsInStrings(new File("2022/src/Third.txt"), isFirst));
    }

    private int charToInt(char ch){
        if (Character.isLowerCase(ch))
            return  ch - 'a' + 1;
        else
            return (int)ch - (65 - 27);
    }

    //First

    private int sumOfCommonsInStrings(File file, boolean isFirst) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> lines = new ArrayList<>();
        int i = 0;
        int sum = 0;
        while ((line = reader.readLine()) != null){

            if (isFirst)
                sum += findCommonCharInInt(line.substring(0, (line.length() / 2)), line.substring((line.length() / 2)));
            else {
                lines.add(line);
                if (lines.size() == 3){
                    sum += findCommonCharInInt(lines.get(0), lines.get(1), lines.get(2));
                    lines.clear();
                }
            }

        }
        return sum;
    }

    private int findCommonCharInInt(String s1, String s2){
        ArrayList<Integer> i1 = new ArrayList<>(), i2 = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            i1.add(charToInt(s1.charAt(i)));
            i2.add(charToInt(s2.charAt(i)));
        }
        i1.retainAll(i2);
        return i1.get(0);
    }

    private int findCommonCharInInt(String s1, String s2, String s3){
        ArrayList<Integer> i1 = new ArrayList<>(), i2 = new ArrayList<>(), i3 = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            i1.add(charToInt(s1.charAt(i)));
        }
        for (int i = 0; i < s2.length(); i++) {
            i2.add(charToInt(s2.charAt(i)));
        }
        for (int i = 0; i < s3.length(); i++) {
            i3.add(charToInt(s3.charAt(i)));
        }
        i1.retainAll(i2);
        i1.retainAll(i3);
        return i1.get(0);
    }
}
