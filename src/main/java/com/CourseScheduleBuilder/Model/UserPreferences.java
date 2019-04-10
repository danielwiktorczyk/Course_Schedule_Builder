package com.CourseScheduleBuilder.Model;

public class UserPreferences {
    private boolean mm = false;
    private boolean me = false;
    private boolean mall = false;
    private boolean tm = false;
    private boolean te = false;
    private boolean tall = false;
    private boolean wm = false;
    private boolean we = false;
    private boolean wall = false;
    private boolean thm = false;
    private boolean the = false;
    private boolean thall = false;
    private boolean fm= false;
    private boolean fe = false;
    private boolean fall = false;

    public UserPreferences(){

    }

    public UserPreferences(boolean mm, boolean me, boolean mall, boolean tm, boolean te, boolean tall, boolean wm, boolean we, boolean wall, boolean thm, boolean the, boolean thall, boolean fm, boolean fe, boolean fall) {
        this.mm = mm;
        this.me = me;
        this.mall = mall;
        this.tm = tm;
        this.te = te;
        this.tall = tall;
        this.wm = wm;
        this.we = we;
        this.wall = wall;
        this.thm = thm;
        this.the = the;
        this.thall = thall;
        this.fm = fm;
        this.fe = fe;
        this.fall = fall;
    }

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

    public boolean compare(UserPreferences prefToCompare){
        if(me == prefToCompare.me && mm == prefToCompare.mm && mall == prefToCompare.mall && tm == prefToCompare.tm && te == prefToCompare.te && tall == prefToCompare.tall
                && wm == prefToCompare.wm && we == prefToCompare.we && wall == prefToCompare.wall && the == prefToCompare.the && thm == prefToCompare.thm && thall == prefToCompare.thall
                && fm == prefToCompare.fm && fe == prefToCompare.fe && fall == prefToCompare.fall){
            return true;
        }
        else{
            return false;
        }
    }

}
