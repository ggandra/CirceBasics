package Playground

import cats.implicits._
import java.util.UUID
import io.circe._
import io.circe.syntax._

object CirceDecoders extends App {

	case class Author(name: String, bio: Option[String])

	case class Article(id: UUID, title: String, content: String, author: Author)

	val neil = Json.obj(
		"name" -> "Neil Gaiman".asJson,
		"bio" -> "Neil is a monster describing mythology".asJson
	)

	val otherAuthor = Json.obj("name" -> "Hajime Isayama".asJson)
	val invalidAuthor = Json.obj("bio" -> 42.asJson)

	val uuid = UUID.randomUUID()

	val validArticle = Json.obj(
		"id" -> uuid.asJson,
		"title" -> "Shingeki no Kyojin".asJson,
		"content" -> "A lot of plots".asJson,
		"author" -> Json.obj(
			"name" -> "Hajime Isayama".asJson
		)
	)

	implicit val authorDecoder: Decoder[Author] = Decoder.forProduct2("name", "bio")(Author.apply)

	println(authorDecoder(invalidAuthor.hcursor))
	println(neil.as[Author])
	println(authorDecoder.decodeAccumulating(invalidAuthor.hcursor))

	/*implicit val articleDecoder: Decoder[Article] = json =>
		for {
			id <- json.get[UUID]("id")
			title <- json.get[String]("title")
			content <- json.get[String]("content")
			author <- json.get[Author]("author")
		} yield Article(id, title, content, author)*/

	implicit val articleDecoder: Decoder[Article] = Decoder.forProduct4(
		"id",
		"title",
		"content",
		"author"
	)(Article.apply)

	println(articleDecoder.decodeAccumulating(validArticle.hcursor))

	val jsonString = validArticle.noSpaces

	println(io.circe.parser.parse(jsonString).flatMap(_.as[Article]))
}
