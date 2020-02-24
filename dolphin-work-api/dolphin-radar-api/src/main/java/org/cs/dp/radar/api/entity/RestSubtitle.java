package org.cs.dp.radar.api.entity;

public class RestSubtitle {
    private boolean on=false;
    private String content;
    private int displaySpeed;
    private int displayRepetitions;
    private int verticalBorder;
    private int transparency;
    private int fontSize;
    private String foregroundColor;
    private String backgroundColor;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDisplaySpeed() {
        return displaySpeed;
    }

    public void setDisplaySpeed(int displaySpeed) {
        this.displaySpeed = displaySpeed;
    }

    public int getDisplayRepetitions() {
        return displayRepetitions;
    }

    public void setDisplayRepetitions(int displayRepetitions) {
        this.displayRepetitions = displayRepetitions;
    }

    public int getVerticalBorder() {
        return verticalBorder;
    }

    public void setVerticalBorder(int verticalBorder) {
        this.verticalBorder = verticalBorder;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
