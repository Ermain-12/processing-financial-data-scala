package org.ermain.scala.quandl4j.finance_data

import java.io.{BufferedWriter, FileWriter}

import com.jimmoores.quandl.{DataSetRequest, QuandlSession}
import com.jimmoores.quandl.tablesaw.TableSawQuandlSession
import tech.tablesaw.api.{ShortColumn, Table}

object QuandlMain {

  def main(args: Array[String]): Unit = {
    println("Retrieving Finance Data.......")

    val session: TableSawQuandlSession = TableSawQuandlSession.create()

    val table = session.getDataSet(
      DataSetRequest.Builder.of("WIKI/GOOGL")
        .withMaxRows(20)
        .build()
    )

    println(table)
  }


  def getQuandlStockData(ticker: String, maxRows: Int = 100): Unit = {

    try{
      val session: TableSawQuandlSession = TableSawQuandlSession.create()

      val table = session.getDataSet(
        DataSetRequest.Builder
          .of(ticker)
          .withMaxRows(maxRows)
          .build()
      )

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
