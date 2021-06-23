package Playground

import io.circe.Json
import io.circe.syntax._

object CircePlayground extends App {

	val jsonString = "Hello there".asJson
	println(jsonString)

	val jsonNumber = 42.asJson
	println(jsonNumber)

	val jsonArray = Json.arr(jsonString, jsonNumber)
	println(jsonArray)

	val jsonObj = Json.obj(
		"foo" -> "Bar".asJson
	)
	println(jsonObj)

	println(jsonString.mapString(_.toUpperCase()))

	val complexObj = Json.obj("nested" -> jsonObj)
	val valueOfComplexObj = complexObj.hcursor.downField("nested")
		.downField("foo")
		.withFocus(_.mapString(_.reverse))
		.top
		.map(_.spaces2)

	println(valueOfComplexObj)
}
