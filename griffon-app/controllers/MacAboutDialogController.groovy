import java.awt.event.WindowEvent

class MacAboutDialogController {
    // this will be injected by Griffon
    def mvcName

    public void closeAbout(WindowEvent evt) {
        println app.groups.keySet()
        destroyMVCGroup(mvcName)
        println app.groups.keySet()
    }
}