package HighScores;

import java.io.*;
import java.nio.file.Files;

public class HighScoreManager {

    private static Highscore highscore;
    private File file;


    public HighScoreManager() throws IOException, ClassNotFoundException {
        file = new File("HighScores");

        if(file.length() != 0) {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            ByteArrayInputStream bis = new ByteArrayInputStream(fileContent);

            try (ObjectInput in = new ObjectInputStream(bis)) {
                highscore = (Highscore) in.readObject();
            }
        } else highscore = new Highscore();
    }

    public Highscore getHighscore() {
        return highscore;
    }

    public static void saveAll() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        byte[] content = null;

        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(highscore);
            out.flush();

            content = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ignored) { }
        }
        try (FileOutputStream fos = new FileOutputStream("HighScores")) {
            assert content != null;
            fos.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
