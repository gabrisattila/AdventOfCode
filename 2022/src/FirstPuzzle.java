import lombok.*;

import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
public class FirstPuzzle {

    private ArrayList<Integer> calories;

    public FirstPuzzle() throws IOException {
        calories = readInput(new File("2022/src/Firs.txt"));

        //First part
        System.out.println(calories.stream().max(Integer::compare).get());

        //Second part
        System.out.println(firstThreeTotal(calories));

    }

    public ArrayList<Integer> readInput(File input) throws IOException {
        ArrayList<Integer> ret = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(input));

        String line;
        int num = 0;
        while ((line = bufferedReader.readLine()) != null){

            if (!line.isEmpty()){
                num += Integer.parseInt(line);
            }else {
                ret.add(num);
                num = 0;
            }
        }

        return ret;
    }


    //Second part
    public int firstThreeTotal(ArrayList<Integer> nums){

        int num = 0;

        for (int i = 0; i < 3; i++) {
            num += nums.stream().max(Integer::compare).get();
            nums.remove(nums.stream().max(Integer::compare).get());
        }

        return num;
    }
}
