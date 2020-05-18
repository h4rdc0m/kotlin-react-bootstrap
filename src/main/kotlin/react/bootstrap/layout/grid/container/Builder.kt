package react.bootstrap.layout.grid.container

import react.RBuilder
import react.RHandler
import react.ReactElement

fun RBuilder.container(
    viscosity: Container.Viscosities? = null,
    classes: String? = null,
    block: RHandler<Container.Props>
): ReactElement = child(Container::class) {
    attrs {
        this.viscosity = viscosity
        this.classes = classes
    }
    block()
}

class ContainerBuilder(private val builder: RBuilder) {
    fun fluid(classes: String? = null, block: RHandler<Container.Props>): ReactElement =
        builder.container(viscosity = Container.Viscosities.FLUID, classes = classes, block = block)

    fun sm(classes: String? = null, block: RHandler<Container.Props>): ReactElement =
        builder.container(viscosity = Container.Viscosities.SM, classes = classes, block = block)

    fun md(classes: String? = null, block: RHandler<Container.Props>): ReactElement =
        builder.container(viscosity = Container.Viscosities.MD, classes = classes, block = block)

    fun lg(classes: String? = null, block: RHandler<Container.Props>): ReactElement =
        builder.container(viscosity = Container.Viscosities.LG, classes = classes, block = block)

    fun xl(classes: String? = null, block: RHandler<Container.Props>): ReactElement =
        builder.container(viscosity = Container.Viscosities.XL, classes = classes, block = block)
}

val RBuilder.Containers: ContainerBuilder
    get() = ContainerBuilder(this)