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
                "Cara Membuat Tempe dari Biji Karet",
                "Pemerintah Dukung Upaya Pengembangan Sawit Jadi Bensin dan Elpiji",
                "Kulit Jeruk Purut Bisa Jadi Sumber Bahan Bakar",
                "Cara Menentukan Jenis Lampu yang Sesuai untuk Berkebun Hidroponik"
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

                ,"Siapkan biji karet sebanyak 1 kilogram. Biji karet dicuci bersih untuk menghilangkan kotoran pada kulit biji. Selanjutnya, biji karet dibuang kulitnya dengan cara memecahkannya. Setelah terpisah dari kulitnya, daging biji direndam selama 1 × 24 jam.\n" +
                "\n" +
                "Setelah direndam, kemudian rebus selama 1 jam. Tiriskan dan biarkan hingga dingin, setelah dingin air rebusan di buang lalu buang bakal daun yang terdapat di dalam biji. Setelah itu, rendam kembali biji karet selama 1×24 jam. Biji karet lalu dicuci dan dikukus ± 30 menit. Setelah dikukus selama 30 menit, air yang tersisa di dalam panci dibuang, kemudian biji karet dipindahkan ke tampah dan diratakan tipis-tipis. Selanjutnya, biji karet dibiarkan dingin sampai permukaan keping karet kering dan airnya menetes habis.\n" +
                "\n" +
                "Setelah dingin, taburkan ragi tempe (Rhizopus oryzae) sebanyak ± 2 gram (0,2 persen dari bobot biji karet) sambil diaduk-aduk sampai rata. Penambahan ragi bertujuan mempercepat/merangsang pertumbuhan jamur. Tahap peragian (fermentasi) adalah tahap penentu keberhasilan dalam membuat tempe.\n" +
                "\n" +
                "Selanjutnya, tempe dikemas sesuai dengan selera, dapat menggunakan plastik ataupun daun pisang. Plastik atau daun pisang yang telah berisi biji karet dilubangi dengan menggunakan jarum yang terbuat dari kayu ukuran kecil kira-kira 8—10 lubang untuk setiap sisi atas dan sisi bawah. Tempe disimpan di tempat yang tidak tertutup (pada suhu kamar) untuk menghindari pembusukan pada tempe karena suhu yang terlalu panas, usahakan di tempat yang terjadi sirkulasi udara. Tempe didiamkan kurang lebih selama 2 × 24 jam. Setelah itu, tempe siap diolah menjadi makanan yang lezat dan bergizi tinggi."

                ,"“Indonesia yang pertama mengembangkan sawit untuk bensin melalui co-processing. Minyak sawit dicampurkan ke kilang dengan proses cracking, menggunakan katalis Merah Putih, yang juga merupakan produksi anak bangsa, dan akan menghasilkan bensin dan LPG di akhir proses,” papar Dadan dalam keterangan tertulis, Sabtu (13/4).\n" +
                "\n" +
                "Ia mengungkapkan, pemanfaatan sawit untuk bensin ini juga telah dilakukan di beberapa negara seperti di Amerika, Italia, dan UEA. Akan tetapi, yang dikembangkan di negara-negara tersebut adalah membuat pabrik baru yang dapat mengolah langsung sawit dengan bensin sebagai salah satu produknya.\n" +
                "\n" +
                "“Yang mereka kembangkan bukan co-processing, tapi stand-alone dari sawit menghasilkan bensin. Untuk co-processing ini kita yang pertama,” ungkapnya.\n" +
                "\n" +
                "Dadan menjelaskan, kelebihan lain dari co-processing, yaitu masih dapat menggunakan kilang yang ada sehingga lebih hemat dalam proses produksinya. “Yang digunakan adalah kilang eksisting, hanya ditambahkan proses di tengahnya untuk menghasilkan bensin dan LPG,” katanya.\n" +
                "\n" +
                "Terkait harga, Dadan mengungkapkan kalau harga bensin dari sawit ini nantinya masih akan tergantung dari harga bahan baku sawitnya. “Ada mekanisme yang saling menguntungkan pastinya, bisa melalui insentif atau bentuk lain, karena kita tahu hingga saat ini di lapangan, kalau harga minyak goreng selalu lebih mahal dari bahan bakar,” katanya.\n" +
                "\n" +
                "Selain Dadan Kusdiana, hadir pula sebagai pembicara pada lokakarya kerja sama Balitbang ESDM dan IPB kali ini Pakar Bioenergi IPB Erliza Hambali dan Armansyah Tambunan, juga Direktur Penyaluran Dana BPDPKS Edi Wibowo, dan Ketua Asosiasi Produsen Biofuel Indonesia (Aprobi) Paulus Tjakrawan."

                ,"Tanpa kita sadari ternyata kulit jeruk purut inilah yang merupakan bagian paling terpenting dari semua dari jeruk purut yang ada. Di balik bentuk kulit jeruk purut yang terlihat sedikit agak aneh tersebut, ternyata memiliki kandungan minyak atsiri yang begitu banyak.\n" +
                "\n" +
                "Jika kantong yang ada pada kulit jeruk tersebut pecah, minyak yang tadinya ada di dalam kulit tersebut akan menguap menjadi gas. Uapan gas tersebut akan mudah sekali terbakar apabila didekatkan percikan api. Oleh karena itu, kulit jeruk purut ini tidak boleh dibuang begitu saja. \n" +
                "Kulit jeruk purut mengandung banyak manfaat untuk semua orang. Salah satunya dapat dimanfaatkan ketika harga minyak mulai melonjak tinggi. Jadi, jika kita menggunakan jeruk purut dalam kehidupan sehari-hari, dianjurkan untuk mengumpulkan kulitnya.\n" +
                "\n" +
                "Jika kita menggunakan kulit jeruk ini sebagai pengganti bahan bakar alternatif, akan mengurangi biaya pengeluaran di saat harga minyak melonjak tinggi. Untuk penggunaannya pun tidak terlalu sulit, hanya dengan membakar kulit jeruk purut tersebut, api akan mudah keluar dan membakar permukaan kulit.\n" +
                "\n" +
                "Selain itu, kita dapat mengurangi limbah kulit jeruk purut yang jika dibiarkan akan menjadi busuk. Daripada kulit jeruk purut terbuang sia-sia, kita dapat memanfaatkannya sebagai bahan bakar minyak yang tentunya akan membuat pengeluaran kita mengenai bahan bakar akan berkurang.\n" +
                "\n" +
                "Bukan hanya itu saja, pemakaian jeruk purut sebagai bahan bakar alternatif ini juga akan berguna untuk mengurangi ketergantungan terhadap bahan bakar minyak. Dengan demikian, Indonesia tidak akan mengalami kerugian yang besar, akibat harga minyak bumi yang semakin meningkat."

                ,"Tanpa kita sadari ternyata kulit jeruk purut inilah yang merupakan bagian paling terpenting dari semua dari jeruk purut yang ada. Di balik bentuk kulit jeruk purut yang terlihat sedikit agak aneh tersebut, ternyata memiliki kandungan minyak atsiri yang begitu banyak.\n" +
                "\n" +
                "Jika kantong yang ada pada kulit jeruk tersebut pecah, minyak yang tadinya ada di dalam kulit tersebut akan menguap menjadi gas. Uapan gas tersebut akan mudah sekali terbakar apabila didekatkan percikan api. Oleh karena itu, kulit jeruk purut ini tidak boleh dibuang begitu saja. \n" +
                "Kulit jeruk purut mengandung banyak manfaat untuk semua orang. Salah satunya dapat dimanfaatkan ketika harga minyak mulai melonjak tinggi. Jadi, jika kita menggunakan jeruk purut dalam kehidupan sehari-hari, dianjurkan untuk mengumpulkan kulitnya.\n" +
                "\n" +
                "Jika kita menggunakan kulit jeruk ini sebagai pengganti bahan bakar alternatif, akan mengurangi biaya pengeluaran di saat harga minyak melonjak tinggi. Untuk penggunaannya pun tidak terlalu sulit, hanya dengan membakar kulit jeruk purut tersebut, api akan mudah keluar dan membakar permukaan kulit.\n" +
                "\n" +
                "Selain itu, kita dapat mengurangi limbah kulit jeruk purut yang jika dibiarkan akan menjadi busuk. Daripada kulit jeruk purut terbuang sia-sia, kita dapat memanfaatkannya sebagai bahan bakar minyak yang tentunya akan membuat pengeluaran kita mengenai bahan bakar akan berkurang.\n" +
                "\n" +
                "Bukan hanya itu saja, pemakaian jeruk purut sebagai bahan bakar alternatif ini juga akan berguna untuk mengurangi ketergantungan terhadap bahan bakar minyak. Dengan demikian, Indonesia tidak akan mengalami kerugian yang besar, akibat harga minyak bumi yang semakin meningkat."
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

