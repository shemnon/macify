import javax.swing.BoxLayout
import javax.swing.JLabel
import java.awt.Font

noparent {
    if (!binding.variables.containsKey('aboutPanel')) {
        if (!binding.variables.containsKey('aboutLabels')) {
            if (!binding.variables.containsKey('aboutTexts')) {
                // auto-gen some about text:

                aboutLabels = [
                    // app icon
                    label(icon:imageIcon('/griffon-icon-64x64.png'), alignmentX:0.5, alignmentY:0.5),
                    // blank line
                    blankLabel = label('', alignmentX:0.5, alignmentY:0.5),
                    // app name in bold
                    label(app.applicationProperties['app.name'], alignmentX:0.5, alignmentY:0.5,
                      font:blankLabel.font.deriveFont(Font.BOLD)),
                    // version
                    label(app.applicationProperties['app.version'], alignmentX:0.5, alignmentY:0.5),
                    // copyright
                    label('Copyright (c) The original author or authors', alignmentX:0.5, alignmentY:0.5),
                ]
            } else {
                aboutLabels = aboutTexts.collect { label(it, alignmentX:0.5, alignmentY:0.5) }
            }
        }
        aboutPanel = vbox(alignmentX:0.5, alignmentY:0.5) {
            aboutLabels.each { label(it) }
        }
    }

}

dialog(id:'aboutDialog', title:'', resizable:false, pack:true, show:true) {
    widget(aboutPanel)
}