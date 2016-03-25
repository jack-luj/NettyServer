package com.obd;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/25.
 */
public class InitTool {
        public List<VirtualThreadCar> initTripId(List<VirtualThreadCar> virtualCarList) {
        DBManager dbManager = new DBManager();
        List<VirtualThreadCar> re = new ArrayList<VirtualThreadCar>();
        for (int i = 0; i < virtualCarList.size(); i++) {
            VirtualThreadCar c = virtualCarList.get(i);
            String sql = "select obdCode,max(tripId) as max_trip_id,max(mileage)as max_mileage from temp_t_obd_drive where obdCode='" + c.getObdCode() + "'";
            try {
                ResultSet rs = dbManager.executeQuery(sql);
                while (rs.next()) {
                    String obdCode = rs.getString("obdCode");
                    int max_trip_id = rs.getInt("max_trip_id");
                    int max_mileage = rs.getInt("max_mileage");
                    c.setTripId(max_trip_id + 1);//tripid自增
                    c.setTotalMileage(max_mileage);
                }
            } catch (SQLException e) {
            }
            re.add(c);
        }
        return re;

    }

    public List<VirtualThreadCar> getObdListFromTxt() {
        Date driveDeadLine = new Date();
        List<VirtualThreadCar> re = new ArrayList<VirtualThreadCar>();
        try {
            System.out.println("loading obd list from file...");
            FileReader fr = new FileReader("d:\\db\\obd_list.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] arrs = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                arrs = line.split(",");
                if (arrs[0] != null && arrs[1] != null && !arrs[0].equals("null") && !arrs[1].equals("null")) {
                    re.add(new VirtualThreadCar(Integer.valueOf(arrs[0]).intValue(), arrs[1], "", driveDeadLine));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {

        }
        System.out.println("loaded "+re.size()+" obd from file.");
    return re;
    }


}
