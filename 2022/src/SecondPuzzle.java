import java.io.*;
import java.util.ArrayList;

public class SecondPuzzle {

    private ArrayList<Integer> scores;

    public SecondPuzzle(File file) throws IOException {

        part(file, true);
        part(file, false);

    }

    public void part(File file, boolean isFirst) throws IOException {
        BufferedReader reader =  new BufferedReader(new FileReader(file));
        int num = 0;

        String line;
        while ((line = reader.readLine()) != null){
            String[] fs = line.split(" ");
            num += new RPS(fs[0].charAt(0), fs[1].charAt(0)).getScore(isFirst);
        }

        System.out.println(num);
    }


    public static class RPS{

        private char f, s;

        private final ArrayList<Character> firstOptions = new ArrayList<>() {{
            add('A');
            add('B');
            add('C');
        }};

        private final ArrayList<Character> secondOptions = new ArrayList<>() {{
            add('X');
            add('Y');
            add('Z');
        }};


        public RPS(Character first, Character second){
            f = first;
            s = second;
        }

        public int getScore(boolean isFirst){

            if (!firstOptions.contains(f) || !secondOptions.contains(s)){
                throw new RuntimeException("The input is not correct");
            }

            int first, second = 0;

            if (isFirst){
                second = whoMuchItWorth(s);
                first = betIt(f, s);
            }else {
                first = whoMuchItWorth(loseDrawWin(f, s));
                second = fina(s);
            }
            return first + second;
        }

        public int fina(char c){
            switch (c){
                case 'X' -> {
                    return 0;
                }
                case 'Y' ->{
                    return 3;
                }
                default -> {
                    return 6;
                }
            }
        }

        public int whoMuchItWorth(Character c){
            if (c == 'A' || c == 'X')
                return 1;
            else if (c == 'B' || c == 'Y') {
                return 2;
            }else
                return 3;
        }

        public int betIt(char f, char s){
            int loseDrawWin = 0;
            switch (f){
                case 'A' -> {
                    if (s == 'X'){
                        loseDrawWin = 3;
                    }else if (s == 'Y'){
                        loseDrawWin = 6;
                    }
                }
                case 'B' -> {
                    if (s == 'Y'){
                        loseDrawWin = 3;
                    }else if (s == 'Z'){
                        loseDrawWin = 6;
                    }

                }
                case 'C' -> {
                    if (s == 'X'){
                        loseDrawWin = 6;
                    }else if (s == 'Z') {
                        loseDrawWin = 3;
                    }
                }
            }
            return loseDrawWin;
        }

        public Character loseDrawWin(char f, char s){
            Character c = ' ';
            if (!secondOptions.contains(s))
                throw new RuntimeException("Error");
            switch (s){
                case 'X' -> {
                    switch (f){
                        case 'A' -> {
                            c = 'Z';
                        }
                        case 'B' -> {
                            c = 'X';
                        }
                        case 'C' -> {
                            c = 'Y';
                        }
                    }
                }
                case 'Y' -> {
                    switch (f){
                        case 'A' -> {
                            c = 'X';
                        }
                        case 'B' -> {
                            c = 'Y';
                        }
                        case 'C' -> {
                            c = 'Z';
                        }
                    }
                }
                case 'Z' -> {
                    switch (f){
                        case 'A' -> {
                            c = 'Y';
                        }
                        case 'B' -> {
                            c = 'Z';
                        }
                        case 'C' -> {
                            c = 'X';
                        }
                    }
                }
            }
            return 'A';
        }
    }
}
