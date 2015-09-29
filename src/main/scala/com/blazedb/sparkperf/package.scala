/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.blazedb

import com.blazedb.sparkperf.util.{TimedResult, PerfLogger}
import org.apache.spark.SparkContext

package object sparkperf {

  case class TestResult(testName: String, resultName: String,
                        optCount: Option[Int] = None, optResult: Option[Any] = None, 
                        stdOut: Option[String] = None, stdErr: Option[String] = None) {
    override def toString() = {
      s"$resultName: count=${optCount.getOrElse("Zero")}"
    }
  }

  def trace(msg: String): Unit = trace(getClass.getName, msg)
  def trace(testName: String, msg: String): Unit = PerfLogger.trace(testName, msg, true)

  trait TestMatrix {
    val A = Array

    def runMatrix(sc: SparkContext, testDims: Product): (Boolean, Seq[TestResult])

  }

  case class TestMatrixSpec(name: String, version: String, genDataParams: GenDataParams)


}
