package chapter17.zeitmessung;

import java.io.*;
import java.util.Scanner;

public class Gepuffert {
    public long time() throws FileNotFoundException {
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            new Gepuffert().copy("test.mp3", "copy.mp3");
            long end = System.currentTimeMillis();
            sum += end - start;
        }
        return sum / 10;
    }

    public void choose() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Pfad der Quelldatei ein:");
        String source = scanner.nextLine();
        System.out.println("Bitte geben Sie den Pfad der Zieldatei ein:");
        String destination = scanner.nextLine();
        try {
            copy(source, destination);
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
            choose();
        }
    }

    public void copy(String source, String destination) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream("test.mp3");
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream("copy.mp3");
            bos = new BufferedOutputStream(fos);
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        } finally {
            try {
                if (bos != null) bos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bis != null) bis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Kopieren dauerte " + (end - start) + " ms");
    }
}
