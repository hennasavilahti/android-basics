public class Lintu extends Eläin{
    public Lintu(String aNimi) {
        super(aNimi);
    }

    @Override
    public void toimi() {
        System.out.println(mNimi + " lentää.");
    }
}
