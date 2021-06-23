package Playground

import java.util.UUID
import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._


object AutomaticDerivation extends App {

	case class Author(name: String, bio: Option[String])

	case class Article(id: UUID, title: String, content: String, author: Author)

	implicitly[Encoder[Author]]
	implicitly[Encoder[Article]]

	implicitly[Decoder[Author]]
	implicitly[Decoder[Article]]

	Author("Tite Kubo", None).asJson.spaces2
}
