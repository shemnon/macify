import javax.swing.BoxLayout
import javax.swing.JLabel
import java.awt.Font

noparent {
    def titleFont = new Font("San Serif", Font.BOLD, 14)
    def lineFont = new Font("San Serif", Font.PLAIN, 10)

    if (!binding.variables.containsKey('aboutPanel')) {
        if (!binding.variables.containsKey('aboutLabels')) {
            if (!binding.variables.containsKey('aboutTexts')) {
                // auto-gen some about text:

                aboutLabels = [
                    // app icon
                    label(icon:imageIcon('/griffon-icon-64x64.png'), alignmentX:0.5),
                    vstrut(12),
                    // app name in bold
                    label(app.applicationProperties['app.name'], alignmentX:0.5,
                      font:titleFont),
                    vstrut(9),
                    // version
                    label(app.applicationProperties['app.version'], alignmentX:0.5, font:lineFont),
                    vstrut(8),
                    // copyright
                    label('Copyright (c) The original author or authors', alignmentX:0.5, font:lineFont),
                    label('All Rights Reserved', alignmentX:0.5, font:lineFont),
                    vstrut(9),
                ]
            } else {
                aboutLabels = aboutTexts.collect { widget(it, alignmentX:0.5) }
            }
        }
        aboutPanel = vbox(border:emptyBorder(9)) {

            aboutLabels.each { widget(it) }
        }
    }

}

dialog(id:'aboutDialog', title:'', windowClosing: controller.&closeAbout, 
        minimumSize: [283, 10], resizable:false, pack:true, show:true, )
{
    widget(aboutPanel)
}