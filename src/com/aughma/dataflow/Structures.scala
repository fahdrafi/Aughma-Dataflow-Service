package com.aughma.dataflow

import java.util.UUID
import scala.xml.Node

trait Datum {
  val _id: UUID
  def toXml: Node
  val fromXml: Node => Datum
}

trait DataSource {
  def Start
  val Target: Pipeline
}

trait Block {
  def Process(datum: Datum) : Seq[Datum]
}

trait Adaptor {
  val ProcessingNode: Block
  val Outputs: Seq[Adaptor]
  def Post(datum: Datum)
}

trait Pipeline extends Block