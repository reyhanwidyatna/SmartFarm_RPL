package com.reyhan.smartfarm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProfilAdapter extends ArrayAdapter<Profil> {
    private Activity context;
    private List<Profil>profilList;

    public ProfilAdapter(Activity context, List<Profil>profilList){
        super(context, R.layout.activity_profil,profilList);
        this.context = context;
        this.profilList = profilList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.list_view, null, true);

        TextView nama = listview.findViewById(R.id.profil_nama);
        TextView alamat = listview.findViewById(R.id.profil_alamat);
        TextView pendidikan = listview.findViewById(R.id.profil_pendidikan);

        Profil profil = profilList.get(position);
        nama.setText(profil.getNama());
        alamat.setText(profil.getAlamat());
        pendidikan.setText(profil.getPendidikan());

        return listview;
    }
}
