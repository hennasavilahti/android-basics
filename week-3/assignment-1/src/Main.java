
public class Main {
    public static void main(String[] args) {

        Note empty = new Note();
        Note note1 = new Note(Color.GREEN);
        Note note2 = new Note("Hello World!");
        Note note3 = new Note("Muista Android -tunnit", Color.BLUE);

        empty.printNote();
        note1.printNote();
        System.out.println(note2);
        note3.printNote();
    }
}