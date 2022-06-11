package org.quiz;

import com.opencsv.CSVParser;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public final class CSVFile {

    public static List<String[]> readCSVfile(String path) throws Exception{
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> records = reader.readAll();

            return records;
        }
    }

    public static void createRecord(String path,String objectToString) throws Exception {
        String[] objectProperties = getObjectProperties(objectToString);
        List<String[]> oldCsvFileRecords = readCSVfile(path);
        if (!(oldCsvFileRecords.isEmpty())){
            oldCsvFileRecords.add(objectProperties);
            writeCSVdata(oldCsvFileRecords,path);
        }
        else{
            createCSVfirstRecord(path,objectToString);
        }
    }

    /*public static List<User> ConvertCSVtoObject(String path) throws IOException {
        try {

            List<User> beans = new CsvToBeanBuilder(new FileReader(path))
                    .withType(User.class)
                    .build()
                    .parse();

            beans.forEach(System.out::println);
            return beans;

        }
        catch (IOException e){
            throw new IOException();
        }
    }*/

    private static void createCSVfirstRecord(String path, String objectToString){
        String[] objectProperties = getObjectProperties(objectToString);
        List<String[]> csvData = createCSVdata(objectProperties);
        writeCSVdata(csvData,path);
    }

    private static void writeCSVdata(List<String[]> csvData, String path){
        try(CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(csvData);
        }
        catch (IOException e){
            e.fillInStackTrace();
        }
    }
    private static String[] getObjectProperties(String objectToString){
        String[] properties = objectToString.split(" ");
        return properties;
    }
    private static List<String[]> createCSVdata(String[] objectProperties){
        System.out.println("Enter columns' count: ");
        Scanner scanner = new Scanner(System.in);
        int columnsCount = scanner.nextInt();
        String[] headers = new String[columnsCount];
        List<String[]> list = new ArrayList<>();

        for (int i =0; i<columnsCount; i++){
            Scanner headersScanner = new Scanner(System.in);
            System.out.println("Enter header number "+(i+1));
            headers[i] = headersScanner.nextLine();
        }
        list.add(headers);
        list.add(objectProperties);

        return list;
    }
}
