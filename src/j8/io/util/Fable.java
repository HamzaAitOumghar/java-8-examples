package j8.io.util;

public class Fable {

    private String title;
    private String text;

    public Fable() {
    }

    public Fable(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void addText(String text) {
        if (this.text == null) {
            this.text = text;
        } else {
            this.text += "\n" + text;
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
