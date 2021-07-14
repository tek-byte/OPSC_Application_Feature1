package com.example.fishquery;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnFish;
    private RecyclerView recyclerView;
    private FishesAdapter adapter;
    private List<Fishes> fishesList;
    private EditText txt_Common;


    DatabaseReference dbFishes;
    DatabaseReference dbFishesA;
    ArrayList<String> myArraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFish = findViewById(R.id.btnFind);
        AutoCompleteTextView AutoCompleteText;
        AutoCompleteText = (AutoCompleteTextView)findViewById(R.id.AutoCompleteText);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fishesList = new ArrayList<>();
        adapter = new FishesAdapter(this, fishesList);
        recyclerView.setAdapter(adapter);

        String[] fish = {
                "Bonefish, Smallscale Bonefish",
                "Black seacatfish, Black seabarbel",
                "St Joseph, Elephant fish, Cape elephant fish",
                "Blacktip Kingfish, Yellotail kingfish",
                "Giant Kingfish, Giant Trevally",
                "Brassy kingfish, Greenspot kingfish",
                "Bigeye kingfish, Bigeye trevally",
                "Garrick, Leervis",
                "Largemouth queenfish, Talang queenfish",
                "Greater amberjack, Greater yellowtail",
                "Giant yellowtail, Cape yellowtail, Geelstert",
                "Longfin amberjack, Longfin yellotail",
                "Southern pompano, African pompano",
                "Largespot pompano, Wave garrick, Wave trevally",
                "Copper shark, Bronze whaler",
                "Zambezi shark",
                "Blacktip shark",
                "Dusky shark, Ridgeback grey shark",
                "Tiger shark",
                "Blue shark",
                "Milk shark, Grey dogshark",
                "Bank steenbras",
                "Natal fingerfun, Natal banky",
                "Dorado, Dolphinfish, Mahimahi",
                "Blue stingray, Blou pylstert",
                "Diamond ray, Butterfly ray, Backwater butterfly ray",
                "Sharpnose stingray, Brown stingray, Skerpneus-pylstert",
                "Hineycomb stingray, Marbled stingray, Leopard stingray",
                "Galjoen, Damba",
                "Banded Galjoen",
                "Cave bass, Lampfish, Lantern fish",
                "Springer, Ladyfish, Tenpounder",
                "Snoek, Cape snoek, Barracouta",
                "Dusky rubberlip",
                "Lemmonfish",
                "Whitebarred rubberlip",
                "Spotted grunter, Spotty, Tiger, Knorhaan, Inkolokolo",
                "Grey grunter, Banded grunter",
                "Javelin grunter, Mof grunter, Spies knorder",
                "Piggy, Pinky, Varkie, Olive grunter",
                "Cowshark, Broadnosed sevengill",
                "Black marlin",
                "Sailfish, Indo-Pacific sailfish",
                "Stiped marlin",
                "Blue marine",
                "Stonebream, Stinker, Stinkvis",
                "Great white shark, White shark, Witdoodshaai",
                "Shortfin mako, Mako shark",
                "Yellowfin emperor, Yellowtail emperor, Scavenger",
                "Blue emperor, Spangled emperor, Mata-hari",
                "Green jobfish, Kaakap",
                "River snapper, Rock salmon, River roman",
                "Speckled snapper, Blubberlip snapper",
                "Blood snapper, Humperhead snapper",
                "Emperor snapper, Emperor red snapper",
                "Protea bream, Yellowtail fusilier",
                "Rosy jobfish, Crimson jobfish",
                "Oxeye tarpod, Indo-pacific tarpon",
                "Shallow-water hake, Shallow-water Cape hake",
                "Harder, Southern mullet, Bokkom",
                "Stiped mullet",
                "Flathead mullet, Grey mullet, Striped muller",
                "Eagle ray, Arendrog",
                "Spotted ragged-tooth shark, Spikkel-skeurtandhaai",
                "Cape knifejaw, Cuckoo bass, Beaked galjoen",
                "Natal knifejaw, Cuckoo bass",
                "Bartail flathead, River gurnard, Sand gurnard",
                "Elf, Shad, Bluefish, Tailor",
                "Prodigal son, Cobia, Kobia",
                "Lesser guitarfish, Lesser sandshark",
                "Giant guitarfish, Giant sandshark",
                "Silver kop, Kabeljou, Mile meagre",
                "Dusky kob, Daga salmon, Kabeljou, Kob, Giant Kob",
                "Squaretail Kob, Half kob",
                "Geelbek, Cape salmon, Silver jewfish",
                "Small Kob, Mini-Kob, Nondi, African croaker",
                "Snapper kob, Longtooth kob",
                "Belman, Baardman, tasselfish",
                "Wahoo",
                "Eastern little tuna, Kawakawa, Mackerel tuna",
                "Skipjack tuna, Bonito, Katunkel, Lesser tunny",
                "Chub mackerel, Common mackerel",
                "King mackerel, Couta, Cuda",
                "Queen mackerel, Natal snoek, Serra, Kanadi kingfish",
                "Albacore, Longfin tuna, Longfin tunny, Albakoor",
                "Yellowfin tuna, Geelvin tuna",
                "Bigeye tuna, Bigeye tunny, Grootoog tuna",
                "Pyjama shark, Striped catshark",
                "Leopard catshark",
                "White-edged rockcod, Captain fine",
                "Catface rockcod, Brown-spotted rockcod",
                "Brindle bass, Giant grouper",
                "Malabar rockcod",
                "Yellowbelly rockcod, Dusky grouper",
                "Halfmoon rockcod, Witch's pric",
                "Potato bass, Potato grouper, Aartappel-baars",
                "Riverbream, Perch, Slim, Jannie",
                "King soldierbream",
                "Carpenter, Silverfish, Kaapenaar",
                "Fransmadam, Karel grootoog",
                "Santer, Soldier, Santer seabream",
                "Englishman",
                "Dagaraad, Daggerhead seabream",
                "Red stumpnose, Miss Lucy, Rooistompneus",
                "Roman, Red Roman",
                "False englishman, False red stumpnose",
                "Slinger",
                "Black musselcracker, Black steenbras, Poenskop",
                "Blacktail, Dassie, Kolstert, Ntimla",
                "Zebra, Wildeperd, Bontrok",
                "Janbruin, John Brown, Blue-eye JB",
                "Westcoast steenbras",
                "White steenbras, Pignose grunter, Witsteenbras",
                "Sand steenbras",
                "Blue hottentot",
                "Hottentot, Hottentot seabream",
                "Bronze bream, Copper bream, Bluefish",
                "Sand soldier, Tjor-tjor",
                "Red steenbras, Copper steenbras",
                "Trawl soldier, Blueskin",
                "Scotsman",
                "Seventy-four, Seventy-four seabream",
                "Dane, Deen",
                "Panga, Dikbekkie",
                "White stumpnose, Witstompneus",
                "Cape stumpnose, Silver bream",
                "Natal stumpnose, Yellowfin bream",
                "Strepie, Karranteen, Sasa",
                "White musselcracker, Brusher",
                "Steentjie",
                "Great Barracuda",
                "Pickhandle barracuda, Sea pike",
                "Scalloped hammerhead",
                "Smooth hammerhead",
                "Soupfin shark, Tope, Vaalhaai",
                "Blackspotted smoothhound",
                "Spotted gullyshark, Gespikkled sloothaai",
                "Swordfish, Broadbill, Swaardvis"};


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, fish);
        AutoCompleteText.setAdapter(arrayAdapter);

        btnFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ComName = AutoCompleteText.getText().toString();

                dbFishes = FirebaseDatabase.getInstance().getReference("Fishes");
                Query queryName = FirebaseDatabase.getInstance().getReference("Fishes")
                        .orderByChild("CommonName")
                        .equalTo(ComName);

                queryName.addListenerForSingleValueEvent(valueEventListener);

            }
        });

        /*

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,myArraylist);
        query2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                myArraylist.add(value);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            fishesList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Fishes fishes = snapshot.getValue(Fishes.class);
                    fishesList.add(fishes);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}