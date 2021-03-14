package dao;

import java.time.LocalDate;

public class Score {
    private Staff staff;

    private LocalDate date;

//    卫生评分
    private int hygiene;

//    考勤评分
    private int attendance;

//    政治评分
    private int politics;

//    贡献度评分
    private int contribution;

//    合作度评分
    private int cooperation;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHygiene() {
        return hygiene;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getPolitics() {
        return politics;
    }

    public void setPolitics(int politics) {
        this.politics = politics;
    }

    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public int getCooperation() {
        return cooperation;
    }

    public void setCooperation(int cooperation) {
        this.cooperation = cooperation;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Score() {
    }

    public Score(Staff staff, LocalDate date, int hygiene, int attendence, int politics, int contribution, int cooperation) {
        this.staff = staff;
        this.date = date;
        this.hygiene = hygiene;
        this.attendance = attendence;
        this.politics = politics;
        this.contribution = contribution;
        this.cooperation = cooperation;
    }
}
