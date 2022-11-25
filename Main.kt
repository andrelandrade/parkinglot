package parking

data class Car(val licensePlate: String, val color: String) {}

class ParkingLot(private val numberSpots: Int) {
    private val parkingSpaces: MutableList<Car?> = MutableList(numberSpots) { null }

    init {
        println("Created a parking lot with $numberSpots spots.")
    }

    fun status() {
        if (isEmpty()) {
            println("Parking lot is empty.")
        } else {
            for (i in parkingSpaces.indices) {
                val car = parkingSpaces[i]

                if (car != null ) {
                    println("${i + 1} ${car.licensePlate} ${car.color}")
                }
            }
        }
    }

    fun park(car: Car) {
        if (!this.isFull()) {
            val freeSpot = nextFreeSpot()
            parkingSpaces[freeSpot] = car
            println("${car.color} car parked in spot ${freeSpot + 1}.")
        }
    }

    fun leave(spot: Int) {
        parkingSpaces[spot - 1] = null
        println("Spot $spot is free.")
    }

    fun regByColor(color: String): String {
        val newColor = color.uppercase()
        val cars = colorFilter(newColor)

        val regByColor = mutableListOf<String>()

        cars.forEach {
            regByColor.add(it.licensePlate)
        }

        var carsList = regByColor.joinToString(", ")
        if (carsList == "") {
            carsList = "No cars with color $color were found."
        }

        return carsList
    }

    fun spotByColor(color: String): String {
        val newColor = color.uppercase()

        val spotByColor = mutableListOf<String>()
        val cars = colorFilter(newColor)

        cars.forEach {
            spotByColor.add((parkingSpaces.indexOf(it) + 1).toString())
        }

        var carsList = spotByColor.joinToString(", ")
        if (carsList == "") {
            carsList = "No cars with color $color were found."
        }

        return carsList
    }

    fun spotByReg(license: String): Int {
        return parkingSpaces.indexOfFirst { it?.licensePlate == license }
    }

    private fun isFull(): Boolean {
        val full = parkingSpaces.all{it != null}
        if (full) println("Sorry, the parking lot is full.")

        return full
    }

    private fun isEmpty(): Boolean {
        return parkingSpaces.all{it == null}
    }

    private fun nextFreeSpot(): Int {
        return parkingSpaces.indexOf(null)
    }

    private fun colorFilter(filter: String): List<Car> {
        return parkingSpaces.filterNotNull().filter{ it.color == filter }
    }

}

fun main(args: Array<String>) {
    var parkingLot: ParkingLot? = null

    fun created(): Boolean {
        if (parkingLot == null)
            println("Sorry, a parking lot has not been created.")

        return parkingLot != null
    }

    do {
        val fullCommand = readln().split(' ')
        val command = fullCommand[0]

        when (command) {
            "create" -> {
                val numberOfSpots = fullCommand[1].toInt()

                parkingLot = ParkingLot(numberOfSpots)
            }
            "park" -> {
                if (created()) {
                    val carColor = fullCommand[2].uppercase()
                    val licensePlate = fullCommand[1]

                    val car = Car(licensePlate, carColor)

                    parkingLot?.park(car)
                }
            }
            "leave" -> {
                if (created()) {
                    val spot = fullCommand[1].toInt()

                    parkingLot?.leave(spot)
                }
            }
            "status" -> {
                if (created()) {
                    parkingLot?.status()
                }
            }
            "reg_by_color" -> {
                if (created()) {
                    val color = fullCommand[1]
                    println(parkingLot?.regByColor(color))
                }
            }
            "spot_by_color" -> {
                if (created()) {
                    val color = fullCommand[1].uppercase()
                    println(parkingLot?.spotByColor(color))
                }
            }
            "spot_by_reg" -> {
                if (created()) {
                    val reg = fullCommand[1]
                    val index = parkingLot?.spotByReg(reg)

                    if (index == -1) {
                        println("No cars with registration number $reg were found.")
                    } else {
                        println(index?.plus(1))
                    }
                }
            }
        }
    } while (command != "exit")
}