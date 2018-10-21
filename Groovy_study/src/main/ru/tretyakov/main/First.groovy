package ru.tretyakov.main

/**
 * TODO comment.
 */
class First {
    void print() {
        println("Hello there!")
    }

    void print2() {
        print("Goodbye!")
    }

    static void main(String[] args) {
        First first = new First()
        first.print()
    }
}
