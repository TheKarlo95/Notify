package hr.karlovrbic.notify.features.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
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

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private ArrayList<User> users;

    private ItemClickListener<User> itemClickListener;

    private Context context;

    public UserListAdapter(ArrayList<User> users, Context context, ItemClickListener<User> itemClickListener) {
        this.users = users;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public UserListAdapter(Collection<User> users, Context context, ItemClickListener<User> itemClickListener) {
        this(new ArrayList<>(users), context, itemClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.pokemonNameTv.setText(users.get(position).getUsername());
//        Glide.with(context).load((String) null) // TO DO add user profile image feature
//                .error(R.drawable.ic_person_placeholder)
//                .placeholder(R.drawable.ic_person_placeholder)
//                .into(holder.civProfileImage);

    }

    @Override
    public int getItemCount() {
        if (users == null || users.isEmpty()) {
            return 0;
        } else {
            return users.size();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void addAll(List<User> userList) {
        if (userList != null && !userList.isEmpty()) {
            if (users == null) {
                users = new ArrayList<>(userList.size());
            }
            users.addAll(userList);
            notifyDataSetChanged();
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_profile_image)
        CircleImageView civProfileImage;
        @BindView(R.id.tv_username)
        TextView pokemonNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (itemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        itemClickListener.onClick(users.get(position), position);
                    }
                });
            }
        }
    }

}
