package tech.salroid.demo.instabadge;

import java.util.Locale;

/**
 * Created by salroid on 3/26/2017.
 */

public class UiUtils {

        static boolean isRtl(){
            Locale defLocal = Locale.getDefault();
            return Character.getDirectionality(defLocal.getDisplayName(defLocal).charAt(0))
                    == Character.DIRECTIONALITY_RIGHT_TO_LEFT;
        }

}
