Ejemplos de parser combinators en Scala
=======================================

Requerimiento: instalar sbt_.

.. _sbt: http://www.scala-sbt.org

Correr los tests::

    $ sbt test

Ejecutar el párser y evaluador de expresiones::

    $ sbt run
    » 1 + 1
    Plus(Num(1.0),Num(1.0)) → 2.0
    » 1 + 2 * 3 / (4 - 5)
    Plus(Num(1.0),Times(Num(2.0),Div(Num(3.0),Minus(Num(4.0),Num(5.0))))) → -5.0

Al ingresar una expresión,
se muestra el parse tree y el resultado.
Si la expresión es inválida,
se muestra el error descrito por el párser.
Para salir, presione enter sin escribir ninguna expresión.

Abrir la consola para jugar con los pársers::

    $ sbt console

    scala> import parser03._
    import parser03._

    scala> Parser03("   1   + 2.5 ")
    res0: parser03.Parser03.ParseResult[parser03.Expression] = [1.14] parsed: Plus(1.0,2.5)

    scala> Evaluator(res0.get)
    res1: Double = 3.5

