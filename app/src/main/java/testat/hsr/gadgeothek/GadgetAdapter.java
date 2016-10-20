package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import testat.hsr.gadgeothek.domain.Gadget;

public class GadgetAdapter extends RecyclerView.Adapter<GadgetAdapter.ViewHolder>{

    private List<Gadget> gadgetList;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public View parent;
        public TextView view;


        public ViewHolder(View itemRoot, TextView view) {
            super(itemRoot);
            this.parent = itemRoot;
            this.view = view;
        }
    }

    public GadgetAdapter(){
    }

    public GadgetAdapter(List<Gadget> gadgetList){
        this.gadgetList = gadgetList;
    }

    public void setList(List<Gadget> gadgetList){
        this.gadgetList = gadgetList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rowlayout,parent,false);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ViewHolder viewHolder = new ViewHolder(v, textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gadget g = gadgetList.get(position);
        holder.view.setText(g.getName());
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }
}


