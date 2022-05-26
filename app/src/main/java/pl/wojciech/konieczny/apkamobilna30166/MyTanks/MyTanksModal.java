package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tanks_table")
public class MyTanksModal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private String mileage;
    private float litersFuel;
    private String costPerLiter;
    private String costSum;
    private String gasStation;

    public MyTanksModal(String date, String mileage, float litersFuel, String costPerLiter, String costSum, String gasStation) {
        this.date = date;
        this.mileage = mileage;
        this.litersFuel = litersFuel;
        this.costPerLiter = costPerLiter;
        this.costSum = costSum;
        this.gasStation = gasStation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public float getLitersFuel() {
        return litersFuel;
    }

    public void setLitersFuel(int litersFuel) {
        this.litersFuel = litersFuel;
    }

    public String getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(String costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    public String getCostSum() {
        return costSum;
    }

    public void setCostSum(String costSum) {
        this.costSum = costSum;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }
}
