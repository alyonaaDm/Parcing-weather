import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Indicators {
    private String data = "";
    private double temperature = 0;
    private double wet = 0;
    private double windSpeed = 0;
    private double directionOfWind = 0;

    public Indicators(String data, double temperature, double wet, double windSpeed, double directionOfWind) {
        this.data = data;
        this.temperature = temperature;
        this.wet = wet;
        this.windSpeed = windSpeed;
        this.directionOfWind = directionOfWind;
    }

    public Indicators() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWet() {
        return wet;
    }

    public void setWet(double wet) {
        this.wet = wet;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getDirectionOfWind() {
        return directionOfWind;
    }

    public void setDirectionOfWind(double directionOfWind) {
        this.directionOfWind = directionOfWind;
    }

    public Indicators toRewrite(String line) {
        int index = 0;
        Indicators temp = new Indicators();
        String allData = toGetDataInNumbers(line, index);
        temp.setData(allData);
        index = index + allData.length() + 1;
        allData = toGetDataInNumbers(line, index);
        temp.setTemperature(Double.parseDouble(allData));
        index = index + allData.length() + 1;
        allData = toGetDataInNumbers(line, index);
        temp.setWet(Double.parseDouble(allData));
        index = index + allData.length() + 1;
        allData = toGetDataInNumbers(line, index);
        temp.setWindSpeed(Double.parseDouble(allData));
        index = index + allData.length() + 1;
        allData = toGetDataInNumbers(line, index);
        temp.setDirectionOfWind(Double.parseDouble(allData));
        return temp;
    }

    public String toGetDataInNumbers(String line, int index) {
        String answer = "";
        while (index < line.length() && line.charAt(index) != ',') {
            answer += line.charAt(index);
            index++;
        }
        return answer;
    }

    public void toGetMaxTemperature(ArrayList<Indicators> list, String outputFileName) {
        Indicators max = new Indicators();
        max.setTemperature(-100);
        double averageInd = 0;
        int k = 0;
        for (Indicators day :
                list) {
            day.setData(day.toConvertData(day.getData()));
            averageInd += day.getTemperature();
            k++;
            if (day.getTemperature() > max.getTemperature()) {
                max = day;

            }
        }
        averageInd /= k;
        String result = String.format("%.1f", max.getTemperature());
        String resultAVG = String.format("%.1f", averageInd);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write("MAXIMUM TEMPERATURE in " + max.getData() + " : " + result + " В°C" + '\n');
            writter.write("AVERAGE TEMPERATURE : " + resultAVG + " В°C" + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void toGetMinWet(ArrayList<Indicators> list, String outputFile) {
        Indicators min = new Indicators();
        min.setWet(1000);
        double averageInd = 0;
        int k = 0;
        for (Indicators day :
                list) {
            averageInd += day.getWet();
            k++;
            if (day.getWet() < min.getWet()) {
                min = day;

            }
        }
        averageInd /= k;
        String result = String.format("%.1f", min.getWet());
        String resultAVG = String.format("%.1f", averageInd);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFile, true))) {
            writter.write("MINIMUM WET in " + min.getData() + " : " + result + " %" + '\n');
            writter.write("AVERAGE WET: " + resultAVG + " %" + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toGetMaxWindSpeed(ArrayList<Indicators> arrayList, String outputFileName) {
        Indicators max = new Indicators();
        max.setWindSpeed(0);
        double averageInd = 0;
        int counter = 0;
        for (Indicators day :
                arrayList) {
            averageInd += day.getWindSpeed();
            counter++;
            if (day.getWindSpeed() > max.getWindSpeed()) {
                max = day;

            }
        }
        averageInd /= counter;
        String result = String.format("%.1f", max.getWindSpeed());
        String resultAVG = String.format("%.1f", averageInd);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writter.write("MAXIMUM WIND SPEED in " + max.getData() + " : " + result + " km/h" + '\n');
            writter.write("AVERAGE WIND SPEED: " + resultAVG + " km/h" + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toGetDirectionOfWind(ArrayList<Indicators> list, String outPutFile) {
        int west = 0;
        int east = 0;
        int north = 0;
        int south = 0;
        int popularDirection = -1;
        int max = 0;
        for (Indicators day :
                list) {
            if (day.getDirectionOfWind() > 315 && day.getDirectionOfWind() < 45) {
                north++;
                if (north > max) {
                    max = north;
                    popularDirection = 0;
                }
            }
            if (day.getDirectionOfWind() > 45 && day.getDirectionOfWind() < 135) {
                east++;
                if (east > max) {
                    max = east;
                    popularDirection = 1;
                }
            }
            if (day.getDirectionOfWind() > 135 && day.getDirectionOfWind() < 225) {
                south++;
                if (south > max) {
                    max = south;
                    popularDirection = 2;
                }
            }
            if (day.getDirectionOfWind() > 225 && day.getDirectionOfWind() < 315) {
                west++;
                if (west > max) {
                    max = west;
                    popularDirection = 3;
                }
            }
        }
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outPutFile, true))) {
            switch (popularDirection) {
                case 0 -> writter.write("MOST POPULAR WIND DIRECTION: north" + '\n');
                case 1 -> writter.write("MOST POPULAR WIND DIRECTION: east" + '\n');
                case 2 -> writter.write("MOST POPULAR WIND DIRECTION: south" + '\n');
                case 3 -> writter.write("MOST POPULAR WIND DIRECTION: west" + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toConvertData(String data) {
        String year = data.substring(0,4);
        String day = data.substring(6,8);
        String month = data.substring(4,6);
        String hours = data.substring(9,11);
        String minutes = data.substring(11,13);
        return day + '/' + month + '/' + year + " " + "at " + hours + "." + minutes;
    }
}
