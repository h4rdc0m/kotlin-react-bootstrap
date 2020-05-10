package react.bootstrap.site.components.docs.layout

import react.RBuilder
import react.bootstrap.layout.Container
import react.bootstrap.layout.container
import react.bootstrap.site.components.docs.fixings.CodeExampleBuilder
import react.bootstrap.site.components.docs.fixings.import
import react.bootstrap.site.components.docs.fixings.ktB

internal val RBuilder.containerFun: String
    get() = RBuilder::container.name

internal val Container.Viscosities.kt: String
    get() = "${Container::class.simpleName}.${Container.Viscosities::class.simpleName}.$name"

internal fun CodeExampleBuilder.ktContainer(block: CodeExampleBuilder.(indentationLevel: Int) -> Unit) {
    ktB(opener = containerFun) {
        block(1)
    }
}

internal fun CodeExampleBuilder.gridImport(className: String) {
    import("layout.grid.$className")
}

internal fun CodeExampleBuilder.importContainerFun() {
    gridImport(containerFun)
}

internal fun CodeExampleBuilder.importContainer() {
    import("layout.${Container::class.simpleName}")
}
