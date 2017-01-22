package hr.karlovrbic.notify.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public class Message implements Parcelable {

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
    private static final String ATTRIBUTE_EVENT = "event";
    private static final String ATTRIBUTE_COMMENTS = "comments";

    @SerializedName(ATTRIBUTE_ID)
    @Expose
    private Long id;
    @SerializedName(ATTRIBUTE_CONTENT)
    @Expose
    private String content;
    @SerializedName(ATTRIBUTE_EVENT)
    @Expose
    private Event event;
    @SerializedName(ATTRIBUTE_COMMENTS)
    @Expose
    private List<Comment> comments;

    public Message(Long id, String content, Event event, List<Comment> comments) {
        this.id = id;
        this.content = content;
        this.event = event;
        this.comments = comments;
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.content = in.readString();
        this.event = in.readParcelable(Event.class.getClassLoader());
        this.comments = in.createTypedArrayList(Comment.CREATOR);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Event getEvent() {
        return event;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.content);
        dest.writeParcelable(this.event, flags);
        dest.writeTypedList(this.comments);
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
        private static final String ATTRIBUTE_CREATOR = "creator";
        private static final String ATTRIBUTE_TITLE = "username";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_TITLE)
        @Expose
        private String title;
        @SerializedName(ATTRIBUTE_CREATOR)
        @Expose
        private User creator;

        public Event(Long id, String title, User creator) {
            this.id = id;
            this.title = title;
            this.creator = creator;
        }

        public Event() {
        }

        protected Event(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.title = in.readString();
            this.creator = in.readParcelable(User.class.getClassLoader());
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public User getCreator() {
            return creator;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.title);
            dest.writeParcelable(this.creator, flags);
        }
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

        public User(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public User() {
        }

        protected User(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.username = in.readString();
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
    }

    public static class Comment implements Parcelable {

        public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
            @Override
            public Comment createFromParcel(Parcel source) {
                return new Comment(source);
            }

            @Override
            public Comment[] newArray(int size) {
                return new Comment[size];
            }
        };

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_CREATOR = "creator";
        private static final String ATTRIBUTE_CONTENT = "content";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_CONTENT)
        @Expose
        private String content;
        @SerializedName(ATTRIBUTE_CREATOR)
        @Expose
        private User creator;

        public Comment(Long id, String content, User creator) {
            this.id = id;
            this.content = content;
            this.creator = creator;
        }

        public Comment() {
        }

        protected Comment(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.content = in.readString();
            this.creator = in.readParcelable(User.class.getClassLoader());
        }

        public Long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public User getCreator() {
            return creator;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.content);
            dest.writeParcelable(this.creator, flags);
        }
    }
}
