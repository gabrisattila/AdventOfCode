import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SixthPuzzle {

    public SixthPuzzle() throws IOException {
        part(true);
        part(false);
    }

    private void part(boolean isFirst) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2022/src/Sixth"));
        if (isFirst){
            System.out.println(marker1(reader.readLine()));
        }else {
            System.out.println(marker2(reader.readLine()));
        }
    }

    private int marker1(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (isStringContainsOnlyUniqueChars(line.substring(i, i + 4))){
                return i + 4;
            }
        }
        return 0;
    }

    private int marker2(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (isStringContainsOnlyUniqueChars(line.substring(i, i + 14))){
                return i + 14;
            }
        }
        return 0;
    }

    private boolean isStringContainsOnlyUniqueChars(String str){
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
