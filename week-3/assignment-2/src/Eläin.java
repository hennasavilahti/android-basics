public class Eläin {
    //Protected muuttuja koska tuntiesimerkeissä tehtiin näin
    protected String mNimi;

    public Eläin(){
        mNimi = "nimetön";
    }
    public Eläin(String aNimi){
        mNimi = aNimi;
    }

    public void herää() {
        System.out.println(mNimi + " herää.");
    }
    public void lepää() {
        System.out.println(mNimi + " lepää.");
    }
    public void toimi() {
        System.out.println(mNimi + " toimii.");
    }
}