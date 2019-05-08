package presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assem.ibrahem.adtask.R;

import java.util.ArrayList;
import java.util.List;

import model.adInfo;


public class adPresenter extends RecyclerView.Adapter<adPresenter.ViewHolder> {

    private Context context;
    private List<adInfo> listitems;
    public adPresenter(Context context , ArrayList<adInfo> listitem)
    {
        this.context = context;
        this.listitems= listitem;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_ad, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(adPresenter.ViewHolder holder, int position) {
        adInfo item = listitems.get(position);
        holder.Title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Title;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            Title = itemView.findViewById(R.id.title);



        }


        @Override
        public void onClick(View v) {
            //get the position that clicked
            int position  =   getAdapterPosition();
            adInfo item = listitems.get(position);

            /**
             *
             * get the info for the clicked ad to show it in the next {@link DetailsScreen}
             */
            adInfo clickedAd = new adInfo(item.getTitle(),item.getUrl(),item.getPicture(),item.getOrder());


            Intent intent = new Intent(context, com.assem.ibrahem.adtask.DetailsScreen.class);
            intent.putExtra("MyTitle", clickedAd.getTitle());
            intent.putExtra("MyPicture", clickedAd.getPicture());
            intent.putExtra("MyUrl", clickedAd.getUrl());
            context.startActivity(intent);

        }


    }


}
