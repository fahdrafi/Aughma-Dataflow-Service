/*
 * Copyright 2014 Aughma Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
   
package com.aughma.dataflow.platform

import java.util.UUID
import scala.xml.Node

trait Datum {
  val _id: UUID
  def toXml: Node
  val fromXml: Node => Datum
}

trait Block {
  def Initialise(params: Map[String, String])
  def Process(datum: Datum) : Seq[Datum]
}

trait DataSource {
  val Targets: Seq[DataReceiver]
}

trait DataReceiver {
  def Post(datum: Datum) 
}

trait Adaptor extends DataReceiver with DataSource {
  val ProcessingNode: Block
}

trait Pipeline extends DataReceiver with DataSource {
  val Input: DataReceiver
  val Output: DataSource
  override val Targets = Output.Targets // check if this even works
}

trait BatchSource extends DataSource {
  def Go
}

trait DataCollector extends DataReceiver {
  def Data: Seq[Datum]
}