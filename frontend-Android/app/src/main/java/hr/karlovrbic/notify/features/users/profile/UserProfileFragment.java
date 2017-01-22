package hr.karlovrbic.notify.features.users.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.UserProfileModule;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.features.shared.view.BaseFragment;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserProfileFragment extends BaseFragment implements IUserProfile.View {

    private static final String ARGUMENT_USER_ID = "user_id";
    private static final String KEY_USER = "user";

    @BindView(R.id.civ_profile_image)
    CircleImageView civProfileImage;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_full_name)
    TextView tvFullName;
    @BindView(R.id.rv_events)
    RecyclerView rvEvents;

    @Inject
    IUserProfile.Presenter presenter;

    private User user;

    private ProfileEventListAdapter adapter;
    private Unbinder unbinder;

    public static UserProfileFragment newInstance(long id) {
        UserProfileFragment fragment = new UserProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ARGUMENT_USER_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        unbinder = ButterKnife.bind(this, view);

        initEventList();

        if (savedInstanceState == null) {
            Bundle args = getArguments();
            presenter.getUser(args.getLong(ARGUMENT_USER_ID));
        } else {
            user = savedInstanceState.getParcelable(KEY_USER);
            showUser(user);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_USER, user);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        presenter.cancel();
    }

    @Override
    public void showUser(User user) {
        this.user = user;
        if (user != null && tvUsername != null && tvFullName != null && rvEvents != null) {
            tvUsername.setText(user.getUsername());
            tvFullName.setText(user.getName() + " " + user.getSurname());
            adapter.addAll(user.getEvents());
        }
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new UserProfileModule(this)).inject(this);
    }

    private void initEventList() {
        Context context = getContext();
        adapter = new ProfileEventListAdapter(null, context, new ItemClickListener<User.Event>() {
            @Override
            public void onClick(User.Event event, int adapterPosition) {
                // go to event
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.addItemDecoration(new DividerItemDecoration(rvEvents.getContext(), layoutManager.getOrientation()));
        rvEvents.setAdapter(adapter);
    }
}
