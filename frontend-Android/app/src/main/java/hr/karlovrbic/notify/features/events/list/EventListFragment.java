package hr.karlovrbic.notify.features.events.list;

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
import hr.karlovrbic.notify.dagger.modules.EventListModule;
import hr.karlovrbic.notify.features.events.event.EventFragment;
import hr.karlovrbic.notify.features.events.shared.EventListAdapter;
import hr.karlovrbic.notify.features.main.MainActivity;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.features.shared.view.BaseFragment;
import hr.karlovrbic.notify.model.Event;

import static hr.karlovrbic.notify.features.main.MainActivity.KEY_USER_ID;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventListFragment extends BaseFragment implements IEventList.View {

    private static final String ARGUMENT_USER_ID = "user_id";
    private static final String KEY_EVENTS = "events";

    @BindView(R.id.rv_event_list)
    RecyclerView rvEventList;

    @Inject
    IEventList.Presenter presenter;

    private EventListAdapter adapter;
    private Unbinder unbinder;
    private ArrayList<Event> events;

    public static EventListFragment newInstance(long userId) {
        EventListFragment fragment = new EventListFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ARGUMENT_USER_ID, userId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (savedInstanceState == null) {
            Bundle args = getArguments();
            Long userId = args.getLong(KEY_USER_ID);

            initEventList(null, userId);

            presenter.getEventList();
        } else {
            ArrayList<Event> events = savedInstanceState.getParcelableArrayList(KEY_EVENTS);
            Long userId = savedInstanceState.getLong(KEY_USER_ID);

            initEventList(events, userId);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_EVENTS, events);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        unbinder.unbind();
    }

    @Override
    public void showEvents(ArrayList<Event> events) {
        this.events = events;
        adapter.addAll(events);
    }

    @Override
    public void changeEvent(Event event, int adapterPosition) {
        adapter.change(event, adapterPosition);
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new EventListModule(this)).inject(this);
    }

    private void initEventList(ArrayList<Event> events, Long userId) {
        Context context = getContext();
        final Long finalUserId = userId;

        adapter = new EventListAdapter(events, userId, context,
                new ItemClickListener<Event>() {
                    @Override
                    public void onClick(Event event, int adapterPosition) {
                        showEvent(event.getId());
                    }
                },
                new ItemClickListener<Event>() {
                    @Override
                    public void onClick(Event event, int adapterPosition) {
                        presenter.followClicked(event, finalUserId, adapterPosition);
                    }
                }
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvEventList.setLayoutManager(layoutManager);
        rvEventList.addItemDecoration(new DividerItemDecoration(rvEventList.getContext(), layoutManager.getOrientation()));
        rvEventList.setAdapter(adapter);
    }

    private void showEvent(Long eventId) {
        ((MainActivity) getActivity()).addFragment(EventFragment.newInstance(eventId, adapter.getUserId()));
    }
}
