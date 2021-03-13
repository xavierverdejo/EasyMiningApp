package com.example.easyminingapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.time.LocalTime;

public class ProfitObject implements Parcelable {

    LocalTime date;
    double resultPerDay, resultPerWeek, resultPerMonth, mhs, watts, cost, poolFee, rewardPerMhs;

    public ProfitObject(LocalTime date, double resultPerDay, double resultPerWeek, double resultPerMonth)
    {
        Log.d("ProfitObject", "New ProfitObject created");
        this.date = date;
        this.resultPerDay = resultPerDay;
        this.resultPerWeek = resultPerWeek;
        this.resultPerMonth = resultPerMonth;
    }

    public ProfitObject(Parcel in) {
        resultPerDay = in.readDouble();
        resultPerWeek = in.readDouble();
        resultPerMonth = in.readDouble();
        mhs = in.readDouble();
        watts = in.readDouble();
        cost = in.readDouble();
        poolFee = in.readDouble();
        rewardPerMhs = in.readDouble();
    }

    public static final Creator<ProfitObject> CREATOR = new Creator<ProfitObject>() {
        @Override
        public ProfitObject createFromParcel(Parcel in) {
            return new ProfitObject(in);
        }

        @Override
        public ProfitObject[] newArray(int size) {
            return new ProfitObject[size];
        }
    };

    public LocalTime getDate() {
        return date;
    }

    public double getResultPerDay() {
        return resultPerDay;
    }

    public double getResultPerWeek() {
        return resultPerWeek;
    }

    public double getResultPerMonth() {
        return resultPerMonth;
    }

    public double getMhs() {
        return mhs;
    }
    public double getWatts() {
        return watts;
    }
    public double getCost() {
        return cost;
    }
    public double getPoolFee() {
        return poolFee;
    }
    public double getRewardPerMhs() {
        return rewardPerMhs;
    }


    public void setData(double mhs, double watts, double cost, double poolFee, double rewardPerMhs)
    {
        this.mhs = mhs;
        this.watts = watts;
        this.cost = cost;
        this.poolFee = poolFee;
        this.rewardPerMhs = rewardPerMhs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(resultPerDay);
        dest.writeDouble(resultPerWeek);
        dest.writeDouble(resultPerMonth);
        dest.writeDouble(mhs);
        dest.writeDouble(watts);
        dest.writeDouble(cost);
        dest.writeDouble(poolFee);
        dest.writeDouble(rewardPerMhs);
    }
}
