public class Kala extends Eläin{
    public Kala(String aNimi) {
        super(aNimi);
    }

    @Override
    public void toimi() {
        System.out.println(mNimi + " ui.");
    }
}
