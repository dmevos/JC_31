import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        final String GAMEDIR = "C://NetologyProject//JavaCore//JC_31//Games";

        File g = new File(GAMEDIR);
        if (g.mkdir()) {
            log.append(String.format("%tF %<tT", new Date()) + " Создан корневой каталог игры: Games\n");
        } else {
            log.append(String.format("%tF %<tT", new Date()) + " Каталог Games не создан");
            if (g.exists()) log.append(" ->  Каталог уже существует.\n");
        }

        String[] arrDirs = {"src", "res", "savegames",
                "temp", "src//main", "src//test",
                "res//drawables", "res//vectors", "res//icons"};
        String[] arrFiles = {"src//main//Main.java", "src//main//Utils.java"};

        for (String dir : arrDirs) {
            File dirSrc = new File(GAMEDIR + "//" + dir);
            if (dirSrc.mkdir()) {
                log.append(String.format("%tF %<tT", new Date()) + " Каталог " + GAMEDIR + "//" + dir + " создан.\n");
            } else {
                log.append(String.format("%tF %<tT", new Date()) + " Каталог " + GAMEDIR + "//" + dir + " не создан");
                if (dirSrc.exists()) log.append(" ->  Каталог уже существует.\n");
            }
        }
        for (String file : arrFiles) {
            File fileSrc = new File(GAMEDIR + "//" + file);
            try {
                if (fileSrc.createNewFile()) {
                    log.append(String.format("%tF %<tT", new Date()) + " Файл " + GAMEDIR + "//" + file + " был создан\n");
                } else {
                    log.append(String.format("%tF %<tT", new Date()) + " Файл " + GAMEDIR + "//" + file + " не был создан");
                    if (fileSrc.exists()) log.append(" ->  Файл уже существует.\n");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter(GAMEDIR + "//temp//temp.txt")) {
            log.append(String.format("%tF %<tT", new Date()) + " - создан НОВЫЙ файл " + GAMEDIR + "//temp//temp.txt");
            log.append(" и в нем имеется информация о ходе установки.");
            writer.write(log.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Отчет о ходе установки смотри: " + GAMEDIR + "//temp//temp.txt ");
    }
}
