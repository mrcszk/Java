package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
    String[] current;
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
    }

    public CSVReader(Reader reader, String delimiter) throws IOException {
        this(reader, delimiter, true);
    }

    public CSVReader(Reader reader) throws IOException {
        this(reader, ",", true);
    }
    void parseHeader() throws IOException {

        String line = reader.readLine();
        if (line == null) {
            return;
        }

        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        current = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        return true;
    }

    String get (int columnIndex, String defaultValue) {
        if (isMissing(columnIndex))
            return defaultValue;
        return current[columnIndex];
    }

    String get(String columnLabel, String defaultValue) {
        return get(columnLabelsToInt.get(columnLabel), defaultValue);
    }

    String get(String columnLabel) {
        return get(columnLabel, "");
    }

    String get(int columnIndex) {
        return get(columnIndex, "");
    }

    int getInt(int columnIndex, int defaultValue) {
        if (isMissing(columnIndex))
            return defaultValue;
        String elem = current[columnIndex];
        return Integer.parseInt(elem);
    }

    int getInt(String columnLabel, int defaultValue) {
        return getInt(columnLabelsToInt.get(columnLabel), defaultValue);
    }

    int getInt(String columnLabel) {
        return getInt(columnLabel, 0);
    }

    int getInt(int columnIndex) {
        return getInt(columnIndex, 0);
    }
    double getDouble(int columnIndex, double defaultValue) {
        if (isMissing(columnIndex))
            return defaultValue;
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel, double defaultValue) {
        return getDouble(columnLabelsToInt.get(columnLabel), defaultValue);
    }

    double getDouble(int columnIndex) {
        return getDouble(columnIndex, 0.0);
    }

    double getDouble(String columnLabel) {
        return getDouble(columnLabel, 0.0);
    }

    long getLong(int columnIndex, long defaultValue) {
        if (isMissing(columnIndex))
            return defaultValue;
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel, long defaultValue) {
        return getLong(columnLabelsToInt.get(columnLabel), defaultValue);
    }

    long getLong(int columnIndex) {
        return getLong(columnIndex, 0);
    }

    long getLong(String columnLabel) {
        return getLong(columnLabel, 0);
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        if (columnIndex >= getRecordLength())
            return true;
        return current[columnIndex].isEmpty();
    }

    boolean isMissing(String columnLabel) {
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if (columnIndex >= getRecordLength())
            return true;
        return current[columnIndex].isEmpty();
    }

    LocalTime getTime(int columnIndex, String format) {
        if (isMissing(columnIndex))
            return LocalTime.MIN;
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
    }

    LocalTime getTime(int columnIndex) {
        if (isMissing(columnIndex))
            return LocalTime.MIN;
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
    }

    LocalTime getTime(String name, String format) {
        return getTime(columnLabelsToInt.get(name), format);
    }

    LocalTime getTime(String name){
        return getTime(columnLabelsToInt.get(name));
    }

    LocalDate getDate(int columnIndex, String format) {
        if (isMissing(columnIndex))
            return LocalDate.MIN;
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
    }

    LocalDate getDate(int columnIndex) {
        if (isMissing(columnIndex))
            return LocalDate.MIN;
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    LocalDate getDate(String name, String format) {
        return getDate(columnLabelsToInt.get(name), format);
    }

    LocalDate getDate(String name) {
        return getDate(columnLabelsToInt.get(name));
    }

}