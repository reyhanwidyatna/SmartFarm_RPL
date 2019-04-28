package com.reyhan.smartfarm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtikelFragment extends android.support.v4.app.Fragment {

    public ArtikelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel, container, false);

        final String [] listviewitems = new String[]{
                "Mentan akan Kirim Dua Penyuluh Pertanian ke Thailand",
                "Jokowi ke Petani: Usai 17 April Kita Atur Penggilingan Punya Dryer",
                "Bantuan di Sektor Pertanian, Bagaimana Dampaknya buat Petani?",
                "Peduli pada Tenaga Kerja Pertanian","Artikel 5",
                "Artikel 6","Artikel 7"
        };
        final String [] listnewsitems = new String[]{
                "Jakarta - Menteri Pertanian Amran Sulaiman melakukan dialog bersama 12.000 penyuluh dan petani se-Indonesia di GOR Sudiang, Makassar. Pada pertemuan tersebut, Amran memanggil dua penyuluh asal Papua dan Aceh. Ia memberikan apresiasi dengan memberangkatkan kedua penyuluh asal Papua dan Aceh tersebut ke Thailand.\n" +
                "\n" +
                "\"Kami memberikan penghargaan ini kepada para penyuluh-penyuluh hebat se-Indonesia. Mereka pahlawan pangan. Karena merekalah, sektor pertanian dalam kurun 4,5 tahun di Pemerintahan Jokowi-JK sektor pertanian meningkat signifikan,\" kata Amran dalam keterangan tertulis, Rabu (10/4/2019).\n" +
                "\n" +
                "Amran menjelaskan, kedua penyuluh itu akan diberangkatkan ke Thailand untuk melakukan studi banding sembari mempelajari teknologi di negara itu untuk disebarluaskan di Indonesia.\n edua penyuluh yang diberangkatkan ke Thailand itu adalah Penyuluh Pertanian Dinas Pertanian dan Perkebunan Provinsi Aceh, Nurlisma dan " +
                "Penyuluh Pertanian asal Kabupaten Timika, Matias Nikah."

                ,"Sragen - Presiden Joko Widodo (Jokowi) berbicara tentang pentingnya pembaruan peralatan pertanian di era modern. Menurutnya, saat ini masih banyak petani yang menjemur padi di jalan usai panen.\n" +
                "\n" +
                "\"Sekarang sudah zaman modern. Dari saya kecil di desa sampai sekarang saya lihat kalau habis panen pasti dijereng di jalan-jalan. Nggih mboten?\" kata Jokowi saat bersilaturahmi dengan pelaku usaha pertanian Jawa Tengah di Sragen, Rabu (3/4/2019).\n" +
                "\n" +
                "Jokowi mengatakan bahwa setiap tempat penggilingan padi haruslah memiliki mesin pengering atau dryer. Hal tersebut dinilai membuat kualitas produksi meningkat." +
                "\"Dryer itu penting sekali. Bukan hanya untuk urusan padi, tapi juga jagung bermasalah, kualitas turun gara-gara apa? Hanya dijemur, tidak masuk ke dryer,\" ujarnya.\n" +
                "\n" +
                "Dia kemudian berencana mengumpulkan para pelaku usaha pertanian, seperti Perkumpulan Penggilingan Padi dan Pengusaha Beras Indonesia (Perpadi) di istana untuk membahas hal tersebut. Jokowi juga akan mengundang perbankan hingga Bulog.\n"

                ,"Artikel 3","Artikel 4","Artikel 5",
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
                String tempnewsview = listnewsitems[i].toString();

                Intent intent = new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("Listviewclickvalue", templistview);
                intent.putExtra("Listnewsclickvalue", tempnewsview);
                startActivity(intent);
            }
        });

        return view;
    }

}

