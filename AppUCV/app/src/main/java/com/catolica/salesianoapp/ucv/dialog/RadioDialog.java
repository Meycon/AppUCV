package com.catolica.salesianoapp.ucv.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;

/**
 * Created by Meycon Augusto on 09/01/2018.
 */

public class RadioDialog extends DialogFragment implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Alterar Contexto")
                .setSingleChoiceItems(R.array.contexto,-1,this)
                .create();
    }

    @Override
    public void onClick(final DialogInterface dialog, int item) {
        String[] contexto = getActivity().getResources().getStringArray(R.array.contexto);
        String semestre = contexto[item];
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 500);

    }
}
