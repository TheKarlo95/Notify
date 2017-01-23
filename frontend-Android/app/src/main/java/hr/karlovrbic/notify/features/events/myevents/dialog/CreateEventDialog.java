package hr.karlovrbic.notify.features.events.myevents.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.features.events.myevents.MyEventsFragment;
import hr.karlovrbic.notify.model.EventCreate;
import hr.karlovrbic.notify.utils.DateUtils;
import hr.karlovrbic.notify.utils.SharedPrefsUtils;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public class CreateEventDialog extends DialogFragment {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.et_description)
    EditText etDescription;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_create_event, null);

        ButterKnife.bind(this, view);

        final Long userId = SharedPrefsUtils.getUserId(getContext());

        builder.setView(view)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String title = etTitle.getText().toString();
                        String date = etDate.getText().toString();
                        String description = etDescription.getText().toString();

                        EventCreate eventCreate = new EventCreate(userId, title, DateUtils.toDate(date), description);

                        ((MyEventsFragment) getTargetFragment()).callback(eventCreate);
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder.create();
    }
}
