package slidingrootnav.transform;

import android.view.View;


public interface RootTransformation {

    void transform(float dragProgress, View rootView);
}
