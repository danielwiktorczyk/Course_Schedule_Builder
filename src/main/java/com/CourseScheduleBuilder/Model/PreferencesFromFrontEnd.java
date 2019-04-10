package com.CourseScheduleBuilder.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PreferencesFromFrontEnd {
    @JsonProperty
    private boolean mm;
    @JsonProperty
    private boolean me;
    @JsonProperty
    private boolean mall;
    @JsonProperty
    private boolean tm;
    @JsonProperty
    private boolean te;
    @JsonProperty
    private boolean tall;
    @JsonProperty
    private boolean wm;
    @JsonProperty
    private boolean we;
    @JsonProperty
    private boolean wall;
    @JsonProperty
    private boolean thm;
    @JsonProperty
    private boolean the;
    @JsonProperty
    private boolean thall;
    @JsonProperty
    private boolean fm;
    @JsonProperty
    private boolean fe;
    @JsonProperty
    private boolean fall;

    public boolean isMm() {
        return mm;
    }

    public void setMm(boolean mm) {
        this.mm = mm;
    }

    public boolean isMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    public boolean isMall() {
        return mall;
    }

    public void setMall(boolean mall) {
        this.mall = mall;
    }

    public boolean isTm() {
        return tm;
    }

    public void setTm(boolean tm) {
        this.tm = tm;
    }

    public boolean isTe() {
        return te;
    }

    public void setTe(boolean te) {
        this.te = te;
    }

    public boolean isTall() {
        return tall;
    }

    public void setTall(boolean tall) {
        this.tall = tall;
    }

    public boolean isWm() {
        return wm;
    }

    public void setWm(boolean wm) {
        this.wm = wm;
    }

    public boolean isWe() {
        return we;
    }

    public void setWe(boolean we) {
        this.we = we;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public boolean isThm() {
        return thm;
    }

    public void setThm(boolean thm) {
        this.thm = thm;
    }

    public boolean isThe() {
        return the;
    }

    public void setThe(boolean the) {
        this.the = the;
    }

    public boolean isThall() {
        return thall;
    }

    public void setThall(boolean thall) {
        this.thall = thall;
    }

    public boolean isFm() {
        return fm;
    }

    public void setFm(boolean fm) {
        this.fm = fm;
    }

    public boolean isFe() {
        return fe;
    }

    public void setFe(boolean fe) {
        this.fe = fe;
    }

    public boolean isFall() {
        return fall;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }
}
