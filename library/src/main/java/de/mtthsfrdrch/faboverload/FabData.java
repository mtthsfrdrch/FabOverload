package de.mtthsfrdrch.faboverload;

public class FabData {

    private int id;
    private String title;
    private int backgroundRes;
    private int imageRes;

    public FabData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
