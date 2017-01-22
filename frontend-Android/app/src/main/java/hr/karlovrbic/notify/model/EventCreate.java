package hr.karlovrbic.notify.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

import static android.R.attr.id;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventCreate implements Parcelable {
    public static final Creator<EventCreate> CREATOR = new Creator<EventCreate>() {
        @Override
        public EventCreate createFromParcel(Parcel source) {
            return new EventCreate(source);
        }

        @Override
        public EventCreate[] newArray(int size) {
            return new EventCreate[size];
        }
    };

    private static final String ATTRIBUTE_CREATOR = "creator";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_DATE = "date";
    private static final String ATTRIBUTE_DESCRIPTION = "description";

    @SerializedName(ATTRIBUTE_CREATOR)
    @Expose
    private User creator;
    @SerializedName(ATTRIBUTE_TITLE)
    @Expose
    private String title;
    @SerializedName(ATTRIBUTE_DATE)
    @Expose
    private Date date;
    @SerializedName(ATTRIBUTE_DESCRIPTION)
    @Expose
    private String description;

    public EventCreate(Long userId,
                        String title,
                        Date date,
                        String description) {
        this.creator = new User(userId);
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public EventCreate() {
    }

    protected EventCreate(Parcel in) {
        this.creator = in.readParcelable(User.class.getClassLoader());
        this.title = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.description = in.readString();
    }

    public User getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.creator, flags);
        dest.writeString(this.title);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeString(this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EventCreate that = (EventCreate) o;

        return new EqualsBuilder()
                .append(creator, that.creator)
                .append(title, that.title)
                .append(date, that.date)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creator)
                .append(title)
                .append(date)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EventCreate{" +
                "id=" + id +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    public static class User implements Parcelable {

        public static final Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel source) {
                return new User(source);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        private static final String ATTRIBUTE_ID = "id";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;

        private User(Long id) {
            this.id = id;
        }

        protected User(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
        }

        public User() {
        }

        public Long getId() {
            return id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return new EqualsBuilder()
                    .append(id, user.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "User{id=" + id + '}';
        }
    }
}

