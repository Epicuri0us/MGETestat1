package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    private View parent;

    ItemViewHolder(View view) {
        super(view);
        setParent(view);
    }

    void setParent(View view) {
        parent = view;
    }

    View getParent(){
        return parent;
    }

    public abstract void bind(T item, boolean expanded);
}