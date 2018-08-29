package org.ermain.scala.quandl4j.finance_data

import java.io.{File, PrintWriter}
import scala.io.Source._
import com.jimmoores.quandl.DataSetRequest
import com.jimmoores.quandl.tablesaw.TableSawQuandlSession
import tech.tablesaw.api.Table

object QuandlMain {

  def main(args: Array[String]): Unit = {
    println("Retrieving Finance Data.......")

    val session: TableSawQuandlSession = TableSawQuandlSession.create()

    val table: Table = session.getDataSet(
      DataSetRequest.Builder.of("WIKI/GOOGL")
        .withMaxRows(1000)
        .build()
    )

    // println(table)
    getQuandlStockData("WIKI/GOOGL")

    val url = "http://archive.ics.uci.edu/ml/machine-learning-databases/00291/airfoil_self_noise.dat"
    val file_name = "self-noise.csv"

//    val file = new File(file_name)
//    if (!file.exists()){
//      val s = new PrintWriter(file_name)
//      val data = scala.io.Source.fromURL(url).getLines
//
//      data.foreach(l =>  s.write(l.trim.split('\t').filter(_ != "").mkString("", ",", "\n")))
//      s.close()
//    }
  }
  def getQuandlStockData(ticker: String, maxRows: Int = 100): Unit = {

    try{
      val session: TableSawQuandlSession = TableSawQuandlSession.create()

      val table = session.getDataSet(
        DataSetRequest.Builder
          .of(ticker)
          .withMaxRows(maxRows)
          .build()
      ).toString

      val file_name = s"$ticker.csv"

      val file_stock = new File(file_name)

      if (!file_stock.exists()){
        val s = new PrintWriter(file_name)
        val data = fromString(table).getLines()

        data.foreach(l =>  s.write(l.trim.split('\t').filter(_ != "").mkString("", ",", "\n")))
        s.close()
      }

      println(s"Printing data for $ticker")
      println(table)
      println()
    }catch {
      // Catching exceptions
      case e: Exception                      =>  println(s"Error retrieving data: $e")
      case _: UnsupportedOperationException  => println("Unsupported operation")
    }
  }
}
