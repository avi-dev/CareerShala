package apps.avi.careershala.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import apps.avi.careershala.Adapter.GuideAdapter;
import apps.avi.careershala.Model.Colleges;
import apps.avi.careershala.Model.Guide;
import apps.avi.careershala.Model.ItemClickListener;
import apps.avi.careershala.R;
import dmax.dialog.SpotsDialog;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class HomeFragment extends Fragment {

    private View view;
    List<Guide> guideList;
    RecyclerView recyclerView,recyclerView2;
    FirebaseDatabase database;
    DatabaseReference category;
    AlertDialog dialog;
    FirebaseRecyclerAdapter<Colleges,CollegeViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        BannerSlider bannerSlider = view.findViewById(R.id.bannerSlider);
        List<Banner> banners=new ArrayList<>();

        banners.add(new DrawableBanner(R.drawable.fu));
        banners.add(new DrawableBanner(R.drawable.se));
        banners.add(new DrawableBanner(R.drawable.fi));
        banners.add(new DrawableBanner(R.drawable.th));
        bannerSlider.setBanners(banners);

        guideList = new ArrayList<>();

        guideList.add(new Guide("After 10",R.drawable.aften));
        guideList.add(new Guide("After 12",R.drawable.after12));
        guideList.add(new Guide("Graduation",R.drawable.aftergrad));
        guideList.add(new Guide("Post Graduation",R.drawable.afterpost));

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
     //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        GuideAdapter adapter = new GuideAdapter(getActivity(),guideList);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Colleges");

       dialog= new SpotsDialog(getActivity());
       dialog.show();

        recyclerView2 = view.findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(),2));
      //  loadColleges();
        dialog.dismiss();

    }


    @Override
    public void onStart() {
        super.onStart();
        loadColleges();
    }

    private void loadColleges()
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
        recyclerView2.setAdapter(adapter);
    }



}
