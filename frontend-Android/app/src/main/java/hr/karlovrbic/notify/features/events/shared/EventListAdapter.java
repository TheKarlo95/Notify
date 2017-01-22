package hr.karlovrbic.notify.features.events.shared;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.model.Event;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private ArrayList<Event> events;
    private Long userId;
    private ItemClickListener<Event> followButtonClickListener;
    private ItemClickListener<Event> clickListener;
    private Context context;

    public EventListAdapter(ArrayList<Event> events,
                            Long userId,
                            Context context,
                            ItemClickListener<Event> clickListener,
                            ItemClickListener<Event> followButtonClickListener) {
        this.events = events;
        this.userId = userId;
        this.context = context;
        this.clickListener = clickListener;
        this.followButtonClickListener = followButtonClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event, userId);
    }

    @Override
    public int getItemCount() {
        if (events == null || events.isEmpty()) {
            return 0;
        } else {
            return events.size();
        }
    }

    public Long getUserId() {
        return userId;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public void addAll(Collection<Event> eventList) {
        if (eventList != null && !eventList.isEmpty()) {
            if (events == null) {
                events = new ArrayList<>(eventList.size());
            }
            events.addAll(eventList);
            notifyDataSetChanged();
        }
    }

    public void add(Event event) {
        events.add(event);
        notifyItemInserted(events.size() - 1);
    }

    public void clear() {
        events = null;
        notifyDataSetChanged();
    }

    public void change(Event newEvent, int index) {
        events.remove(index);
        events.add(index, newEvent);
        notifyItemChanged(index);
    }

    public void remove(int index) {
        events.remove(index);
        notifyItemRemoved(index);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_event_image)
        CircleImageView civEventImage;
        @BindView(R.id.tv_title_creator)
        TextView tvTitleCreator;
        @BindView(R.id.btn_follow)
        Button btnFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (clickListener != null) {
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        clickListener.onClick(events.get(position), position);
                    }
                };

                civEventImage.setOnClickListener(listener);
                tvTitleCreator.setOnClickListener(listener);
            }
            if (followButtonClickListener != null) {
                btnFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        followButtonClickListener.onClick(events.get(position), position);
                    }
                });
            }
        }

        public void bind(Event event, Long userId) {
            tvTitleCreator.setText(event.getTitle() + " - " + event.getCreator().getUsername());

            setButtonVisibility(event, userId);
        }

        private void setButtonVisibility(Event event, Long userId) {
            if (userId.equals(event.getCreator().getId())) {
                btnFollow.setVisibility(View.GONE);
            } else {
                for (Event.User user : event.getSubscribers()) {
                    if (userId.equals(user.getId())) {
                        setAsUnfollowButton();
                        return;
                    }
                }
                setAsFollowButton();
            }
        }

        private void setAsFollowButton() {
            btnFollow.setVisibility(View.VISIBLE);
            btnFollow.setText("Follow");
            btnFollow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFollowButton));
        }

        private void setAsUnfollowButton() {
            btnFollow.setVisibility(View.VISIBLE);
            btnFollow.setText("Unfollow");
            btnFollow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorUnfollowButton));
        }
    }

}
