package de.itemis.calender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SvgWriter {
    private File file;
    private String fileName = "calender.Calender.svg";
    PrintWriter pw;
    public SvgWriter() throws IOException {
        this.file = new File(fileName);
        this.file.createNewFile();
        this.pw = new PrintWriter(file);
    }
    public void write(String writable){
        this.pw.write(writable);
    }
}
