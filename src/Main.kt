fun main() {
    println("Выберите размерность поля морского боя:")
    println("1 — Маленькое (8x8)")
    println("2 — Среднее (10x10)")
    println("3 — Большое (12x12)")
    print("Введите номер варианта: ")

    val input = readln()

    val size = when (input) {
        "1" -> 8
        "2" -> 10
        "3" -> 12
        else -> {
            println("Неверный ввод! Создастся стандартное поле 10x10.")
            10
        }
    }

    val playerField = createEmptyField(size)
    val enemyField = createEmptyField(size)

    if (playerField.size > 5 && playerField.size > 3) {
        playerField[3][2] = '#'
        playerField[3][3] = '#'
        playerField[3][4] = '#'
        playerField[3][5] = '#'
    }

    if (enemyField.size > 7 && enemyField.size > 5) {
        enemyField[5][7] = '#'
        enemyField[6][7] = '#'
        enemyField[7][7] = '#'
        enemyField[5][5] = 'X'
        enemyField[5][6] = 'O'
    }

    printBothFields(playerField, enemyField, debug = false) // По умолчанию дебаг отключен
}

fun createEmptyField(size: Int = 10): Array<CharArray> {
    return Array(size) { CharArray(size) { '.' } }
}

fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false) {
    println("=== $title ===")

    print("   ")
    for (c in 0..9) {
        print("$c ")
    }
    println()

    if (debug) {
        val border = "   " + "+---".repeat(10) + "+"
        for (r in 0..9) {
            println(border)
            print(" $r | ")
            for (c in 0..9) {
                val cell = field.getOrNull(r)?.getOrNull(c) ?: '.'
                val displayCell = if (cell == '#' && !showShips) '.' else cell
                print("$displayCell | ")
            }
            println()
        }
        println(border)
    } else {
        for (r in 0..9) {
            print("$r  ")
            for (c in 0..9) {
                val cell = field.getOrNull(r)?.getOrNull(c) ?: '.'
                val displayCell = if (cell == '#' && !showShips) '.' else cell
                print("$displayCell ")
            }
            println()
        }
    }
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false, debug: Boolean = false) {
    if (player.size != enemy.size) {
        println("Ошибка: Поля должны быть одного размера!")
        return
    }

    println("------- Ваше поле -------          ------- Поле противника -------")
    if (debug) {
        val border = "   " + "+---".repeat(10) + "+"
        print("   ")
        for (c in 0..9) print("$c ")
        print("        ")
        print("   ")
        for (c in 0..9) print("$c ")
        println()

        for (r in 0..9) {
            print(border)
            print("        ")
            println(border)

            print(" $r | ")
            for (c in 0..9) {
                val cell = player.getOrNull(r)?.getOrNull(c) ?: '.'
                print("$cell | ")
            }

            print("        ")

            print(" $r | ")
            for (c in 0..9) {
                val cell = enemy.getOrNull(r)?.getOrNull(c) ?: '.'
                val displayCell = if (cell == '#' && !showEnemyShips) '.' else cell
                print("$displayCell | ")
            }
            println()
        }
        print(border)
        print("        ")
        println(border)
    } else {
        print("   ")
        for (c in 0..9) print("$c ")
        print("        ")
        print("   ")
        for (c in 0..9) print("$c ")
        println()

        for (r in 0..9) {
            print("$r  ")
            for (c in 0..9) {
                val cell = player.getOrNull(r)?.getOrNull(c) ?: '.'
                print("$cell ")
            }

            print("        ")

            print("$r  ")
            for (c in 0..9) {
                val cell = enemy.getOrNull(r)?.getOrNull(c) ?: '.'
                val displayCell = if (cell == '#' && !showEnemyShips) '.' else cell
                print("$displayCell ")
            }
            println()
        }
    }
}
