package hr.karlovrbic.notify.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Karlo Vrbic on 21.01.17..
 */
public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
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
    private static final String ATTRIBUTE_TOKEN = "fcm_token";
    private static final String ATTRIBUTE_USERNAME = "username";
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_BIRTHDAY = "birthday";
    private static final String ATTRIBUTE_CREATED_AT = "created_at";
    private static final String ATTRIBUTE_PROFILE_PICTURE = "profile_picture_link";
    private static final String ATTRIBUTE_EVENTS = "events";

    @SerializedName(ATTRIBUTE_ID)
    @Expose
    private Long id;
    @SerializedName(ATTRIBUTE_TOKEN)
    @Expose
    private String token;
    @SerializedName(ATTRIBUTE_USERNAME)
    @Expose
    private String username;
    @SerializedName(ATTRIBUTE_EMAIL)
    @Expose
    private String email;
    @SerializedName(ATTRIBUTE_NAME)
    @Expose
    private String name;
    @SerializedName(ATTRIBUTE_SURNAME)
    @Expose
    private String surname;
    @SerializedName(ATTRIBUTE_BIRTHDAY)
    @Expose
    private Date birthDay;
    @SerializedName(ATTRIBUTE_CREATED_AT)
    @Expose
    private Date createdAt;
    @SerializedName(ATTRIBUTE_PROFILE_PICTURE)
    @Expose
    private String profilePictureLink;
    @SerializedName(ATTRIBUTE_EVENTS)
    @Expose
    private List<Event> events;

    public User(Long id,
                String token,
                String username,
                String email,
                String name,
                String surname,
                Date birthDay,
                Date createdAt,
                String profilePictureLink,
                List<Event> events) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.profilePictureLink = profilePictureLink;
        this.events = events;
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.username = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        long tmpBirthDay = in.readLong();
        this.birthDay = tmpBirthDay == -1 ? null : new Date(tmpBirthDay);
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.profilePictureLink = in.readString();
        this.events = in.createTypedArrayList(Event.CREATOR);
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeLong(this.birthDay != null ? this.birthDay.getTime() : -1);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.profilePictureLink);
        dest.writeTypedList(this.events);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, user.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                ", createdAt=" + createdAt +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                ", events=" + events +
                '}';
    }


    public static class Event implements Parcelable {

        public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
            @Override
            public Event createFromParcel(Parcel source) {
                return new Event(source);
            }

            @Override
            public Event[] newArray(int size) {
                return new Event[size];
            }
        };

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_TITLE = "title";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_TITLE)
        @Expose
        private String title;

        public Event(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public Event() {
        }

        protected Event(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.title = in.readString();
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.title);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Event event = (Event) o;

            return new org.apache.commons.lang3.builder.EqualsBuilder()
                    .append(id, event.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "Event{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}