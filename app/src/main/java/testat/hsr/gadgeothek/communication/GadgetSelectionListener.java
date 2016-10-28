package testat.hsr.gadgeothek.communication;

import testat.hsr.gadgeothek.domain.Gadget;

/**
 * Created by LucaAdmin on 20.10.2016.
 */

public interface GadgetSelectionListener {
    public void onGadgetSelected(int position, Gadget gadget);
}
