package y2021.d02


class NavigationTracker {
    fun navigate(navigationVisitor: NavigationVisitor, commands: List<Command>): Int {
        commands.forEach { command ->
            command.direction.visitNavigator(navigationVisitor, command.units)
        }

        return with(navigationVisitor.getPosition()) {
            depth * horizontal
        }
    }
}

abstract class NavigationVisitor {
    protected val position = Position(0, 0, 0)

    @JvmName("getPosition1")
    fun getPosition() = position

    abstract fun visitUp(units: Int)
    abstract fun visitDown(units: Int)
    abstract fun visitForward(units: Int)
}

class NavigationVisitorSimple : NavigationVisitor() {

    override fun visitUp(units: Int) {
        position.depth -= units
    }

    override fun visitDown(units: Int) {
        position.depth += units
    }

    override fun visitForward(units: Int) {
        position.horizontal += units
    }
}

class NavigationVisitorWithAim : NavigationVisitor() {

    override fun visitUp(units: Int) {
        position.aim -= units
    }

    override fun visitDown(units: Int) {
        position.aim += units
    }

    override fun visitForward(units: Int) {
        with(position) {
            horizontal += units
            depth += aim * units
        }
    }
}

