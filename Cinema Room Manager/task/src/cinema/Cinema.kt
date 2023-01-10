package cinema
const val MIN = 8
const val MAX = 10

fun main() {

    var totalPrice = 0
    var numberTicket = 0
    var curPrice = 0
    println("Enter the number of rows:") //количество рядов
    val rows = readln().toInt()
    println("Enter the number of seats in each row:") //количество мест в ряду
    val seats = readln().toInt()
    val d = MutableList(rows) { MutableList(seats) { "S" } }
    if (rows * seats <= 60) {
        totalPrice = rows * seats * 10
    } else if (rows * seats > 60) {
        totalPrice = if (rows % 2 == 0) {
            rows / 2 * seats * 10 + rows / 2 * seats * 8
        } else rows / 2 * seats * 10 + (rows / 2 + 1) * seats * 8
    }
    printMenu()
    while (true) {
        when (readln().toInt()) {
            0 -> break
            1 -> {
                printSeats(d, rows, seats)
                printMenu()
            }
            2 -> {
                while (true) {
                    println("\nEnter a row number:")
                    val hor = readln().toInt()
                    println("Enter a seat number in that row:")
                    val vert = readln().toInt()
                    if (hor !in 1..rows || vert !in 1..seats) {
                        println("Wrong input!")
                    } else if (d[hor - 1][vert - 1] == "B") {
                        println("That ticket has already been purchased!")
                    } else {
                        var ticket: Int
                        d[hor - 1][vert - 1] = "B"
                        if (rows * seats <= 60) {
                            ticket = MAX
                            curPrice += MAX
                            numberTicket++
                            println("Ticket price: $$ticket\n")
                        } else if (rows * seats > 60 && rows / 2 >= hor) {
                            ticket = MAX
                            curPrice += MAX
                            numberTicket++
                            println("Ticket price: $$ticket\n")
                        } else if (rows * seats > 60 && rows / 2 <= hor) {
                            ticket = MIN
                            curPrice += MIN
                            numberTicket++
                            println("Ticket price: $$ticket\n")
                        }
                        break
                    }
                }
                printMenu()
            }
            3 -> {
                println("""|Number of purchased tickets: $numberTicket
                           |Percentage: ${"%.2f".format(numberTicket / (rows * seats).toDouble() * 100)}%
                           |Current income: $$curPrice
                           |Total income: $$totalPrice""".trimMargin())
                printMenu()
            }
        }
    }
}

fun printSeats(seatSize: MutableList<MutableList<String>>, r: Int, s: Int) {
    for (z in 0..s) {
        when (z) {
            0 -> print("\nCinema:\n ")
            else -> print(" $z")
        }
    }
    println()
    for (i in 0 until r) {
        println("${i + 1} ${seatSize[i].joinToString(" ")}")
    }
}
fun printMenu() {
    println()
    println("""|1. Show the seats
               |2. Buy a ticket
               |3. Statistics
               |0. Exit""".trimMargin())
}
