package com.example.sistemahoras;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sistemahoras.dao.Atividades;
import com.exe.sistemahoras.R;

public class Adapter extends ArrayAdapter<Atividades> {

	private List<Atividades>itens;

	public Adapter(Context context, int textViewResourceId, List<Atividades>itens) {
		super(context, textViewResourceId, itens);
		this.itens = itens;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	public void add(Atividades a){
		itens.add(a);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(R.layout.activity_list_view, parent, false);
		}
		Atividades atividades = itens.get(position);
		((TextView) gridView.findViewById(R.id.textViewListDesc)).setText(atividades.getDescricao());
		((TextView) gridView.findViewById(R.id.textViewListDate)).setText(atividades.getDate());
		//((ImageView) gridView.findViewById(R.id.menu_icon)).setBackgroundResource(menu.getImageResourse());
		return gridView;
	}

}

