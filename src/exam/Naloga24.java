package exam;

import java.io.*;
import java.nio.charset.Charset;

public class Naloga24 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Napačno število argumentov!");
            System.exit(0);
        }

        BeriPisiTXT beriPisi = new BeriPisiTXT();
        String vhodnaDatoteka = args[0];
        String izhodnaDatoteka = args[1];

        if (args.length < 3) {
            // prepišem datoteko v privzetem (UTF-8) kodirnem sistemu
            String vsebina = beriPisi.preberi(vhodnaDatoteka);
            beriPisi.zapisi(izhodnaDatoteka, vsebina);
        } else { // 3. in 4. argument sta imeni kodirnih sistemov
            String vhodniCharset = args[2];
            String izhodniCharset = args[3];
            // prepišem datoteko iz enega v drug kodirni sistem
            String vsebina = beriPisi.preberi(vhodnaDatoteka, vhodniCharset);
            beriPisi.zapisi(izhodnaDatoteka, vsebina, izhodniCharset);
        }

    }
}

interface BeriPisi {
    String preberi(String imeDatoteke);
    void zapisi(String imeDatoteke, String vsebina);
}

class BeriPisiTXT implements BeriPisi {

    @Override
    public String preberi(String filename) {
        return this.preberi(filename, "UTF-8");
    }

    @Override
    public void zapisi(String filename, String content) {
        zapisi(filename, content, "UTF-8");
    }

    public String preberi(String filename, String encoding) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(filename), Charset.forName(encoding));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffReader = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        try {
            while (buffReader.ready()) {
                content.append((char) buffReader.read());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    void zapisi(String filename, String content, String encoding) {
        OutputStreamWriter writer;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename), Charset.forName(encoding));
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
