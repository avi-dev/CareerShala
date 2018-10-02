package apps.avi.careershala.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import apps.avi.careershala.Adapter.CollegeViewHolder;
import apps.avi.careershala.Adapter.ProductAdapter;
import apps.avi.careershala.Model.Colleges;
import apps.avi.careershala.Model.ItemClickListener;
import apps.avi.careershala.R;
import dmax.dialog.SpotsDialog;

public class CollegeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
  //  FirebaseDatabase database;
    //DatabaseReference category;
    AlertDialog dialog;
    ProductAdapter adapter;
    List<Colleges> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.college, container, false);
        initViews();
       // loadColleges();
        //dialog.dismiss();
        return view;
    }

    private void initViews()
    {

       // database = FirebaseDatabase.getInstance();
        //category = database.getReference("Colleges");
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // dialog= new SpotsDialog(getActivity());
        //dialog.show();
       // loadColleges();
        productList = new ArrayList<>();

        DatabaseReference dbProducts = FirebaseDatabase.getInstance().getReference("Colleges");

        dbProducts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        Colleges p = productSnapshot.getValue(Colleges.class);
                        productList.add(p);
                    }

                    adapter = new ProductAdapter(getActivity(), productList);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




























  /*  private void loadColleges()
    {
        FirebaseRecyclerAdapter<Colleges,CollegeViewHolder> adapter = new FirebaseRecyclerAdapter<Colleges,
                CollegeViewHolder>(Colleges.class,R.layout.card_row,CollegeViewHolder.class,category) {
            @Override
            protected void populateViewHolder(CollegeViewHolder viewHolder, Colleges model, int position) {
                viewHolder.txtName.setText(model.getTitle());
                Picasso.with(getActivity().getApplicationContext()).load(model.getImage()).into(viewHolder.imageView);

                final Colleges clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }*/
}
