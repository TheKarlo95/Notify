package hr.karlovrbic.notify.features.users.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class ProfileEventListAdapter extends RecyclerView.Adapter<ProfileEventListAdapter.ViewHolder> {

    private List<User.Event> events;

    private ItemClickListener<User.Event> itemClickListener;

    private Context context;

    public ProfileEventListAdapter(List<User.Event> events, Context context, ItemClickListener<User.Event> itemClickListener) {
        this.events = events;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_profile_event, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User.Event event = events.get(position);
        holder.tvTitle.setText(event.getTitle());
//        Glide.with(context).load((String) null) // TO DO add user profile image feature
//                .error(R.drawable.ic_person_placeholder)
//                .placeholder(R.drawable.ic_person_placeholder)
//                .into(holder.civProfileImage);
    }

    @Override
    public int getItemCount() {
        if (events == null || events.isEmpty()) {
            return 0;
        } else {
            return events.size();
        }
    }

    public void addAll(List<User.Event> userList) {
        if (userList != null && !userList.isEmpty()) {
            if(events == null) {
                events = new ArrayList<>(userList.size());
            }
            events.addAll(userList);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        events = null;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_profile_image)
        CircleImageView civEventImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (itemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        itemClickListener.onClick(events.get(position), position);
                    }
                });
            }
        }
    }

}
