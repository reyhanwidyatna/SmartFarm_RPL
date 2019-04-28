package com.reyhan.smartfarm;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilFragment extends android.support.v4.app.Fragment {

    TextView text1,text2,text3;

    public ProfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        TextView edit = view.findViewById(R.id.editprofils);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentedit = new Intent(getActivity(), EditprofilActivity.class);
                startActivity(intentedit);
            }
        });

        Button signout = (Button) view.findViewById(R.id.signoutButton);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Sign Out Berhasil", Toast.LENGTH_LONG).show();
                Intent intentlogout = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentlogout);
            }
        });

        return view;
    }

}
