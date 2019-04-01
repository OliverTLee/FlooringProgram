/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

//File Dao abstract class created by Corbin March
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public abstract class FileDao<T> {

    private final int columnCount;
    private final boolean hasHeaders;

    public FileDao(int columnCount, boolean hasHeaders) {
        this.columnCount = columnCount;
        this.hasHeaders = hasHeaders;
    }

    public List<T> read(Function<String[], T> mapper, String path) throws StorageException {

        ArrayList<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            if (hasHeaders) { // throw out header...
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                if (tokens.length == columnCount) {
                    T obj = mapper.apply(tokens);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
        return result;
    }

    public void write(Collection<T> items, Function<T, String> mapper, String path) throws StorageException {
        String header = "";
        if (hasHeaders) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                header = reader.readLine();
            } catch (IOException ex) {
                throw new StorageException(ex.getMessage(), ex);
            }
        }
        try (PrintWriter writer = new PrintWriter(path)) {
            if (hasHeaders) {
                writer.println(header);
            }
            for (T obj : items) {
                if (obj != null) {
                    writer.println(mapper.apply(obj));
                }
            }
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
    }

    public void append(T item, Function<T, String> mapper, String path) throws StorageException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(mapper.apply(item));
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
    }

    public void append(T item, Function<T, String> mapper, String path, String header) throws StorageException {
        boolean shouldWriteHeader = false;
        File file = new File(path);
        if (file.length() == 0) {
            shouldWriteHeader = true;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            if (shouldWriteHeader) {
                writer.println(header);
            }
            writer.println(mapper.apply(item));
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
    }

    public String[] readHeaderMapper(String line) {
        String[] out = new String[1];
        out[0] = line;
        return out;
    }

    public String writeHeaderMapper(String[] lineArray) {
        return lineArray[0];
    }
}
