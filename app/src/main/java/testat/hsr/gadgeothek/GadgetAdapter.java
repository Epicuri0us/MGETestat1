package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.communication.GadgetSelectionListener;
import testat.hsr.gadgeothek.domain.Gadget;

public class GadgetAdapter extends RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder>{

    public class GadgetViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView view;
        public LinearLayout expandable;
        public TextView manufacturer;
        public TextView condition;
        public TextView price;
        public TextView inventorynr;

        public GadgetViewHolder(View itemRoot) {
            super(itemRoot);
            this.parent = itemRoot;
            this.view = (TextView)itemRoot.findViewById(R.id.textView);
            this.expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
            this.manufacturer = (TextView) itemRoot.findViewById(R.id.manufacturer);
            this.condition = (TextView) itemRoot.findViewById(R.id.condition);
            this.price = (TextView) itemRoot.findViewById(R.id.price);
            this.inventorynr = (TextView) itemRoot.findViewById(R.id.inventorynr);
        }
    }

    private List<Gadget> gadgetList;
    private GadgetSelectionListener selectionListener;
    private int expandedPosition = -1;


    public GadgetAdapter(List<Gadget> gadgetList, GadgetSelectionListener selectionListener){
        this.gadgetList = gadgetList;
        this.selectionListener = selectionListener;
    }

    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.gadget_layout,parent,false);
        GadgetViewHolder viewHolder = new GadgetViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GadgetViewHolder holder, final int position) {
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
                handleExpansionFromGadget(position);
                selectionListener.onGadgetSelected(position,gadgetList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }

    private void handleExpansionFromGadget(int position){
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        // Set the current position to "expanded"
        if(expandedPosition == position){
            expandedPosition = -1;
            notifyItemChanged(expandedPosition);
        }else{
            expandedPosition = position;
            notifyItemChanged(expandedPosition);
        }

    }
}



