package controller;

import dao.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.CsvUtil;
//import utils.JDBCUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddStaffController {

    @FXML
    private TextField InputWname;

    @FXML
    private ComboBox<String> selectWsex;

    @FXML
    private TextField InputWaccount;

    @FXML
    private ComboBox<String> selectWunit;

//    @FXML
//    private TextField InputWpw;

    @FXML
    private Button Wcancel;

    @FXML
    private Button wsure;

    @FXML
    private DatePicker datePick2;

    @FXML
    private Label labelId;

//    @FXML
//    private Label labelUint;

    @FXML
    private Label lableName1;

    @FXML
    private Label labelSex1;

    @FXML
    private Label labelPwd;

    @FXML
    private Label labelDate;

    private static String staffCsvPath = "/csv/staff.csv";
//    @FXML
//    private Label dateMsg;

//    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    private CsvUtil csvUtil = new CsvUtil();

    @FXML
    public void onclickSelectWsex() {
        //当前选中的是下拉列表中某个值
        System.out.println(selectWsex.getValue());
    }

    @FXML
    public void onclikSelectWunit() {
        System.out.println(selectWunit.getValue());
    }

    //清空列表
    @FXML
    public void wMakeCancel() {
//        System.out.println("wMakeCancel");
        InputWname.setText("");
        InputWaccount.setText("");
//        InputWpw.setText("");
        selectWsex.getSelectionModel().select(0);
//        selectWunit.getSelectionModel().select(0);
        datePick2.setValue(null);

        lableName1.setText("");
        labelSex1.setText("");
        labelDate.setText("");
        labelPwd.setText("");
        labelId.setText("");
//        labelUint.setText("");
//        dateMsg.setText("");
    }

    @FXML
    public void wMakesure() {
        boolean flag = false;
        String wName = InputWname.getText();
        if(wName == null || wName.equals("")) {
            lableName1.setText("姓名不能为空~");
            flag = true;
        } else {
            lableName1.setText("");
        }
        String wAccount = InputWaccount.getText();
        if(wAccount == null || wAccount.equals("")) {
            labelId.setText("员工号不能为空~");
            flag = true;
        } else {
            String strRegex = "^[A-Za-z0-9]+$";
            boolean isNumId = wAccount.matches(strRegex);
            if (!isNumId) {
                labelId.setText("输入包含非数字或者非字母~");
                flag = true;
            } else {
                labelId.setText("");
            }
        }
//        String wPwd = InputWpw.getText();
//        if(wPwd == null || wPwd.equals("")) {
//            labelPwd.setText("密码不能为空~");
//            flag = true;
//        } else {
//            labelPwd.setText("");
//        }

        //弹窗或在窗口页面中显示错误
        String sex = selectWsex.getValue();
        if(sex == null || sex.equals("选择性别")) {
            labelSex1.setText("请选择性别~");
            flag = true;
        } else {
            labelSex1.setText("");
        }

        //获取日期：2种方式
        System.out.println(datePick2.getEditor().getText());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(String.valueOf(datePick2.getValue()));
//            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println("date:" + date);

        if(date == null) {
            labelDate.setText("日期输入有误");
            flag = true;
        } else {
            //注意要设置为null
            if(!flag) datePick2.setValue(null);
            labelDate.setText("");
        }
        System.out.println(wName + " " + wAccount + " " + sex + " " + " " + date);
        if(flag) return;
//        String sql = "SELECT COUNT(*) FROM workers where WId = ?";
//        Long cnt = template.queryForObject(sql, Long.class, wAccount);
        Boolean jdg = csvUtil.FindId(wAccount,staffCsvPath);
        if(jdg) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("添加工作人员状态");
            alert.setContentText("添加失败，插入2个相同的工作人员账号~");
            alert.showAndWait();
        } else {
//            sql = "INSERT INTO workers(WId, WPasswd, WName, WSex, WUnit, WDate) VALUES(?, ?, ?, ?, ?, ?);";
//            int count = template.update(sql, wAccount, wName, sex, date);
            Boolean jdg1 = csvUtil.AddStaff(new Staff(wName,wAccount,sex,date),staffCsvPath);
            if(jdg1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("添加工作人员状态");
                alert.setContentText("成功添加一名工作人员：" + wName + "。");
                alert.show();
                wMakeCancel();
                System.out.println("插入记录成功！");
            } else {
                System.out.println("插入记录失败~");
            }
        }
    }
}
