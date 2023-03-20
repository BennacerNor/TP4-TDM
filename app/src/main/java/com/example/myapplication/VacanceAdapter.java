package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VacanceAdapter extends ArrayAdapter<Vacance> {

    private Context mContext;
    List<Vacance> es;
    public VacanceAdapter(Context context, List<Vacance> vacances) {
        super(context, 0, vacances);
        mContext = context;
        es=vacances;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,
                    false);
        }
        VacanceViewHolder viewHolder = (VacanceViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new VacanceViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.text);

            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Vacance>
        Vacance vacance = getItem(position);
        //il ne reste plus qu'à remplir la vue
        viewHolder.titre.setText(vacance.getTitre());

        String description=vacance.getDescription();

        int orientation = mContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (description.length() > 7) {
                description = description.substring(0,10) + "...";
                viewHolder.desc.setText(description);
            }else{

                viewHolder.desc.setText(vacance.getDescription());
            }
// set a larger number of characters to display when in landscape orientation
        }
  else {
            if (description.length() > 7) {
                description = description.substring(0, 5) + "...";
                viewHolder.desc.setText(description);
            } else {

                viewHolder.desc.setText(vacance.getDescription());
            }
        }
        viewHolder.image.setImageResource(vacance.getIdImage());

        return convertView;}



}
