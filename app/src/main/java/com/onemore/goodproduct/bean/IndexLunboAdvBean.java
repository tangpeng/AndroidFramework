package com.onemore.goodproduct.bean;/**
 * Created by Administrator on 2017/9/20.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: tangxiaopeng
 * @Data: 2017/9/20 12:38
 * @desc： 首页的轮播和广告和资讯的轮播
 */
public class IndexLunboAdvBean implements Parcelable {

    public int id;
    public String title;
    public String desc;
    public String img_url;
    public String url;
    public int jump_type;
    public int jump_value;

    public IndexLunboAdvBean() {
    }

    protected IndexLunboAdvBean(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        img_url = in.readString();
        url = in.readString();
        jump_type = in.readInt();
        jump_value = in.readInt();
    }

    public static final Creator<IndexLunboAdvBean> CREATOR = new Creator<IndexLunboAdvBean>() {
        @Override
        public IndexLunboAdvBean createFromParcel(Parcel in) {
            return new IndexLunboAdvBean(in);
        }

        @Override
        public IndexLunboAdvBean[] newArray(int size) {
            return new IndexLunboAdvBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getJump_type() {
        return jump_type;
    }

    public void setJump_type(int jump_type) {
        this.jump_type = jump_type;
    }

    public int getJump_value() {
        return jump_value;
    }

    public void setJump_value(int jump_value) {
        this.jump_value = jump_value;
    }

    @Override
    public String toString() {
        return "IndexLunboBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", img_url='" + img_url + '\'' +
                ", url='" + url + '\'' +
                ", jump_type=" + jump_type +
                ", jump_value=" + jump_value +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(img_url);
        parcel.writeString(url);
        parcel.writeInt(jump_type);
        parcel.writeInt(jump_value);
    }
}
