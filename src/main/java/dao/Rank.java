package dao;

public class Rank {
    private int rank;
    private String id;
    private String name;
    private String sex;
    private String date;
    private String weekScore;
    private String monthScore;
    private String todayScore;

    public Rank() { }

    public Rank(int rank, String id, String name, String sex, String date, String weekScore, String monthScore, String todayScore) {
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.weekScore = weekScore;
        this.monthScore = monthScore;
        this.todayScore = todayScore;
    }

    public Rank(int rank, String id, String name, String sex, String date, String weekScore, String todayScore) {
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.weekScore = weekScore;
        this.todayScore = todayScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekScore() {
        return weekScore;
    }

    public void setWeekScore(String weekScore) {
        this.weekScore = weekScore;
    }

    public String getTodayScore() {
        return todayScore;
    }

    public void setTodayScore(String todayScore) {
        this.todayScore = todayScore;
    }

    public String getMonthScore() {
        return monthScore;
    }

    public void setMonthScore(String monthScore) {
        this.monthScore = monthScore;
    }
}
