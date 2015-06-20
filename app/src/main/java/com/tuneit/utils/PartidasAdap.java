package com.tuneit.utils;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tuneit.R;
import com.tuneit.activities.AnswerActivity;

public class PartidasAdap extends ArrayAdapter<Partida>{
private int             resource;
private LayoutInflater  inflater;
private Context         context;
List<Partida> objects;
    public PartidasAdap(Context ctx, int resourceId,List<Partida> objects) {
        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        this.context=ctx;
        this.objects=objects;
    }
     public View getView ( int position, View convertView, ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.rows, parent,false);
        final Partida P = objects.get(position);
                        TextView PlayerName = (TextView) convertView.findViewById(R.id.PlayerName);
        PlayerName.setText(P.getPartner());

        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        
        ImageButton img = (ImageButton)convertView.findViewById(R.id.imageButton2);
        img.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, AnswerActivity.class);
				i.putExtra("filename", P.getFilename());
				i.putExtra("partidaid", P.getId());
				i.putExtra("genre", P.getGenre());
				i.putExtra("a1", P.getA1());
				i.putExtra("a2", P.getA2());
				i.putExtra("a3", P.getA3());
				context.startActivity(i);
			}
        	
        });
        //fecha.setText(P.getDate());

        /*ImageView imgenero = (ImageView) convertView.findViewById(R.id.genreIma);
        String uri = "drawable/" + P.getGenre();
    int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
    Drawable image = context.getResources().getDrawable(imageResource);
    imgenero.setImageDrawable(image);*/

        return convertView;
}

}
/*public class PartidasAdap extends ArrayAdapter<Partida>{
	private int				resource;
	private LayoutInflater	inflater;
	private Context 		context;
	public PartidasAdap(Context ctx, int resourceId,List<Partida> objects) {
		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from( ctx );
		this.context=ctx;
	}
	 public View getView ( int position, View convertView, ViewGroup parent ) {
		 		LayoutInflater inflater = getLayoutInflater();
		 			convertView = inflater.inflate(R.layout.rows, parent, false);   Partida P = getItem( position );
                                TextView PlayerName = (TextView) convertView.findViewById(R.id.PlayerName);
                PlayerName.setText(P.getPartner());
                 
                TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
                fecha.setText(P.getDate());
                 
                ImageView imgenero = (ImageView) convertView.findViewById(R.id.genreIma);
                String uri = "drawable/" + P.getGenre();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable image = context.getResources().getDrawable(imageResource);
            imgenero.setImageDrawable(image);

                return convertView;
        }
	
}*/
