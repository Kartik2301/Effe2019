package com.effe.android.effe2019;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ForSponsors implements Parcelable {
    public String name;
    public String imageUrl;
    public List<String> categories;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeStringList(this.categories);
    }

    public ForSponsors() {
    }

    protected ForSponsors(Parcel in) {
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.categories = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ForSponsors> CREATOR = new Parcelable.Creator<ForSponsors>() {
        @Override
        public ForSponsors createFromParcel(Parcel source) {
            return new ForSponsors(source);
        }

        @Override
        public ForSponsors[] newArray(int size) {
            return new ForSponsors[size];
        }
    };
}
