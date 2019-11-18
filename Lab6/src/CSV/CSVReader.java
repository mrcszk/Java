package CSV;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    String[] currentLine;


    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename,String delimiter) throws IOException {
        this(new FileReader(filename), delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(new FileReader(filename), ";", true);
    }


    void parseHeader() throws IOException {
        String line = reader.readLine();
        if(line == null) return;

        String[] header = line.split(delimiter);

        int counter = 0;
        for(String columnLabel : header){
            columnLabels.add(columnLabel);
            columnLabelsToInt.put(columnLabel, counter);
            counter ++;
        }
    }

    boolean nextLine() throws IOException {
        String line  = null;

        line = reader.readLine();
        if(line == null) return false;

        this.currentLine = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        return true;
    }

    int getInt(String textId){
        String value = currentLine[this.columnLabelsToInt.get(textId)];
        return Integer.parseInt(value);
    }

    double getDouble(String textId){
        String value = currentLine[this.columnLabelsToInt.get(textId)];
        return Double.parseDouble(value);
    }

    String get(String textId){
        if(!isMissing(textId))
            return currentLine[this.columnLabelsToInt.get(textId)];
        else
            return "";
    }

    int getInt(int id){
        String value = currentLine[id];
        return Integer.parseInt(value);
    }

    double getDouble(int id){
        String value = currentLine[id];
        return Double.parseDouble(value);
    }

    String get(int id){
        if(!isMissing(id))
            return currentLine[id];
        else
            return "";
    }

    List<String> getColumnLabels(){
        List<String> returnableList = new ArrayList<>();
        for(String label : this.columnLabels){
            returnableList.add(label);
        }

        return returnableList;
    }

    int getRecordLength(){ return currentLine.length; }

    boolean isMissing(int columdId){
        return currentLine[columdId] == null;
    }

    boolean isMissing(String columnLabel){
        return currentLine[this.columnLabelsToInt.get(columnLabel)] == null;
    }

    LocalTime getTime(String colname) {
        return getTime(columnLabelsToInt.get(colname));
    }

    LocalTime getTime(int columnIndex) {
        return LocalTime.parse(currentLine[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
    }

    LocalDate getDate(String colname) {
        return getDate(columnLabelsToInt.get(colname));
    }

    LocalDate getDate(int columnIndex) {
        return LocalDate.parse(currentLine[columnIndex], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}