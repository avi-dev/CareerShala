package apps.avi.careershala.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import apps.avi.careershala.Model.ItemClickListener;
import apps.avi.careershala.R;

public class CollegeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public CollegeViewHolder(View itemView){
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView)itemView.findViewById(R.id.image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
