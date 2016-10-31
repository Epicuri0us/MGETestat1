package testat.hsr.gadgeothek;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

abstract class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    private ListAdapter listAdapter;
    private View parent;
    protected Animation slideOutAnimation;
    protected CardView card;

    ItemViewHolder(View view, ListAdapter listAdapter) {
        super(view);
        setParent(view);
        this.listAdapter = listAdapter;
        card = (CardView) view.findViewById(R.id.card);

        slideOutAnimation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_out_right);
        slideOutAnimation.setDuration(300);
        slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getListAdapter().refresh();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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