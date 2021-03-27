import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AplicationManager {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        String outPutFile = "OutPut.txt";
        String inPutFile = file.getName();
        Indicators indicators = new Indicators();
        ArrayList<Indicators> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inPutFile))){
            String line;
            while ((line = reader.readLine()) != null){
                if (line.startsWith("2021")){
                    list.add(indicators.toRewrite(line));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        indicators.toGetMaxTemperature(list,outPutFile);
        indicators.toGetMinWet(list,outPutFile);
        indicators.toGetMaxWindSpeed(list,outPutFile);
        indicators.toGetDirectionOfWind(list, outPutFile);
    }
}
