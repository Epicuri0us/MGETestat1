package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    private ListAdapter listAdapter;
    private View parent;

    ItemViewHolder(View view, ListAdapter listAdapter) {
        super(view);
        setParent(view);
        this.listAdapter = listAdapter;
    }

    void setParent(View view) {
        parent = view;
    }

    View getParent(){
        return parent;
    }

    ListAdapter getListAdapter() { return listAdapter; }

    public abstract void bind(T item, boolean expanded);
}