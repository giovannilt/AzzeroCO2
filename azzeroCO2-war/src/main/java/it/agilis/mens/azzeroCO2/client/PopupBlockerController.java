package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.user.client.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 1/29/12
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PopupBlockerController {


    private final static int periodMillis = 4000;


    public static void detect() {
        startPopupBlockerDetection();
        timer.schedule(periodMillis);
    }

    private static Timer timer = new Timer() {
        @Override
        public void run() {
            if (!isPopupBlocked()) {
                checkTestPopup();
            }
        }
    };

    public static boolean isPopupBlocked() {
        return isPopupBlockedNative();
    }


    private static native boolean isPopupBlockedNative() /*-{
        return $wnd.popUpsBlocked;
    }-*/;


    private static native void startPopupBlockerDetection() /*-{
        $wnd.popUpsBlocked = false;
        $wnd.testPopup = $wnd.open('popuptest.html', 'popuptest', 'width=1px,height=1px,left=0,top=0,scrollbars=no');
        if (!$wnd.testPopup || $wnd.testPopup.closed ||
                typeof $wnd.testPopup.closed == 'undefined') {
            $wnd.popUpsBlocked = true
            if ($wnd.testPopup) $wnd.testPopup.close();
        } else {
            $wnd.popUpsBlocked = false;
        }
    }-*/;


    private static native void checkTestPopup() /*-{
        if ($wnd.testPopup) {
            if ($wnd.testPopup.test()) {
                $wnd.popUpsBlocked = false;
            } else {
                $wnd.popUpsBlocked = true;
            }
            $wnd.testPopup.close();
        }
    }-*/;
}
