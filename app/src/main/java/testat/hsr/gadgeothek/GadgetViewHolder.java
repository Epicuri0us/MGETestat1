package testat.hsr.gadgeothek;

import android.animation.Animator;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;


public class GadgetViewHolder extends ItemViewHolder<Gadget> {

    private TextView view;
    private TextView nameInner;
    private LinearLayout expandable;
    private TextView manufacturer;
    private TextView condition;
    private TextView price;
    private TextView inventorynr;
    private Button reserveButton;


    public GadgetViewHolder(View itemRoot, ListAdapter listAdapter) {
        super(itemRoot, listAdapter);
        this.setParent(itemRoot);

        view = (TextView) itemRoot.findViewById(R.id.textView);
        nameInner = (TextView) itemRoot.findViewById(R.id.gadgetNameInner);
        expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
        manufacturer = (TextView) itemRoot.findViewById(R.id.manufacturer);
        condition = (TextView) itemRoot.findViewById(R.id.condition);
        price = (TextView) itemRoot.findViewById(R.id.price);
        inventorynr = (TextView) itemRoot.findViewById(R.id.inventorynr);
        reserveButton = (Button) itemRoot.findViewById(R.id.reserve);
    }

    @Override
    public void bind(final Gadget g, final boolean expanded) {
        view.setText(g.getName());
        nameInner.setText(g.getName());
        inventorynr.setText(g.getInventoryNumber());
        price.setText(Double.toString(g.getPrice()));
        condition.setText(g.getCondition().toString());
        manufacturer.setText(g.getManufacturer());

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.reserveGadget(g, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Toast.makeText(view.getContext(), g.getName() + " has been reserved.", Toast.LENGTH_SHORT).show();

                        card.startAnimation(slideOutAnimation);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(view.getContext(), "Error when trying to reserve " + g.getName() + ".", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if (expanded) {
            view.setVisibility(View.GONE);
            expandable.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
            expandable.setVisibility(View.GONE);
        }
    }
}
