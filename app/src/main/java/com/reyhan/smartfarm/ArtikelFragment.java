package com.reyhan.smartfarm;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ArtikelFragment extends android.support.v4.app.Fragment {

    public ArtikelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel, container, false);

        final String [] listviewitems = new String[]{
                "Artikel 1","Artikel 2","Artikel 3","Artikel 4","Artikel 5",
                "Artikel 6","Artikel 7"
        };

        ListView listview;
        listview = (ListView) view.findViewById(R.id.listView);

        final ArrayAdapter<String> listviewadapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,listviewitems);

        listview.setAdapter(listviewadapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String templistview = listviewitems[i].toString();

                Intent intent = new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("Listviewclickvalue", templistview);
                startActivity(intent);
            }
        });

        return view;
    }

}
