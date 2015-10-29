package com.aleksandrp.superstopwatch.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.db.functions_db.DBImpl;

/**
 * Created by Aleksandr on 30.10.2015.
 */
public class CastomDialog extends AlertDialog.Builder {

    private Context context;
    private View view;
    private String title;
    private DBImpl db;

    public CastomDialog(Context context, View view, String title) {
        super(context);
        this.context = context;
        this.view = view;
        this.title = title;
        db = new DBImpl(context);
        init();

    }


    private void init() {
        setTitle(title);
        setPositiveButton(context.getString(R.string.positive),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (view == null) {
                            db.removeAllTime();
                            Toast.makeText(context, R.string.all_times_deleted, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, view.getBottom() + "", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        setNegativeButton(context.getResources().getString(R.string.negative),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        setCancelable(true);
    }


}
