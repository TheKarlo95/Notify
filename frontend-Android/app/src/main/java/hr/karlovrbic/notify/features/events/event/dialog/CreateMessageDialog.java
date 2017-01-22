package hr.karlovrbic.notify.features.events.event.dialog;

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
import hr.karlovrbic.notify.features.events.event.EventFragment;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public class CreateMessageDialog extends DialogFragment {

    @BindView(R.id.et_message)
    EditText etTitle;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_create_message, null);

        ButterKnife.bind(this, view);

        builder.setView(view)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String message = etTitle.getText().toString();

                        ((EventFragment) getTargetFragment()).callback(message);
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
