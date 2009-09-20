import org.codehaus.griffon.macify.other.AboutMenuItemFactory
import org.codehaus.griffon.macify.other.PreferencesMenuItemFactory
import griffon.util.GriffonPlatformHelper
import griffon.util.GriffonApplicationUtils

class MacifyGriffonAddon {


    // lifecycle methods

    // called once, after the addon is created
    def addonInit(app) {
        if (GriffonApplicationUtils.isMacOSX) {
            // invoke via reflection so we don't have verifier errors
            factories.aboutMenuItem = Class.forName('org.codehaus.griffon.macify.mac.MacAboutMenuItemFactory').newInstance()
            factories.preferencesMenuItem = Class.forName('org.codehaus.griffon.macify.mac.MacPreferencesMenuItemFactory').newInstance()
        }
    }

    // called once, after all addons have been inited
    //def addonPostInit(app) {
    //}

    // called many times, after creating a builder
    //def addonInit(app) {
    //}

    // called many times, after creating a builder and after
    // all addons have been inited
    //def addonPostInit(app) {
    //}


    // to add MVC Groups use create-mvc


    // builder fields, these are added to all builders.
    // closures can either be literal { it -> println it}
    // or they can be method closures: this.&method

    // adds methods to all builders
    //def methods = [
    //    methodName: { /*Closure*/ }
    //]

    // adds properties to all builders
    //def props = [
    //    propertyName: [
    //        get: { /* optional getter closuer},
    //        set: {val-> /* optional setter closuer},
    //  ]
    //]

    // adds new factories to all builders
    def factories = [
        aboutMenuItem : new AboutMenuItemFactory(),
        preferencesMenuItem : new PreferencesMenuItemFactory()
    ]


    //TODO enumerate FactoryBuilderSupporte delegate closures
    def mvcGroups = [
        // MVC Group for "MacAboutDialog"
        'MacAboutDialog' : [
            model : 'MacAboutDialogModel',
            view : 'MacAboutDialogView',
            controller : 'MacAboutDialogController'
        ]
    
    ]
}
