package testat.hsr.gadgeothek;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.communication.ItemSelectionListener;
import testat.hsr.gadgeothek.domain.Gadget;

public class GadgetAdapter extends RecyclerView.Adapter<GadgetAdapter.ViewHolder>{

    private List<Gadget> gadgetList;
    private ItemSelectionListener selectionListener;
    private int expandedPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View parent;
        public TextView view;
        public LinearLayout expandable;
        public TextView manufacturer;
        public TextView condition;
        public TextView price;
        public TextView inventorynr;


        public ViewHolder(View itemRoot, TextView view, TextView manufacturer,
                          TextView condition, TextView price, TextView inventorynr,
                          LinearLayout expandable) {
            super(itemRoot);
            this.parent = itemRoot;
            this.view = view;
            this.expandable = expandable;
            this.manufacturer = manufacturer;
            this.condition = condition;
            this.price = price;
            this.inventorynr = inventorynr;
        }
    }


    public GadgetAdapter(List<Gadget> gadgetList, ItemSelectionListener selectionListener){
        this.gadgetList = gadgetList;
        this.selectionListener = selectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rowlayout,parent,false);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        LinearLayout expandable = (LinearLayout) v.findViewById(R.id.expandableText);
        TextView manufacturer = (TextView) v.findViewById(R.id.manufacturer);
        TextView condition = (TextView) v.findViewById(R.id.condition);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView inventorynr = (TextView) v.findViewById(R.id.inventorynr);
        ViewHolder viewHolder = new ViewHolder(v, textView,manufacturer,condition,
                price,inventorynr,expandable);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Gadget g = gadgetList.get(position);
        holder.view.setText(g.getName());
        holder.inventorynr.setText("Inventorynumber: " +g.getInventoryNumber());
        holder.price.setText("Price: "+ g.getPrice());
        holder.condition.setText("Condition: "+g.getCondition());
        holder.manufacturer.setText("Manufacturer: "+ g.getManufacturer());

        if (position == expandedPosition) {
            holder.expandable.setVisibility(View.VISIBLE);
        } else {
            holder.expandable.setVisibility(View.GONE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                // Set the current position to "expanded"
                expandedPosition = position;
                notifyItemChanged(expandedPosition);
                selectionListener.onItemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }
}


