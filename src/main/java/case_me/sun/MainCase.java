package case_me.sun;

import case_me.sun.case_gne.*;

public class MainCase {


    public static void main(String[] args) {
        try {
            Target_Gne.generate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            Target_Model.generate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
