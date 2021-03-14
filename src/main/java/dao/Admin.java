package dao;

public class Admin {

    private String adminName;

    private String psw;

    public Admin() {
    }

    public Admin(String adminId, String psw) {
        adminName = adminId;
        this.psw = psw;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
