package utils;

import dao.Staff;
import javafx.scene.control.Alert;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvUtil {

    /**
     * 描述：获取IO流中的数据，组装成List<Object[]>对象
     * @param path  物理文件地址
     * @return
     * @throws Exception
     */
    public List<List<String>> getCsvData(String path){
        List<List<String>> list = null;
        try {
            //BufferedReader reader = new BufferedReader(new FileReader("E:\\work\\javaProject\\ExcelCompare\\src\\main\\java\\excel\\data_new.csv"));//换成你的文件名
            File f = new File("src/main/resources" + path);
//            System.out.println(f.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(f.getCanonicalPath()));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;

            list = new ArrayList<>();
            List<String> tampList = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                tampList = new ArrayList<String>();
                for (String obj:item) {
                    tampList.add(obj);
                }
                list.add(tampList);

//                String last = item[item.length-1];//这就是你要的数据了
//                int value = Integer.parseInt(last);//如果是数值，可以转化为数值
//                System.out.println(list);
            }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
//        OutputList(list);

        return list;
    }

    private static void OutputList(List<Object[]> dataList) {
        //数据封装格式一，将表格中的数据遍历取出后封装进对象放进List
        for (int i = 0; i < dataList.size(); i++) {
//            System.out.println("----------------"+dataList.get(i).length);
            System.out.println();
            for (Object obj : dataList.get(i)) {
                System.out.print(obj);
                System.out.print("    ");
//                System.out.println(obj);
            }
        }
    }

    public Staff FindStaff(String staffId,String path) throws FileNotFoundException {
        Staff staff = null;
        try {
            FileReader fr = new FileReader("src/main/resources" + path);
            BufferedReader reader = new BufferedReader(fr);
            reader.readLine();
            String line = null;
            List<Object> list = null;

            while((line = reader.readLine()) != null) {
                list = new ArrayList<Object>();
                String[] item = line.split(",");
                for(Object obj:item) {
                    list.add(obj);
                }
                if(list.get(0).equals(staffId)) {
//                    System.out.println("list1" + list.get(1).toString());
//                    System.out.println("list2" + list.get(2).toString());
//                    System.out.println((LocalDate)list.get(3));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    System.out.println("日期" + list.get(3).toString());
                    staff = new Staff(list.get(1).toString(),staffId,list.get(2).toString(),sdf.parse(list.get(3).toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public Boolean DeleteStaff(String staffId,String path)
    {
        try{

            FileReader fr = new FileReader("src/main/resources" + path);
            BufferedReader br = new BufferedReader(fr);
//            br.readLine();

            String line =null;
            int flag = 0;
            List<String> list = new ArrayList<String>();
            while((line = br.readLine()) != null) {
                String[] item = line.split(",");
                list.add(line);
                if(item[0].equals(staffId)) {
                    flag = 1;
                    list.remove(list.size()-1);
                    System.out.println("已经找到员工号" + staffId);
                }
            }
            if(flag == 1) {
//                覆盖式写法
                FileWriter fw = new FileWriter("src/main/resources" + path,false);
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i=0;i<list.size();i++) {
                    bw.write(list.get(i));
                    System.out.println(list.get(i));
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                System.out.println("删除成功");
                return true;
            }
            else {
                System.out.println("删除失败，可能已经删除，不要重复点击");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean FindId(String staffId,String path) {
        try {
            //BufferedReader reader = new BufferedReader(new FileReader("E:\\work\\javaProject\\ExcelCompare\\src\\main\\java\\excel\\data_new.csv"));//换成你的文件名
            FileReader fr = new FileReader("src/main/resources" + path);
//            System.out.println(f.getCanonicalPath());
            BufferedReader reader = new BufferedReader(fr);//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;

            List<Object> tampList = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                tampList = new ArrayList<Object>();
                for (Object obj:item) {
                    tampList.add(obj);
                }
//                System.out.println(tampList.get(0).toString());
                if(tampList.get(0).toString().equals(staffId))
                    return true;
            }
            reader.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean AddStaff(Staff staff,String path)
    {
        String sex;
        if(staff.getSex() == "男")
            sex = "man";
        else
            sex = "woman";
        try {
//            true = append, false = overwrite!!!
            FileWriter fw = new FileWriter("src/main/resources" + path,true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.newLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            writer.write(staff.getStaffId() + "," + staff.getStaffName() + "," + sex + "," + sdf.format(staff.getCreateDate()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean SetScore(String id,String date,String hygiene,String attendance,String politics,String contribution,String cooperation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(date);
            Staff staff = FindStaff(id,"/csv/staff.csv");
            if(date1.before(staff.getCreateDate()))
            {
                System.out.println("评分日期早于员工入职时日期!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("添加员工评分状态");
                alert.setContentText("评分日期早于员工入职时日期!");
                alert.showAndWait();
                return false;
            }
            FileReader fr = new FileReader("src/main/resources/csv/score.csv");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            String log = id+","+date+","+hygiene+","+attendance+","+politics+","+contribution+","+cooperation;
            int flag = 0;
            List<String> list = new ArrayList<>();
            while((line = br.readLine()) != null) {
                String[] item = line.split(",");
                list.add(line);
                System.out.println(item[0] + " " + item[1]);
//                已有这条记录，覆盖
                if(item[0].equals(id) && item[1].equals(date)) {
                    flag = 1;
                    list.set(list.size() - 1,log);
                }
            }
            if(flag == 0) {
                FileWriter fw = new FileWriter("src/main/resources/csv/score.csv",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(log);
                bw.newLine();
                bw.flush();
                bw.close();
            }
            else {
                FileWriter fw = new FileWriter("src/main/resources/csv/score.csv",false);
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i=0;i<list.size();i++) {
                    bw.write(list.get(i));
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            }
            return  true;
        } catch (ParseException e) {
            System.out.println("日期格式有误");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("添加员工评分状态");
            alert.setContentText("添加失败,日期格式有误!");
            alert.showAndWait();
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Staff FindStaffByName(String staffName, String path) {
        Staff staff = null;
        try {
            FileReader fr = new FileReader("src/main/resources" + path);
            BufferedReader reader = new BufferedReader(fr);
            reader.readLine();
            String line = null;
            List<Object> list = null;

            while((line = reader.readLine()) != null) {
                list = new ArrayList<Object>();
                String[] item = line.split(",");
                for(Object obj:item) {
                    list.add(obj);
                }
                if(list.get(1).equals(staffName)) {
//                    System.out.println("list1" + list.get(1).toString());
//                    System.out.println("list2" + list.get(2).toString());
//                    System.out.println((LocalDate)list.get(3));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    System.out.println("日期" + list.get(3).toString());
                    staff = new Staff(list.get(1).toString(),list.get(0).toString(),list.get(2).toString(),sdf.parse(list.get(3).toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public Boolean checkPassword(String text, String path) {
        try {
            FileReader fr = new FileReader("src/main/resources" + path);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String[] items = br.readLine().split(",");
            if(items[1].equals(text)) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean SetAdminPassword(String text, String path) {
        try {
            FileWriter fw = new FileWriter("src/main/resources" + path,false);
            BufferedWriter br = new BufferedWriter(fw);
            br.write("adminName,password");
            br.newLine();
            br.write("admin," + text);
            br.flush();
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}