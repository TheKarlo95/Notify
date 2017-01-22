package hr.karlovrbic.notify.features.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.MainModule;
import hr.karlovrbic.notify.features.events.followed.FollowedEventListFragment;
import hr.karlovrbic.notify.features.events.list.EventListFragment;
import hr.karlovrbic.notify.features.events.myevents.MyEventsFragment;
import hr.karlovrbic.notify.features.shared.view.BaseView;
import hr.karlovrbic.notify.features.users.list.UserListFragment;

public class MainActivity extends BaseView implements IMain.View {

    public static final String KEY_USER_ID = "user_id";
    private static final String KEY_FIRST_FRAGMENT_ADDED = "first_fragment_added";
    private static final String KEY_LAST_ITEM_ID = "last_item_id";

    @BindView(R.id.bnv_menu)
    BottomNavigationView bnvMenu;

    private long userId;
    private boolean firstFragmentAdded;
    private int lastItemId;

    @NonNull
    public static Intent buildIntent(@NonNull Context context, Long userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_users:
                        lastItemId = 0;
                        replaceFragment(UserListFragment.newInstance());
                        break;
                    case R.id.action_events:
                        lastItemId = 1;
                        replaceFragment(EventListFragment.newInstance(userId));
                        break;
                    case R.id.action_followed:
                        lastItemId = 2;
                        replaceFragment(FollowedEventListFragment.newInstance(userId));
                        break;
                    case R.id.action_my_events:
                        lastItemId = 3;
                        replaceFragment(MyEventsFragment.newInstance(userId));
                        break;
                }
                return true;
            }
        });

        if (savedInstanceState != null) {
            firstFragmentAdded = savedInstanceState.getBoolean(KEY_FIRST_FRAGMENT_ADDED);
            lastItemId = savedInstanceState.getInt(KEY_LAST_ITEM_ID, R.id.action_users);
            userId = savedInstanceState.getLong(KEY_USER_ID);

            bnvMenu.getMenu().getItem(lastItemId).setChecked(true);
        } else {
            userId = getIntent().getExtras().getLong(KEY_USER_ID);
        }

        if (!firstFragmentAdded) {
            firstFragmentAdded = true;
            replaceFragment(UserListFragment.newInstance());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_USER_ID, userId);
        outState.putBoolean(KEY_FIRST_FRAGMENT_ADDED, firstFragmentAdded);
        outState.putInt(KEY_LAST_ITEM_ID, lastItemId);
    }

    @Override
    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fl_fragment, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment, fragment);
        transaction.commit();
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new MainModule(this)).inject(this);
    }
}
