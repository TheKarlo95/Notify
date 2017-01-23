package hr.karlovrbic.notify.features.events.myevents;

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
import butterknife.OnClick;
import butterknife.Unbinder;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.MyEventsModule;
import hr.karlovrbic.notify.features.events.event.EventFragment;
import hr.karlovrbic.notify.features.events.myevents.dialog.CreateEventDialog;
import hr.karlovrbic.notify.features.events.myevents.dialog.CreateEventDialogCallback;
import hr.karlovrbic.notify.features.events.shared.EventListAdapter;
import hr.karlovrbic.notify.features.main.MainActivity;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.features.shared.view.BaseFragment;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.EventCreate;
import hr.karlovrbic.notify.utils.SharedPrefsUtils;

public class MyEventsFragment extends BaseFragment implements IMyEvents.View, CreateEventDialogCallback {

    public static final String CREATE_EVENT_TAG = "create_event_dialog";
    private static final String KEY_EVENTS = "events";

    @BindView(R.id.rv_my_events)
    RecyclerView rvEventList;

    @Inject
    IMyEvents.Presenter presenter;

    private EventListAdapter adapter;
    private Unbinder unbinder;

    public static MyEventsFragment newInstance() {
        return new MyEventsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_events, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (savedInstanceState == null) {
            Long userId = SharedPrefsUtils.getUserId(getContext());

            initEventList(null, userId);

            presenter.getMyEventList(userId);
        } else {
            ArrayList<Event> events = savedInstanceState.getParcelableArrayList(KEY_EVENTS);
            Long userId = SharedPrefsUtils.getUserId(getContext());

            initEventList(events, userId);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_EVENTS, adapter.getEvents());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        unbinder.unbind();
    }

    @Override
    public void showEvents(ArrayList<Event> events) {
        adapter.setEvents(events);
    }

    @Override
    public void addEvent(Event event) {
        adapter.add(event);
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void callback(EventCreate eventCreate) {
        presenter.createEvent(eventCreate);
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new MyEventsModule(this)).inject(this);
    }

    @OnClick(R.id.btn_add_event)
    public void addEventClicked() {
        CreateEventDialog dialog = new CreateEventDialog();

        dialog.setTargetFragment(this, 0);
        dialog.show(getActivity().getSupportFragmentManager(), CREATE_EVENT_TAG);
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
                null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvEventList.setLayoutManager(layoutManager);
        rvEventList.addItemDecoration(new DividerItemDecoration(rvEventList.getContext(), layoutManager.getOrientation()));
        rvEventList.setAdapter(adapter);
    }

    private void showEvent(Long eventId) {
        ((MainActivity) getActivity()).addFragment(EventFragment.newInstance(eventId));
    }
}
