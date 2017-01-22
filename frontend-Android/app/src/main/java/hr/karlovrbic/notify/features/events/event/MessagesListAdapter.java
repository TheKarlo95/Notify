package hr.karlovrbic.notify.features.events.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.model.Event;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.ViewHolder> {

    private ArrayList<Event.Message> messages;
    private Long eventId;
    private ItemClickListener<Event.Message> clickListener;
    private Context context;

    public MessagesListAdapter(ArrayList<Event.Message> messages,
                               Long eventId,
                               Context context,
                               ItemClickListener<Event.Message> clickListener) {
        this.messages = messages;
        this.eventId = eventId;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event.Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        if (messages == null || messages.isEmpty()) {
            return 0;
        } else {
            return messages.size();
        }
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public ArrayList<Event.Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Event.Message> messages) {
        this.messages = new ArrayList<>(messages);
        notifyDataSetChanged();
    }

    public void addAll(Collection<Event.Message> messageCollection) {
        if (messageCollection != null && !messageCollection.isEmpty()) {
            if (messages == null) {
                messages = new ArrayList<>(messageCollection.size());
            }
            messages.addAll(messageCollection);
            notifyDataSetChanged();
        }
    }

    public void add(Event.Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    public void clear() {
        messages = null;
        notifyDataSetChanged();
    }

    public void change(Event.Message newMessage, int index) {
        messages.remove(index);
        messages.add(index, newMessage);
        notifyItemChanged(index);
    }

    public void remove(int index) {
        messages.remove(index);
        notifyItemRemoved(index);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content)
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (clickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        clickListener.onClick(messages.get(position), position);
                    }
                });
            }
        }

        public void bind(Event.Message message) {
            tvContent.setText(message.getContent());
        }
    }

}
