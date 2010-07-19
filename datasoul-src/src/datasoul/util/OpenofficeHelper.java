/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datasoul.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author samuel
 */
public class OpenofficeHelper {

    private File helper;

    public OpenofficeHelper() throws IOException {

        helper = File.createTempFile("DatasoulOpenofficeHelper", ".odt");
        helper.deleteOnExit();
        InputStream is = OpenofficeHelper.class.getResourceAsStream("OpenofficeHelper.odt");
        FileOutputStream fos = new FileOutputStream(helper);
        int x;
        while ((x=is.read()) != -1){
            fos.write((byte)x);
        }
        is.close();
        fos.close();

    }

    public void dispose() throws IOException {
        helper.delete();
    }

    public String convertImages(File f) throws IOException, InterruptedException{

        // Create a copy of the file
        File tmp = File.createTempFile("dsconv", f.getName());
        tmp.deleteOnExit();
        FileInputStream is = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(tmp);
        int x;
        while ((x=is.read()) != -1){
            fos.write((byte)x);
        }
        is.close();
        fos.close();

        // Run openoffice to convert
        String[] cmd = { "soffice",
            helper.getAbsolutePath(),
            "macro://./Standard.Module1.ConvertToImage(\""+tmp.getAbsolutePath()+"\")"
        };
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        Process proc = pb.start();

        if (proc.waitFor() != 0){
            return null;
        }else{

            File done = new File(tmp.getAbsolutePath()+".done");
            done.deleteOnExit();
            while (!done.exists()){
                System.out.println("Waiting...");
                Thread.sleep(1000);
            }

            done.delete();
        }
        return tmp.getAbsolutePath();
    }

    public static void main(String args[]) {

        try{
            OpenofficeHelper help = new OpenofficeHelper();
            File f =  new File("/tmp/gloire.ppt");
            String b = help.convertImages(f);
            
            System.out.println("Sainda >>> "+b);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
