package testat.hsr.gadgeothek;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ListAdapter<ViewHolder extends ItemViewHolder, Item> extends RecyclerView.Adapter<ViewHolder>{

    private ViewGroup parent;
    private Class<ViewHolder> viewHolderClass;
    private List<Item> itemList;
    private int expandedPosition = -1;
    private ViewHolder holder;
    private int rowlayout;
    private PagerAdapter pagerAdapter;

    public ListAdapter(List<Item> itemList, Class<ViewHolder> holderClass, @LayoutRes int rowlayout, PagerAdapter pagerAdapter){
        this.itemList = itemList;
        this.viewHolderClass = holderClass;
        this.rowlayout = rowlayout;
        this.pagerAdapter = pagerAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(rowlayout, parent, false);
        try {
            holder = viewHolderClass.getDeclaredConstructor(View.class, ListAdapter.class).newInstance(v, this);
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

    public void refresh() {
        pagerAdapter.notifyDataSetChanged();
    }
}
