package tech.salroid.demo.instabadge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by salroid on 4/2/2017.
 */

public class A extends RecyclerView.Adapter<A.VH>{
    Context context;
    List<String> list;

    public A(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(context).inflate(R.layout.custom_single_row,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class VH extends RecyclerView.ViewHolder {
        TextView textView;
         VH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tea);

        }
    }
}
