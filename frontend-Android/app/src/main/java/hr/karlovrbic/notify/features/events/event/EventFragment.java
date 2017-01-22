package hr.karlovrbic.notify.features.events.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.EventModule;
import hr.karlovrbic.notify.features.events.event.dialog.CreateMessageDialog;
import hr.karlovrbic.notify.features.events.event.dialog.CreateMessageDialogCallback;
import hr.karlovrbic.notify.features.shared.ItemClickListener;
import hr.karlovrbic.notify.features.shared.view.BaseFragment;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.Message;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventFragment extends BaseFragment implements IEvent.View, CreateMessageDialogCallback {

    public static final String CREATE_MESSAGE_TAG = "create_message_dialog";
    private static final String ARGUMENT_EVENT_ID = "event_id";
    private static final String KEY_EVENT = "event";

    @BindView(R.id.civ_event_image)
    CircleImageView civEventImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.rv_events)
    RecyclerView rvEvents;

    @Inject
    IEvent.Presenter presenter;

    private Event event;

    private MessagesListAdapter adapter;
    private Unbinder unbinder;

    public static EventFragment newInstance(long id) {
        EventFragment fragment = new EventFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ARGUMENT_EVENT_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        unbinder = ButterKnife.bind(this, view);

        initEventList(null, null);

        if (savedInstanceState == null) {
            Bundle args = getArguments();
            presenter.getEvent(args.getLong(ARGUMENT_EVENT_ID));
        } else {
            event = savedInstanceState.getParcelable(KEY_EVENT);
            showEvent(event);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_EVENT, event);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        presenter.cancel();
    }

    @Override
    public void showEvent(Event event) {
        this.event = event;
        if (event != null) {
            tvTitle.setText(event.getTitle());
            tvDate.setText(toString(event.getDate()));
            tvDescription.setText(event.getDescription());

            adapter.setMessages(event.getMessages());
            adapter.setEventId(event.getId());
        }
    }

    @Override
    public void addMessage(Message value) {
        adapter.add(new Event.Message(value.getId(), value.getContent()));
    }

    @Override
    public void callback(String message) {
        presenter.createMessage(message, adapter.getEventId());
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new EventModule(this)).inject(this);
    }

    @OnClick()
    public void addMessageClicked() {
        CreateMessageDialog dialog = new CreateMessageDialog();

        dialog.setTargetFragment(this, 0);
        dialog.show(getActivity().getSupportFragmentManager(), CREATE_MESSAGE_TAG);
    }

    private void initEventList(ArrayList<Event.Message> messages, Long eventId) {
        Context context = getContext();
        adapter = new MessagesListAdapter(messages, eventId, context, new ItemClickListener<Event.Message>() {
            @Override
            public void onClick(Event.Message message, int adapterPosition) {
                // go to message
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.addItemDecoration(new DividerItemDecoration(rvEvents.getContext(), layoutManager.getOrientation()));
        rvEvents.setAdapter(adapter);
    }

    @SuppressLint("SimpleDateFormat")
    private static String toString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = null;

        if (date != null) {
            strDate = dateFormat.format(date);
        }

        return strDate;
    }
}
