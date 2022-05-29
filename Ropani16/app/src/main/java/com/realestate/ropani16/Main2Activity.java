package com.realestate.ropani16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    private ArrayList<PropertyDesc> pList;
    private RecyclerView mRecyclerView;
    private Property mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ropani16");

        createPropertyList();
        buildRecyclerView();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Property(pList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Property.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("Image", pList.get(position).getbImageResource());
                intent.putExtra("Name", pList.get(position).getbPropertyName());
                intent.putExtra("Instruction", pList.get(position).getbPropertyDesc());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_log_out, menu);

        MenuItem searchItem = menu.findItem(R.id.rSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.log_out:
                showDialog();
                break;
            case R.id.profie:
                startActivity(new Intent(getApplicationContext(),profileActivity.class));
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void showDialog(){

        final AlertDialog.Builder alert =new AlertDialog.Builder(Main2Activity.this);
        View mView = getLayoutInflater().inflate(R.layout.logout_dialog,null);
        Button btn_cancel=(Button)mView.findViewById(R.id.cancel);
        Button btn_ok=(Button)mView.findViewById(R.id.okLogout);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast=Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void createPropertyList(){
        pList = new ArrayList<>();
        pList.add(new PropertyDesc(R.drawable.r1, "Ratnapark, Kathmandu \n" +
                "House- 1 crore 90 lakhs",getString(R.string.property1)));
        pList.add(new PropertyDesc(R.drawable.r2, "Kapan, Kathmandu \n" +
                "House- 2 crore 80 lakhs",getString(R.string.property2)));
        pList.add(new PropertyDesc(R.drawable.r3, "Sundhara, Kathmandu \n" +
                "House- 7 crore 20 lakhs",getString(R.string.property3)));
        pList.add(new PropertyDesc(R.drawable.r4, "Basundhara, Kathmandu \n" +
                "House- 5 crore 90 lakhs",getString(R.string.property4)));
        pList.add(new PropertyDesc(R.drawable.r5, "New Road, Kathmandu \n" +
                "House- 1 crore 10 lakhs",getString(R.string.property5)));
        pList.add(new PropertyDesc(R.drawable.r6, "Baneshwor, Kathmandu \n" +
                "House- 3 crore 75 lakhs",getString(R.string.property6)));
        pList.add(new PropertyDesc(R.drawable.r7, "Chipledhunga, Pokhara \n" +
                "House- 6 crore 90 lakhs",getString(R.string.property7)));
        pList.add(new PropertyDesc(R.drawable.r8, "Durbarmarg, Kathmandu \n" +
                "House- 2 crore 10 lakhs",getString(R.string.property8)));
        pList.add(new PropertyDesc(R.drawable.r9, "Nilopul, Kathmandu \n" +
                "House- 11 crore",getString(R.string.property9)));
        pList.add(new PropertyDesc(R.drawable.r10, "Setopul, Kathmandu \n" +
                "House- 9 crore 12 lakhs",getString(R.string.property10)));
        pList.add(new PropertyDesc(R.drawable.r11, "Ratopul, Kathmandu \n" +
                "Land- 12 lakh per anna",getString(R.string.property11)));
        pList.add(new PropertyDesc(R.drawable.r12, "Kalopul, Kathmandu \n" +
                "Land- 30 lakh per anna",getString(R.string.property12)));
        pList.add(new PropertyDesc(R.drawable.r13, "Britamod, Jhapa \n" +
                "Land- 45 lakh per anna",getString(R.string.property13)));
        pList.add(new PropertyDesc(R.drawable.r14, "Dadagaun, Butwal \n" +
                "Land- 23 lakh per anna",getString(R.string.property14)));
        pList.add(new PropertyDesc(R.drawable.r15, "Maitighar, Patan \n" +
                "Land- 35 lakh per anna",getString(R.string.property15)));
        pList.add(new PropertyDesc(R.drawable.r16, "New Road, Pokhara \n" +
                "Land- 7 lakh per anna",getString(R.string.property16)));
        pList.add(new PropertyDesc(R.drawable.r17, "Haddigaun, Kathmandu \n" +
                "Land- 20 lakh per anna",getString(R.string.property17)));
        pList.add(new PropertyDesc(R.drawable.r18, "Naxal, Kathmandu \n" +
                "Land- 43 lakh per anna",getString(R.string.property18)));
        pList.add(new PropertyDesc(R.drawable.r19, "Mitrapark, Kathmandu \n" +
                "Land- 40 lakh per anna",getString(R.string.property19)));
        pList.add(new PropertyDesc(R.drawable.r20, "Naxal, Kathmandu \n" +
                "Land- 10 lakh per anna",getString(R.string.property20)));
    }
}
