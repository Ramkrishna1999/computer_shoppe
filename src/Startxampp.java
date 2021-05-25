
import java.io.IOException;


public class Startxampp {
        
   public void startxampp() throws IOException {
         Runtime r=Runtime.getRuntime();
        Process p=null;
       p=r.exec(" C:\\xampp\\xampp_start");
    }
   public void stopxampp() throws IOException {
         Runtime r=Runtime.getRuntime();
        Process p=null;
       p=r.exec(" C:\\xampp\\xampp_stop");
    }
             
    }
    