package com;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {

    public static void main(String[] args) throws IOException {
        ArrayList<File> files = getFilesFromFolder();
        ArrayList<String> names = compareFiles(files);
        createReport(names);
    }

    private static String getCurrentPath() {
        return new File(".").getAbsolutePath();
    }

    private static ArrayList<File> getFilesFromFolder() {
        File path = new File(getCurrentPath());
        File[] files = path.listFiles();
        if (files != null) {
            return new ArrayList<File>(Arrays.asList(files));
        } else {
            return null;
        }
    }

    private static void createReport(ArrayList<String> fileNames) throws IOException {
        if (fileNames != null) {
            FileWriter writer = new FileWriter(getCurrentPath() + "/report.txt");
            for (String fileName : fileNames) {
                writer.append(fileName).append("\n");
            }
            writer.close();
        }
    }

    private static ArrayList<String> compareFiles(ArrayList<File> files) throws IOException {
        ArrayList<String> names = new ArrayList<String>();
        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                File currentFile = files.get(i);
                if (currentFile.isDirectory()) continue;
                for (int j = i + 1; j < files.size(); j++) {
                    File nextFile = files.get(j);
                    if (nextFile.isDirectory()) continue;
                    if (FileUtils.contentEquals(currentFile, nextFile)) {
                        if (!names.contains(currentFile.getName())) names.add(currentFile.getName());
                        if (!names.contains(nextFile.getName())) names.add(nextFile.getName());
                        files.remove(j);
                        j--;
                    }
                }
                if (names.contains(currentFile.getName())) names.add("===========================");
            }
            return names;
        } else {
            throw new AssertionError("There is no files in current folder");
        }
    }
}


