package Playground

import cats.data.NonEmptyList
import java.util.UUID
import io.circe._
import io.circe.syntax._

object CirceEncoders extends App {

	case class Author(name: String, bio: Option[String])

	case class Article(id: UUID, title: String, content: String, author: Author)

	val neil = Author(
		"Neil Gaiman",
		None
	)

	val article = Article(
		UUID.randomUUID(),
		"Nordic Mythology",
		"Histories of the nordic mythology",
		neil
	)

	implicit val authorEncoder: Encoder[Author] = author => Json.obj(
		"name" -> author.name.asJson,
		"bio" -> author.bio.asJson
	)

	implicit val articleEncoder: Encoder[Article] = article => Json.obj(
		"id" -> article.id.asJson,
		"title" -> article.title.asJson,
		"content" -> article.content.asJson,
		"author" -> article.author.asJson
	)

	println(article.asJson.spaces2)
	println(List.fill(5)(article).asJson.spaces2)
}
