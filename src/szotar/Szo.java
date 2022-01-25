/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */

package szotar;

/* @author Máté Andrea */
public class Szo {
    private int szoID;
    private String lecke;
    private String angol;
    private String magyar;

    public Szo(int szoID, String lecke, String angol, String magyar) {
        this.szoID = szoID;
        this.lecke = lecke;
        this.angol = angol;
        this.magyar = magyar;
    }

    public int getSzoID() {
        return szoID;
    }

    public String getLecke() {
        return lecke;
    }

    public String getAngol() {
        return angol;
    }

    public String getMagyar() {
        return magyar;
    }

}
