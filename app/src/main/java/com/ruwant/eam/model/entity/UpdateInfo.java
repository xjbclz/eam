package com.ruwant.eam.model.entity;

import android.os.Parcel;

/**
 * Created by 00265372 on 2017/2/12.
 */

public class UpdateInfo extends Model  {

    // 800不强制升级 801强制升级
    public int upgrade;

    public String version;

    //提示框标题栏显示的文字
    //public String title;

    public String url;

    //显示提示信息次数,不支持小数;如为 0,则没有限制
    public int limitTimes;

    //显示提示信息的时间间隔,以小时为单位,不支持小数
    public int interval;

    //显示服务器端的更新日志
    public String changes;

    public String size;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.upgrade);
        dest.writeString(this.version);
        //dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeInt(this.limitTimes);
        dest.writeInt(this.interval);
        dest.writeString(this.changes);
        dest.writeString(this.size);
    }

    public UpdateInfo() {
    }

    protected UpdateInfo(Parcel in) {
        this.upgrade = in.readInt();
        this.version = in.readString();
        //this.title = in.readString();
        this.url = in.readString();
        this.limitTimes = in.readInt();
        this.interval = in.readInt();
        this.changes = in.readString();
        this.size = in.readString();
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "upgrade=" + upgrade +
                ", version='" + version + '\'' +
                //", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", limitTimes=" + limitTimes +
                ", interval=" + interval +
                ", changes='" + changes + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public static final Creator<UpdateInfo> CREATOR = new Creator<UpdateInfo>() {
        @Override
        public UpdateInfo createFromParcel(Parcel source) {
            return new UpdateInfo(source);
        }

        @Override
        public UpdateInfo[] newArray(int size) {
            return new UpdateInfo[size];
        }
    };
}
