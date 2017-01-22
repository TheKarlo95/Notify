package hr.karlovrbic.notify.features.users.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.UserListModule;
import hr.karlovrbic.notify.features.main.MainActivity;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.features.shared.view.BaseFragment;
import hr.karlovrbic.notify.features.users.UserListAdapter;
import hr.karlovrbic.notify.features.users.profile.UserProfileFragment;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserListFragment extends BaseFragment implements IUserList.View {

    private static final String KEY_USERS = "users";

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;

    @Inject
    IUserList.Presenter presenter;

    private UserListAdapter adapter;
    private Unbinder unbinder;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        initUserList();
        if (savedInstanceState != null) {
            ArrayList<User> users = savedInstanceState.getParcelableArrayList(KEY_USERS);
            showUsers(users);
        } else {
            presenter.getUserList();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_USERS, adapter.getUsers());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        unbinder.unbind();
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void addUsers(ArrayList<User> users) {
        adapter.addAll(users);
    }

    @Override
    public void clear() {
        adapter.setUsers(null);
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new UserListModule(this)).inject(this);
    }

    private void initUserList() {
        Context context = getContext();
        adapter = new UserListAdapter(null, context, new ItemClickListener<User>() {
            @Override
            public void onClick(User user, int adapterPosition) {
                ((MainActivity) getActivity()).addFragment(UserProfileFragment.newInstance(user.getId()));
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvUserList.setLayoutManager(layoutManager);
        rvUserList.addItemDecoration(new DividerItemDecoration(rvUserList.getContext(), layoutManager.getOrientation()));
        rvUserList.setAdapter(adapter);
    }
}
