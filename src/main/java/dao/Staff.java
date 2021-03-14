package dao;

import java.time.LocalDate;
import java.util.Date;

public class Staff {
    private String staffName;

    private String staffId;

    private String sex;

    private Date createDate;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Staff() {
    }

    public Staff(String staffName, String staffId, String sex, Date createDate) {
        this.staffName = staffName;
        this.staffId = staffId;
        this.sex = sex;
        this.createDate = createDate;
    }
}
