package com.ruwant.eam.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 00265372 on 2017/2/22.
 */

public class CommentDetail implements Parcelable, Cloneable {
    public String commentContent;
    public int commentId;

    public CommentDetail clone() {
        CommentDetail commentDetail = null;
        try {
            commentDetail = (CommentDetail) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return commentDetail;
    }

    @Override
    public String toString() {
        return "CommentDetail{" +
                " commentContent ='" + commentContent + '\'' +
                ", commentId ='" + commentId + '\'' +
                '}';
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        super.writeToParcel(dest, flags);
        dest.writeString(this. commentContent);
        dest.writeInt(this. commentId);
    }

    public CommentDetail() {}

    protected CommentDetail(Parcel in) {
//        super(in);
        this. commentContent = in.readString();
        this. commentId= in.readInt();
    }

    public static final Creator<CommentDetail> CREATOR = new Creator<CommentDetail>() {
        public CommentDetail createFromParcel(Parcel source) {return new CommentDetail(source);}

        public CommentDetail[] newArray(int size) {return new CommentDetail[size];}
    };

    private void setCommentContent(String strContent){
        commentContent = strContent;
    }

    private String getCommentContent(){
        return commentContent;
    }

    private void setCommentId(int intId){
        commentId = intId;
    }

    private int getCommentId(){
        return commentId;
    }
}

