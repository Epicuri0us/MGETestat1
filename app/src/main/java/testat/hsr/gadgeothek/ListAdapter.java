package testat.hsr.gadgeothek;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import testat.hsr.gadgeothek.communication.ItemSelectionListener;

public class ListAdapter<ViewHolder extends ItemViewHolder, Item> extends RecyclerView.Adapter<ViewHolder>{

    private Class<ViewHolder> viewHolderClass;
    private List<Item> itemList;
    private ItemSelectionListener selectionListener;
    private int expandedPosition = -1;
    private ViewHolder holder;
    private int rowlayout;

    public ListAdapter(List<Item> itemList, ItemSelectionListener selectionListener, Class<ViewHolder> holderClass, @LayoutRes int rowlayout){
        this.itemList = itemList;
        this.selectionListener = selectionListener;
        this.viewHolderClass = holderClass;
        this.rowlayout = rowlayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(rowlayout, parent, false);
        try {
            holder = viewHolderClass.getDeclaredConstructor(View.class).newInstance(v);
        } catch (Exception e) {
            Log.e(ListAdapter.class.getSimpleName(), "Couldn't instantiate ViewHolder (blame Java Type Erasure)");
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item i = itemList.get(position);
        holder.bind(i, position == expandedPosition);

        holder.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExpansion(position);
                selectionListener.onItemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void handleExpansion(int position){
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        // Set the current position to "expanded"
        if(expandedPosition == position){
            expandedPosition = -1;
        }else{
            expandedPosition = position;
        }
        notifyItemChanged(expandedPosition);
    }
}
