package com.catolica.salesianoapp.ucv.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.util.SharedPrefManager;

/**
 * Created by Meycon Augusto on 09/01/2018.
 */

public class DialogSair  extends DialogFragment implements DialogInterface.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
        dialogo.setTitle("ATENÇÃO");
        dialogo.setMessage("Deseja mesmo sair?");
        dialogo.setPositiveButton("Sim",this);
        dialogo.setIcon(R.drawable.ic_error_outline_black_24dp);
        dialogo.setNegativeButton("Não",this);
        return dialogo.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==DialogInterface.BUTTON_POSITIVE){
            SharedPrefManager.getInstance(getActivity()).logout();
        }else if(which==DialogInterface.BUTTON_NEGATIVE){
        }
    }

}
