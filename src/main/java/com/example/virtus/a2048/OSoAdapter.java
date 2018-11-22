package com.example.virtus.a2048;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class OSoAdapter extends ArrayAdapter<Integer> {
    private Context ct;
    private ArrayList<Integer> arr;

    public OSoAdapter(@NonNull Context context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public void notifyDataSetChanged() {
        arr=DataGame.getDataGame().getArrSO();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_o_vuong, null);
        }
        if (arr.size() > 0) {
            OVuong o = (OVuong) convertView.findViewById(R.id.txvOVuong);
            o.setTett(arr.get(position));
        }
        return convertView;
    }
}
