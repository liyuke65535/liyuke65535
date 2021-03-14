package emp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmpRankLook {

    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty sex = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleIntegerProperty weekScore = new SimpleIntegerProperty();
    private SimpleIntegerProperty monthScore = new SimpleIntegerProperty();

    public EmpRankLook(String id, String name, String sex, String date, int weekScore, int monthScore) {

        this.id.set(id);
        this.name.set(name);
        this.sex.set(sex);
        this.date.set(date);
        this.weekScore.set(weekScore);
        this.monthScore.set(monthScore);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getWeekScore() {
        return weekScore.get();
    }

    public SimpleIntegerProperty weekScoreProperty() {
        return weekScore;
    }

    public void setWeekScore(int weekScore) {
        this.weekScore.set(weekScore);
    }

    public int getMonthScore() {
        return monthScore.get();
    }

    public SimpleIntegerProperty monthScoreProperty() {
        return monthScore;
    }

    public void setMonthScore(int monthScore) {
        this.monthScore.set(monthScore);
    }
}
