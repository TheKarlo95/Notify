package hr.karlovrbic.notify.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class Event implements Parcelable {

    public static final Creator<Event> CREATOR = new Creator<Event>() {
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
    private static final String ATTRIBUTE_CREATOR = "creator";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_DATE = "date";
    private static final String ATTRIBUTE_DESCRIPTION = "description";
    private static final String ATTRIBUTE_CREATED_AT = "created_at";
    private static final String ATTRIBUTE_PICTURE = "picture_link";
    private static final String ATTRIBUTE_SUBSCRIBERS = "subscribers";
    private static final String ATTRIBUTE_MESSAGES = "messages";

    @SerializedName(ATTRIBUTE_ID)
    @Expose
    private Long id;
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
    @SerializedName(ATTRIBUTE_CREATED_AT)
    @Expose
    private Date createdAt;
    @SerializedName(ATTRIBUTE_PICTURE)
    @Expose
    private String pictureLink;
    @SerializedName(ATTRIBUTE_SUBSCRIBERS)
    @Expose
    private List<User> subscribers;
    @SerializedName(ATTRIBUTE_MESSAGES)
    @Expose
    private List<Message> messages;

    private Event(Long id,
                  User creator,
                  String title,
                  Date date,
                  String description,
                  Date createdAt,
                  String pictureLink,
                  List<User> subscribers,
                  List<Message> messages) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.date = date;
        this.description = description;
        this.createdAt = createdAt;
        this.pictureLink = pictureLink;
        this.subscribers = subscribers;
        this.messages = messages;
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.creator = in.readParcelable(User.class.getClassLoader());
        this.title = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.description = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.pictureLink = in.readString();
        this.subscribers = in.createTypedArrayList(User.CREATOR);
        this.messages = in.createTypedArrayList(Message.CREATOR);
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean isFollower(Long userId) {
        if (userId == null) {
            return false;
        } else {
            for (User user : subscribers) {
                if (user.getId().equals(userId)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isFollower(User user) {
        return isFollower(user.getId());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.creator, flags);
        dest.writeString(this.title);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeString(this.description);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.pictureLink);
        dest.writeTypedList(this.subscribers);
        dest.writeTypedList(this.messages);
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
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", pictureLink='" + pictureLink + '\'' +
                ", subscribers=" + subscribers +
                ", messages=" + messages +
                '}';
    }


    public static class User implements Parcelable {

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
        private static final String ATTRIBUTE_USERNAME = "username";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_USERNAME)
        @Expose
        private String username;

        private User(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        protected User(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.username = in.readString();
        }

        public User() {
            this(null, null);
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.username);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return new EqualsBuilder()
                    .append(id, user.id)
                    .append(username, user.username)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(username)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public static class Message implements Parcelable {

        public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
            @Override
            public Message createFromParcel(Parcel source) {
                return new Message(source);
            }

            @Override
            public Message[] newArray(int size) {
                return new Message[size];
            }
        };

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_CONTENT = "content";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_CONTENT)
        @Expose
        private String content;

        public Message(Long id, String content) {
            this.id = id;
            this.content = content;
        }

        public Message() {
        }

        protected Message(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.content = in.readString();
        }

        public Long getId() {
            return id;
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
            dest.writeValue(this.id);
            dest.writeString(this.content);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Message message = (Message) o;

            return new EqualsBuilder()
                    .append(id, message.id)
                    .append(content, message.content)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(content)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "Message{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}

