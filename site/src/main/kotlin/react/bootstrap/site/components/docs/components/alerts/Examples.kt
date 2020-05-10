@file:Suppress("NAME_SHADOWING", "NestedLambdaShadowedImplicitParameter")

package react.bootstrap.site.components.docs.components.alerts

import kotlinx.html.currentTimeMillis
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.bootstrap.components.alert.Alert
import react.bootstrap.components.alert.alert
import react.bootstrap.components.alert.closingElement
import react.bootstrap.components.alert.dismissibleAlert
import react.bootstrap.components.alert.h4
import react.bootstrap.components.alert.link
import react.bootstrap.lib.ClassNames
import react.bootstrap.site.components.docs.fixings.SectionComponent
import react.bootstrap.site.components.docs.fixings.codeExample
import react.bootstrap.site.components.docs.fixings.contentTitle
import react.bootstrap.site.components.docs.fixings.import
import react.bootstrap.site.components.docs.fixings.importClassNames
import react.bootstrap.site.components.docs.fixings.ktB
import react.bootstrap.site.components.docs.fixings.ktIB
import react.bootstrap.site.components.docs.fixings.liveExample
import react.bootstrap.site.components.docs.fixings.ln
import react.bootstrap.site.components.docs.formattedText
import react.bootstrap.site.components.docs.kt
import react.child
import react.dom.a
import react.dom.button
import react.dom.h4
import react.dom.hr
import react.dom.p
import react.dom.strong
import react.functionalComponent
import react.useState

internal class Examples : SectionComponent() {
    override val title: String = "Examples"

