package com.example.innerwheelclub;

public class item
{
    int background;
    String Title;
    int photo;
    public item(Object o)
    {}

    public item(int background, String Title)
    {
        this.background=background;
        this.Title=Title;
    }

    public int getBackground() {
        return background;
    }

    public String getTitle() {
        return Title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}