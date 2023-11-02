package org.ifsp.models.user;

import com.google.gson.annotations.Expose;

import java.io.PrintWriter;
import java.util.Random;

public class User {
    private final int Id = new Random().nextInt(1, 1000);
    private String name;
    private PrintWriter printWriter;


    public User(String name, PrintWriter printWriter) {
        this.name = name;
        this.printWriter = printWriter;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
