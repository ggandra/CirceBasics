package Playground

import cats.implicits.toFunctorOps
import io.circe.generic.semiauto.deriveDecoder
import io.circe.{Decoder, Json}
import io.circe.syntax._

object CircePlayground extends App {

	val jsonString = "Hello there".asJson
//	println(jsonString)

	val jsonNumber = 42.asJson
//	println(jsonNumber)

	val jsonArray = Json.arr(jsonString, jsonNumber)
//	println(jsonArray)

	val jsonObj = Json.obj(
		"foo" -> "Bar".asJson
	)
//	println(jsonObj)

//	println(jsonString.mapString(_.toUpperCase()))

	val complexObj = Json.obj(
		//"nested" -> jsonObj,
		"name" -> "gabriel".asJson,
		"lastName" -> "gandra".asJson
	)
	val valueOfComplexObj = complexObj.hcursor.downField("nested")
		.downField("foo")
		.withFocus(_.mapString(_.reverse))
		.top
		.map(_.spaces2)

//	println(valueOfComplexObj)

	val arrayObjects = Json.arr(
		complexObj,
		complexObj
	)
	println(arrayObjects)

	case class Person(name: String, lastName: String) extends ListPerson
	object Person {
		implicit val decoder: Decoder[Person] = deriveDecoder[Person]
	}

//	case class Nested(foo: String)
//
//	object Nested {
//		implicit val decoder: Decoder[Nested] = deriveDecoder[Nested]
//	}

	sealed trait ListPerson
	object ListPerson {
		implicit val decoder: Decoder[ListPerson] = List[Decoder[ListPerson]](
			Person.decoder.widen
		).reduce(_ or _)
	}

//	println(ListPerson.decoder.decodeAccumulating(arrayObjects.hcursor.downArray.))
}
