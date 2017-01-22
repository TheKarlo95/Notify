package hr.karlovrbic.notify.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class MessageCreate implements Parcelable {

    public static final Creator<MessageCreate> CREATOR = new Creator<MessageCreate>() {
        @Override
        public MessageCreate createFromParcel(Parcel source) {
            return new MessageCreate(source);
        }

        @Override
        public MessageCreate[] newArray(int size) {
            return new MessageCreate[size];
        }
    };

    private static final String ATTRIBUTE_CONTENT = "content";

    @SerializedName(ATTRIBUTE_CONTENT)
    @Expose
    private String content;

    public MessageCreate(String content) {
        this.content = content;
    }

    public MessageCreate() {
    }

    protected MessageCreate(Parcel in) {
        this.content = in.readString();
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MessageCreate that = (MessageCreate) o;

        return new EqualsBuilder()
                .append(content, that.content)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "MessageCreate{content='" + content + '}';
    }
}