    override fun RBuilder.render() {
        sectionTitle(section)
        formattedText {
            """
                Alerts are available for any length of text, as well as an optional dismiss button. For proper styling,
                use one of the eight <required|strong> variants (e.g., <${Alert.Variants.SUCCESS.kt}|code>).
            """.trimIndent()
        }
        liveExample {
            Alert.Variants.values().iterator().forEach { variant ->
                alert(variant) {
                    +"A simple ${variant.name.toLowerCase()} alert-check it out!"
                }
            }
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$alertName")
            ln { }
            Alert.Variants.values().iterator().forEach { variant ->
                ktB(0, alertName, variant.kt) {
                    ln(it) { +"+\"A simple ${variant.name.toLowerCase()} alert-check it out!\"" }
                }
            }
        }
        subSectionTitle("Link color", section)
        formattedText('[', ']') {
            """
                Use the [$linkName|code]-function (only available inside [RElementBuilder<Alert>|code] to quickly provide
                matching colored links within any alert.
            """.trimIndent()
        }
        liveExample {
            Alert.Variants.values().iterator().forEach { variant ->
                alert(variant) {
                    +"A simple ${variant.name.toLowerCase()} alert with "
                    link { a(href = "#") { +"an example link" } }; +". Give it a click if you like."
                }
            }
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$linkName")
            import("components.alert.$alertName")
            ln { }
            Alert.Variants.values().iterator().forEach { variant ->
                ktB(0, alertName, variant.kt) {
                    ln(it) { +"+\"A simple ${variant.name.toLowerCase()} alert with \"" }
                    ln(it) {
                        +"$linkName { a(href = \"#\") { +\"another example link\" } }; +\". Give it a"
                        +"click if you like.\""
                    }
                }
            }
        }
        subSectionTitle("Additional content", section)
        p {
            +"""
                Alerts can also contain additional HTML elements like headings, paragraphs and dividers.
            """.trimIndent()
        }
        liveExample {
            alert(Alert.Variants.SUCCESS) {
                h4 { +"Well done!" }
                p {
                    +"""
                        Aww yeah, you successfully read this important alert message. This example text is going to run a
                        bit longer so that you can see how spacing within an alert works with this kind of content.
                    """.trimIndent()
                }
                hr { }
                p("${ClassNames.MB_0}") {
                    +"Whenever you need to, be sure to use margin utilities to keep things nice and tidy."
                }
            }
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$alertName")
            import("components.alert.h4")
            importClassNames()
            ln { }
            ktB(0, alertName, Alert.Variants.SUCCESS.kt) {
                ktIB(it, "h4", "+\"Well done!\\")
                ktB(it, "p") {
                    ln(it) { +"+\"\"\"" }
                    ln(it + 1) {
                        +"Aww yeah, you successfully read this important alert message. This example text is going to"
                        +" run a"
                    }
                    ln(it + 1) {
                        +"bit longer so that you can see how spacing within an alert works with this kind of content."
                    }
                    ln(it) { +"\"\"\".trimIndent()" }
                }
                ktIB(it, "hr", "")
                ktB(it, "p", "\"\${${ClassNames.MB_0.kt}}\"") {
                    ln(it) {
                        +"+\"Whenever you need to, be sure to use margin utilities to keep things nice and tidy.\""
                    }
                }
            }
        }
        subSectionTitle("Dismissing", section)
        formattedText {
            """
                Use <$dismissibleAlertName { }|code> to create a dismissible alert.
            """.trimIndent()
        }
        liveExample {
            dismissibleAlert(variant = Alert.Variants.WARNING, fade = true) {
                attrs {
                    dismissible?.apply {
                        onClose = {
                            console.log("Close on Alert was clicked. Timestamp: ${currentTimeMillis()}")
                        }
                        onClosed = {
                            console.log("Alert was dismissed. Timestamp: ${currentTimeMillis()}")
                        }
                    }
                }
                strong { +"Holy guacamole!" }; +" You should check in on some of those fields below."
            }
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$dismissibleAlertName")
            ln { }
            ktB(0, dismissibleAlertName, "variant" to Alert.Variants.WARNING.kt, "fade" to "true") {
                ktB(it, "attrs") {
                    ktB(it, "${Alert.Props::dismissible.name}?.apply") {
                        ktB(it, Alert.Props.Dismissible::onClose.name) {
                            ln(it) {
                                +"console.log(\"Close on Alert was clicked. Timestamp: \${currentTimeMillis()}\")"
                            }
                        }
                        ktB(it, Alert.Props.Dismissible::onClosed.name) {
                            ln(it) {
                                +"console.log(\"Close on Alert was clicked. Timestamp: \${currentTimeMillis()}\")"
                            }
                        }
                    }
                }
                ln(it) { +"strong { +\"Holy guacamole!\" }; +\" You should check in on some of those fields below.\"" }
            }
        }
        contentTitle(RBuilder::h4, "Close element")
        formattedText { "You can build your own custom close element, by using <$closingElementName { }|code>." }
        liveExample {
            dismissibleAlert(variant = Alert.Variants.INFO) {
                +"You want some cookies?"
                hr { }
                closingElement {
                    button(classes = "${ClassNames.BTN} ${ClassNames.BTN_SUCCESS}") {
                        +"Sure!"
                    }
                }
            }
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$closingElementName")
            import("components.alert.$dismissibleAlertName")
            importClassNames()
            ln { }
            ktB(0, dismissibleAlertName, "variant" to Alert.Variants.INFO.kt) {
                ln(it) { +" +\"You want some cookies?\"" }
                ktIB(it, "hr", "")
                ktB(it, closingElementName) {
                    ktB(it, "button", "classes" to "\"\${${ClassNames.BTN.kt}} \${${ClassNames.BTN_SUCCESS.kt}}\"") {
                        ln(it) { +"+\"Sure!\"" }
                    }
                }
            }
        }
        contentTitle(RBuilder::h4, "State hook")
        p {
            +"The callbacks can also be used for something like this:"
        }
        liveExample {
            child(dismissibleAlert)
        }
        codeExample {
            import("components.alert.Alert")
            import("components.alert.$closingElementName")
            import("components.alert.$dismissibleAlertName")
            importClassNames()
            ln { }
            ktB(0, "private val dismissibleAlert = functionalComponent<RProps>") {
                ln(it) { +"val (show, setShow) = useState(false)" }
                ln { }
                ktB(it, "if (show)") {
                    ktB(it, dismissibleAlertName, "variant" to Alert.Variants.DANGER.kt) {
                        ktB(it, "attrs") {
                            ktB(it, "${Alert.Props::dismissible.name}?.apply") {
                                ktB(it, Alert.Props.Dismissible::onClosed.name) {
                                    ln(it) { +"setShow(false)" }
                                }
                            }
                        }
                        ln(it) { +"+\"You picked the wrong house, fool!\"" }
                        ktIB(it, "hr", "")
                        ktB(it, closingElementName) {
                            ktB(
                                it,
                                "button",
                                "classes" to "\"\${${ClassNames.BTN.kt}} \${${ClassNames.BTN_INFO.kt}}\""
                            ) {
                                ln(it) { +"+\"Ey, ey ey ey, Big Smoke, it's me, Carl, chill, chill!\"" }
                            }
                        }
                    }
                }
                ktB(it, "else") {
                    ktB(
                        it,
                        "button",
                        "classes" to "\"\${${ClassNames.BTN.kt}} \${${ClassNames.BTN_OUTLINE_DANGER.kt}}\""
                    ) {
                        ktB(it, "attrs") {
                            ln(it) { +"onClickFunction = { setShow(true) }" }
                        }
                        ln(it) { +"+\"Open door & go in\"" }
                    }
                }
            }
        }
    }

    private val dismissibleAlert = functionalComponent<RProps> {
        val (show, setShow) = useState(false)

        // Todo use button component
        if (show) {
            dismissibleAlert(variant = Alert.Variants.DANGER) {
                attrs {
                    dismissible?.apply {
                        onClosed = {
                            setShow(false)
                        }
                    }
                }
                +"You picked the wrong house, fool!"
                hr { }
                closingElement {
                    button(classes = "${ClassNames.BTN} ${ClassNames.BTN_INFO}") {
                        +"Ey, ey ey ey, Big Smoke, it's me, Carl, chill, chill!"
                    }
                }
            }
        } else {
            button(classes = "${ClassNames.BTN} ${ClassNames.BTN_OUTLINE_DANGER}") {
                attrs {
                    onClickFunction = { setShow(true) }
                }
                +"Open door & go in"
            }
        }
    }
}
