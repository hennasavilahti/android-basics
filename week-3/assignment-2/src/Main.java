import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
        ArrayList<Eläin> eläimet = new ArrayList<>();
        eläimet.add(new Lintu("Haukka"));
        eläimet.add(new Kala("Kilpikonna"));


        System.out.println("\nAamulla:");
        for(Eläin e : eläimet){
            e.herää();
        }

        System.out.println("\nPäivällä:");
        for(Eläin e : eläimet){
            e.toimi();
        }

        System.out.println("\nIllalla:");
        for(Eläin e : eläimet){
            e.lepää();
        }
    }
}
