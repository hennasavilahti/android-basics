// enum-tietotyyppi color
enum Color {
    RED, GREEN, BLUE, YELLOW, ORANGE, PURPLE, WHITE
}
public class Note {
    // private-muuttujat
    private String content;
    private Color backgroundColor;

    // Oletusrakentaja
    public Note() {
        this.content = "";
        this.backgroundColor = Color.WHITE;
    }

    // Parametrillinen konstruktori
    public Note(String content) {
        this.content = content;
        backgroundColor = Color.WHITE;
    }
    public Note(Color backgroundColor) {
        this.content = "";
        this.backgroundColor = backgroundColor;
    }
    public Note(String content, Color backgroundColor) {
        this.content = content;
        this.backgroundColor = backgroundColor;
    }

    // Getterit ja setterit datalle
    public String getContent() {
        return content;
    }
    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    // Noten tulostusmetodi
    public void printNote() {
        System.out.println("Content: " + content + ", BackgroundColor: " + backgroundColor);
    }

    @Override
    public String toString() {
        return "Content: " + content + ", BackgroundColor: " + backgroundColor;
    }
}
