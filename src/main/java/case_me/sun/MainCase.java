package case_me.sun;

import case_me.sun.case_gne.Target_Gne;

public class MainCase {


    public static void main(String[] args) {
        try {
            Target_Gne.generate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
