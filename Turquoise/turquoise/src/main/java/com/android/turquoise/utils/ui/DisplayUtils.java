package com.android.turquoise.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.turquoise.R;


/**
 * Created by jikoobaruah on 26/05/17.
 */

public class DisplayUtils {

    public static void showUpdateDialog(final Context context, boolean isForced, DialogInterface.OnClickListener dismissClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                NavigationUtils.navigateToPlaystore(context);
            }
        });
        builder.setNegativeButton("No, thanks", dismissClickListener);

        if (isForced) {
            builder.setTitle("Update required");
        } else {
            builder.setTitle("Update available");

        }

        builder.setMessage("Please update your app");

        builder.create().show();

    }



//    public static void showYesNoDialog(Context context, String text, final View.OnClickListener yesClickListener, final View.OnClickListener noClickListener) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        View dialogView = LayoutInflater.from(context).inflate(R.layout.popup_yes_no, null, false);
//        dialogBuilder.setView(dialogView);
//
//        TextView textView = (TextView) dialogView.findViewById(R.id.tv_title);
//        ImageButton doneButton = (ImageButton) dialogView.findViewById(R.id.btn_done);
//        ImageButton cancelButton = (ImageButton) dialogView.findViewById(R.id.btn_cancel);
//        textView.setText(text);
//
//        final AlertDialog alertDialog = dialogBuilder.create();
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                if (noClickListener != null)
//                    noClickListener.onClick(v);
//            }
//        });
//
//        doneButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                if (yesClickListener != null)
//                    yesClickListener.onClick(v);
//            }
//        });
//
//
//        alertDialog.show();
//
//    }

}
