
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
import javax.swing.Action


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
            def attrs = new LinkedHashMap(attributes) // make a mutable copy
            attrs.action = new AboutDialogAction(app)
            return builder.menuItem(attrs)
        }

    }
}

class AboutDialogAction extends AbstractAction {

    IGriffonApplication app

    public AboutDialogAction(IGriffonApplication app) {
        this.app = app
        putValue(Action.NAME, "About ${app.applicationProperties['app.name']}" as String)
    }

    void actionPerformed(ActionEvent e) {
        GriffonApplicationHelper.createMVCGroup(app, 'MacAboutDialog')
    }

}