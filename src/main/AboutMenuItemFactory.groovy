
/**
 * Created by IntelliJ IDEA.
 * User: shemnon
 * Date: Aug 27, 2009
 * Time: 8:40:04 PM
 */

import static griffon.util.GriffonApplicationUtils.isMacOSX
import javax.swing.AbstractAction
import java.awt.event.ActionEvent
import griffon.util.IGriffonApplication
import com.apple.eawt.Application
import com.apple.eawt.ApplicationAdapter
import com.apple.eawt.ApplicationEvent
import griffon.util.GriffonApplicationHelper


class AboutMenuItemFactory extends AbstractFactory {

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        IGriffonApplication app = builder.app
        if (isMacOSX) {
            Application.application.addApplicationListener([
                handleAbout : {ApplicationEvent evt ->
                    GriffonApplicationHelper.createMVCGroup(app, 'MacAboutDialog')
                    evt.handled = true
                }
            ] as ApplicationAdapter)
            return null
        } else {
            def mef = builder.getFactories()['menuItem']
            attributes.value = new AboutDialogAction(app)
            return mef.newInstance(builder, name, value, attributes)
        }

    }
}

class AboutDialogAction extends AbstractAction {

    IGriffonApplication app

    public AboutDialogAction(IGriffonApplication app) {
        this.app = app
    }

    void actionPerformed(ActionEvent e) {
        GriffonApplicationHelper.createMVCGroup(app, 'MacAboutDialog')
    }

}