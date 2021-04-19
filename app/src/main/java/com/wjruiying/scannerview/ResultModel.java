package com.wjruiying.scannerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author changshuaiji
 * @description <p> event model </P>
 * @date 2021/4/19
 */
public class ResultModel implements Parcelable {
    private int width;
    private int height;
    private String result;

    public ResultModel() {
    }

    protected ResultModel(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        result = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultModel> CREATOR = new Creator<ResultModel>() {
        @Override
        public ResultModel createFromParcel(Parcel in) {
            return new ResultModel(in);
        }

        @Override
        public ResultModel[] newArray(int size) {
            return new ResultModel[size];
        }
    };

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
