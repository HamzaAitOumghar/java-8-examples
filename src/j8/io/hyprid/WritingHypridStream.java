package j8.io.hyprid;

import j8.io.util.AesopReader;
import j8.io.util.Fable;
import j8.io.util.FableData;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class WritingHypridStream {


    public static void main(String[] args) {

        AesopReader aesopReader = new AesopReader();
        List<Fable> fables = aesopReader.readFable("files/aesop.txt");
        System.out.println("#fable = " + fables.size());
        List<FableData> fablesDatas = new ArrayList<>();


        try (ByteArrayOutputStream aesopBos = new ByteArrayOutputStream();
             OutputStreamWriter out = new OutputStreamWriter(aesopBos, StandardCharsets.UTF_8);
             PrintWriter printer = new PrintWriter(out);
        ) {

            ByteArrayOutputStream textBos = new ByteArrayOutputStream();
            int offset = aesopBos.size();
            for (Fable f : fables) {
                ByteArrayOutputStream fableBos = new ByteArrayOutputStream();
                try (GZIPOutputStream gzip = new GZIPOutputStream(fableBos)) {
                     gzip.write(f.getTitle().getBytes());
                }
                catch (Exception E){
                    E.printStackTrace();
                }
                int length = fableBos.size();
                textBos.write(fableBos.toByteArray());
                offset+=length;
                FableData fableData =new FableData(f,offset,length);
                fablesDatas.add(fableData);
            }

            printer.println("Aesop's Fables");
            printer.printf("%d\n", fables.size());

            for (FableData f : fablesDatas) {
                printer.printf("%7d %7d %s\n", f.getOffset() ,f.getLength(), f.getFable().getTitle());
            }
            System.out.println(new String(aesopBos.toByteArray()));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
