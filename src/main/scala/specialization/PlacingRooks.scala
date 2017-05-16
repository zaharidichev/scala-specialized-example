package specialization

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit}

sealed trait Color
case object White extends Color
case object Black extends Color
case class RookSpecialized[@specialized(Int) T](x:T, y:T, color: Color)
case class RookNonSpecialized[T](x:T, y:T, color: Color)

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.All))
class PlacingRooks {

  final val BoardLength = 2048

  @Benchmark
  def fillRookSpecialized = {
    for {
      x <- 1 to BoardLength
      y <- 1 to BoardLength
    } yield RookSpecialized[Int](x,y,White)
  }

  @Benchmark
  def fillRookNonSpecialized = {
    for {
      x <- 1 to BoardLength
      y <- 1 to BoardLength
    } yield RookNonSpecialized[Int](x,y,White)
  }


}
