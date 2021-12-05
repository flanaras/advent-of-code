package y2021.d02

data class Command(val direction: Direction, val units: Int)
data class Position(var horizontal: Int, var depth: Int, var aim: Int)

enum class Direction {
    UP {
        override fun visitNavigator(navigationVisitor: NavigationVisitor, units: Int) {
            navigationVisitor.visitUp(units)
        }
    },
    DOWN {
        override fun visitNavigator(navigationVisitor: NavigationVisitor, units: Int) {
            navigationVisitor.visitDown(units)
        }
    },
    FORWARD {
        override fun visitNavigator(navigationVisitor: NavigationVisitor, units: Int) {
            navigationVisitor.visitForward(units)
        }
    };

    abstract fun visitNavigator(navigationVisitor: NavigationVisitor, units: Int)
}
