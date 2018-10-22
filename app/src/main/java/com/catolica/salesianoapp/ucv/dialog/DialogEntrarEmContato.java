package com.catolica.salesianoapp.ucv.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;

/**
 * Created by Meycon Augusto on 09/01/2018.
 */

public class DialogEntrarEmContato extends DialogFragment implements DialogInterface.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
        dialogo.setTitle("Esqueceu a senha?");
        dialogo.setMessage("Favor procurar a secretaria ou entrar em contato com o telefone: (027) 3331-8500");
        dialogo.setPositiveButton("OK",this);
        dialogo.setIcon(R.drawable.ic_vpn_key_black_24dp);
        return dialogo.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

}
