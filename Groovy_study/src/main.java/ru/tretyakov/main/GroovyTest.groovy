/**
 * Test groovy class.
 */
class GroovyTest {
    def var = 10
    def add(var) {
        assert this.var + var == 20
        return this.var + var
    }


    static void main(String[] args) {
        GroovyTest test = new GroovyTest();
        println(test.add(10))
    }
}

